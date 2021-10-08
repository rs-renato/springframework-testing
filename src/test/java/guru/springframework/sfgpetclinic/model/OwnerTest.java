package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.CustomArgumentProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Owner Test")
class OwnerTest implements ModelTests {

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

    @DisplayName("Value Source")
    @ParameterizedTest(name = "{displayName} - [{index} - {arguments}]")
    @ValueSource(strings = {"parameterized", "test", "with", "valueSource"})
    void testParameterized(String val){
        System.out.println(val);
    }

    @DisplayName("Enum Source")
    @ParameterizedTest(name = "{displayName} - [{index} - {arguments}]")
    @EnumSource(OwnerType.class)
    void testParameterized(OwnerType type){
        System.out.println(type);
    }

    @DisplayName("CSV Source")
    @ParameterizedTest(name = "{displayName} - [{index} - {arguments}]")
    @CsvSource({
            "First, 0",
            "Second, 1",
            "Third, 2",
    })
    void testParameterized(String name, int value){
        System.out.println("name/value:" + name +"/" + value);
    }

    @DisplayName("CSV File")
    @ParameterizedTest(name = "{displayName} - [{index} - {arguments}]")
    @CsvFileSource(resources = "/input.csv", numLinesToSkip = 1)
    void testParameterizedCsvFile(String name, int value){
        System.out.println("name/value:" + name +"/" + value);
    }


    @DisplayName("Method Source")
    @ParameterizedTest(name = "{displayName} - [{index} - {arguments}]")
    @MethodSource("guru.springframework.sfgpetclinic.CustomArgumentProvider#getArgs")
    void testParameterizedMethodProvider(String name, int value){
        System.out.println("name/value:" + name +"/" + value);
    }


    @DisplayName("Arguments Source")
    @ParameterizedTest(name = "{displayName} - [{index} - {arguments}]")
    @ArgumentsSource(CustomArgumentProvider.class)
    void testParameterizedArgsProvider(String name, int value){
        System.out.println("name/value:" + name +"/" + value);
    }

    static Stream<Arguments> getArgs(){
        return Stream.of(
                Arguments.of("Four", 4),
                Arguments.of("Five", 5),
                Arguments.of("Six", 6)
        );
    }
}