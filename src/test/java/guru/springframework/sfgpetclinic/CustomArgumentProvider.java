package guru.springframework.sfgpetclinic;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class CustomArgumentProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of("Eight", 8),
                Arguments.of("Nine", 9),
                Arguments.of("Ten", 10)
        );
    }

    static Stream<Arguments> getArgs(){
        return Stream.of(
                Arguments.of("Four", 4),
                Arguments.of("Five", 5),
                Arguments.of("Six", 6)
        );
    }
}
