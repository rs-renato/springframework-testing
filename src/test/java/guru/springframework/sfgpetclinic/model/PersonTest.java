package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonTest implements ModelTests {

    Person person;

    @BeforeEach
    void setUp() {
        person = new Person(1l, "Jhon", "Doe");
    }

    @Test
    void propertiesShouldBeSet(){
        assertAll("Test Properties Set",
                () -> assertEquals("Jhon", person.getFirstName(), "First name failed"),
                () -> assertEquals("Doe", person.getLastName(), "Last name failed")
        );
    }

    @RepeatedTest(value = 10, name = "{displayName} : {currentRepetition}/{totalRepetitions}")
    @DisplayName("Repeated Test")
    void repeatedTests(){

    }

    @RepeatedTest(value = 5, name = "{displayName} : {currentRepetition}/{totalRepetitions}")
    @DisplayName("Repeated Test")
    void repeatedTestsWithDepInjection(TestInfo testInfo, RepetitionInfo repetitionInfo){
        System.out.println(testInfo);
        System.out.println(repetitionInfo);
    }


}