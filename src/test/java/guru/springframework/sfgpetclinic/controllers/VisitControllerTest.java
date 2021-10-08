package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.VisitService;
import guru.springframework.sfgpetclinic.services.map.PetMapService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

//    @Mock
//    PetService petService;

    @Spy
    PetMapService petService;

    @InjectMocks
    VisitController visitController;

    @Test
    void loadPetWithVisit() {
        Map<String, Object> model = new HashMap<>();
        Pet pet = new Pet(1L);

        when(petService.findById(anyLong())).thenReturn(pet);

        Visit visit = visitController.loadPetWithVisit(1L, model);

        assertNotNull(visit);
        assertNotNull(visit.getPet());
    }

    @Test
    void loadPetWithVisitUsingSpy(){

        Map<String, Object> model = new HashMap<>();
        Pet pet = new Pet(1L);

        petService.save(pet);

        when(petService.findById(anyLong())).thenCallRealMethod();

        Visit visit = visitController.loadPetWithVisit(1L, model);

        assertNotNull(visit);
        assertNotNull(visit.getPet());

        verify(petService, atLeastOnce()).findById(anyLong());

    }
}