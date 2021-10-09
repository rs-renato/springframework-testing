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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {

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