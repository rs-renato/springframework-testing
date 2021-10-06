package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    Person person;

    @BeforeEach
    void setUp() {
        person = new Person(1l, "Jhon", "Doe");
    }

    @Test
    void propertiesShouldBeSet(){
        assertAll("Test Properties Set",
                () -> assertEquals("Jhona", person.getFirstName(), "First name failed"),
                () -> assertEquals("Doe", person.getLastName(), "Last name failed")
        );
    }
}