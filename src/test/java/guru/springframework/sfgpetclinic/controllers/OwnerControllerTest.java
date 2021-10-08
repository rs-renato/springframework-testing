package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.OwnerArgumentProvider;
import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    Model model;

    @Mock
    OwnerService ownerService;

    @Mock
    BindingResult bindingResult;

    @InjectMocks
    OwnerController ownerController;

    @Captor
    ArgumentCaptor<String> argumentCaptor;

    @BeforeEach
    void setUp() {
        when(ownerService.findAllByLastNameLike(argumentCaptor.capture())).thenAnswer(invocationOnMock -> {
            String name = invocationOnMock.getArgument(0);

            return switch (name) {
                case "%Silva%" -> List.of(new Owner(1L, "Amanda", "Silva"));
                case "%NotFound%", "%%" -> Collections.emptyList();
                case "%Souza%" -> List.of(
                        new Owner(1L, "Amanda", "Souza"),
                        new Owner(1L, "Bruna", "Souza")
                );
                default -> throw new RuntimeException("Invalid Arg");
            };
        });
    }


    @DisplayName("Process Find Form")
    @ParameterizedTest(name = "Arguments-{index} [{arguments}]")
    @ArgumentsSource(OwnerArgumentProvider.class)
    void processFindForm(long id, String firstName, String lastName, String argExpected, String viewExpected) {
        Owner owner = new Owner(id, firstName, lastName);

        String viewName = ownerController.processFindForm(owner, bindingResult, model);
        assertEquals(argExpected, argumentCaptor.getValue());
        assertEquals(viewExpected, viewName);
    }

    /*@Test
    void processFindForm() {
        Owner owner = new Owner(1L, "Amanda", "Silva");

        String viewName = ownerController.processFindForm(owner, bindingResult, model);
        assertEquals("%Silva%", argumentCaptor.getValue());
        assertEquals("redirect:/owners/1", viewName);
    }

    @Test
    void processFindFormNotFound() {
        Owner owner = new Owner(1L, "Amanda", "NotFound");

        String viewName = ownerController.processFindForm(owner, bindingResult, model);
        assertEquals("%NotFound%", argumentCaptor.getValue());
        assertEquals("owners/findOwners", viewName);
    }

    @Test
    void processFindFormNotFoundNullLastname() {
        Owner owner = new Owner(1L, "Amanda", null);

        String viewName = ownerController.processFindForm(owner, bindingResult, model);
        assertEquals("%%", argumentCaptor.getValue());
        assertEquals("owners/findOwners", viewName);
    }

    @Test
    void processFindFormMultiple() {
        Owner owner = new Owner(1L, "Amanda", "Souza");

        String viewName = ownerController.processFindForm(owner, bindingResult, model);
        assertEquals("%Souza%", argumentCaptor.getValue());
        assertEquals("owners/ownersList", viewName);
    }*/
}