package org.springframework.samples.petclinic.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.repository.PetRepository;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClinicServiceImplTest {

    @Mock
    PetRepository petRepository;

    @InjectMocks
    ClinicServiceImpl clinicService;
    
    @Test
    void findPetTypes() {
        when(petRepository.findPetTypes()).thenReturn(new ArrayList<>());

        Collection<PetType> petTypeList = clinicService.findPetTypes();

        assertNotNull(petTypeList);
        verify(petRepository).findPetTypes();

    }
}