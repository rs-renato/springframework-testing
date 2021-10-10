package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {

    MockMvc mockMvc;

    @Mock
    ClinicService clinicService;

    @Mock
    Map<String, Object> model;

    @InjectMocks
    VetController vetController;

    List<Vet> vetsList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        vetsList.add(new Vet());
        when(clinicService.findVets()).thenReturn(vetsList);
        mockMvc = MockMvcBuilders.standaloneSetup(vetController).build();
    }

    @Test
    void testControllerShowVetList() throws Exception {
        mockMvc.perform(get("/vets.html"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("vets"))
                .andExpect(view().name("vets/vetList"));
    }

    @Test
    void showVetList() {
        String view = vetController.showVetList(model);
        // verify one interation on findVets
        then(clinicService).should().findVets();
        // same verification, another way
        verify(clinicService).findVets();
        verify(model, times(1)).put(anyString(), any());
        assertEquals("vets/vetList", view);
    }

    @Test
    void showResourcesVetList() {
        Vets vets = vetController.showResourcesVetList();
        assertEquals(vetsList.size(), vets.getVetList().size());
        verify(clinicService).findVets();
        verify(model, never()).put(anyString(), any());
    }
}