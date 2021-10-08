package guru.springframework.sfgpetclinic;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class OwnerArgumentProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of(1L, "Amanda", "Silva", "%Silva%", "redirect:/owners/1"),
                Arguments.of(1L, "Amanda", "NotFound", "%NotFound%", "owners/findOwners"),
                Arguments.of(1L, "Amanda", null, "%%", "owners/findOwners"),
                Arguments.of(1L, "Amanda", "Souza", "%Souza%", "owners/ownersList")
        );
    }
}
