package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    MockMvc mockMvc;

    @Mock
    ClinicService clinicService;

    @Mock
    Map<String, Object> model;

    @InjectMocks
    OwnerController ownerController;

    @Captor
    ArgumentCaptor<String> captor;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    @AfterEach
    void tearDown() {
        reset(clinicService);
    }

    @Test
    void updateOwnerPostValid() throws Exception {

        mockMvc.perform(post("/owners/{ownerId}/edit", 1)
                    .param("firstName", "Jane")
                    .param("lastName", "Doe")
                    .param("address", "R")
                    .param("city", "C")
                    .param("telephone", "123456"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/owners/1"));
    }

    @Test
    void updateOwnerPostNotValid() throws Exception {

        mockMvc.perform(post("/owners/{ownerId}/edit", 1)
                    .param("firstName", "Jane")
                    .param("lastName", "Doe")
                    .param("city", "C"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeHasErrors("owner"))
                .andExpect(model().attributeHasFieldErrors("owner", "address"))
                .andExpect(model().attributeHasFieldErrors("owner", "telephone"));
    }

    @Test
    void processCreationFormNotValid() throws Exception {

        mockMvc.perform(post("/owners/new")
                    .param("firstName", "Jane")
                    .param("lastName", "Doe")
                    .param("city", "C"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeHasErrors("owner"))
                .andExpect(model().attributeHasFieldErrors("owner", "address"))
                .andExpect(model().attributeHasFieldErrors("owner", "telephone"));
    }

    @Test
    void processCreationForm() throws Exception {

        mockMvc.perform(post("/owners/new")
                    .param("firstName", "Jane")
                    .param("lastName", "Doe")
                    .param("address", "R")
                    .param("city", "C")
                    .param("telephone", "123456"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrlPattern("/owners/*"));
    }

    @Test
    void returnOneOwner() throws Exception {
        Owner owner = new Owner();
        owner.setId(1);
        owner.setLastName("findMe");
        when(clinicService.findOwnerByLastName(owner.getLastName())).thenReturn(List.of(owner));

        mockMvc.perform(get("/owners")
                    .param("lastName", owner.getLastName()))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/owners/1"));
//                .andExpect(view().name("redirect:/owners/1"));

        verify(clinicService).findOwnerByLastName((captor.capture()));

        assertEquals(owner.getLastName(), captor.getValue());
    }

    @Test
    void returnListOfOwners() throws Exception {
        when(clinicService.findOwnerByLastName("")).thenReturn(List.of(new Owner(), new Owner()));

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("selections"))
                .andExpect(view().name("owners/ownersList"));

        verify(clinicService).findOwnerByLastName((captor.capture()));

        assertTrue(captor.getValue().isEmpty());
    }

    @Test
    void findByNameNotFound() throws Exception {
        mockMvc.perform(get("/owners")
                    .param("lastName", "dont find me"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"));
    }

    @Test
    void initCreationFormTest() throws Exception {
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("owners/createOrUpdateOwnerForm"));
    }
}