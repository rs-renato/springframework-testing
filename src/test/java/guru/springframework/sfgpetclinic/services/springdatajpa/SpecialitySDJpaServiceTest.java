package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDJpaService service;

    @Captor
    ArgumentCaptor<Long> longArgumentCaptor;

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(specialtyRepository).deleteById(1L);
    }

    @Test
    void findById() {
        Speciality speciality = new Speciality();
        when(specialtyRepository.findById(1L)).thenReturn(Optional.of(speciality));

        Speciality specialityFound = service.findById(1L);
        assertNotNull(specialityFound);
        verify(specialtyRepository).findById(anyLong());
    }

    @Test
    void exceptionThrowMock() {
        doThrow(new RuntimeException("err")).when(specialtyRepository).deleteAll();
        RuntimeException exception = assertThrows(RuntimeException.class, () -> specialtyRepository.deleteAll());
        verify(specialtyRepository).deleteAll();
        assertEquals("err", exception.getMessage());
    }

    @Test
    void argumentCaptor(){
        Speciality speciality = new Speciality();
        when(specialtyRepository.findById(longArgumentCaptor.capture())).thenReturn(Optional.of(speciality));

        service.findById(1L);
        assertEquals(Long.valueOf(1L), longArgumentCaptor.getValue());
    }
}