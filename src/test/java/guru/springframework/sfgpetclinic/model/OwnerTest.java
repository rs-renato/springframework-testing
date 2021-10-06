package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Owner Test")
class OwnerTest {

    Owner owner;

    @BeforeEach
    void setUp() {
        owner = new Owner(1l, "Jhon", "Doe");
        owner.setCity("Rio");
        owner.setTelephone("123456");
    }

    @DisplayName("Test Properties Set With inheritance")
    @Test
    void inheritancePropertiesShouldBeSet(){
        assertAll("Test Properties Set",
                ()-> assertAll("Person Properties",
                        () -> assertEquals("Jhon", owner.getFirstName(), "First name failed"),
                        () -> assertEquals("Doe", owner.getLastName(), "Last name failed")),
                () -> assertAll("Owner Properties",
                        () -> assertEquals("Rio", owner.getCity(), "City failed"),
                        () -> assertEquals("123456", owner.getTelephone(), "Phone number failed"))
        );
    }
}