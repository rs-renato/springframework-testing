package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.TimingExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@ExtendWith(TimingExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IndexControllerTest implements ControllerTests{

    IndexController controller;

    @BeforeAll
    void setUp() {
        controller = new IndexController();
    }

    @Test
    void oopsHandler() {
       Exception exception = assertThrows(IllegalArgumentException.class, () ->
               controller.oopsHandler()
        );

       assertEquals("oops!", exception.getMessage());
    }

    @Test
    void testTimeout(){
        // waits for operation completion
        assertTimeout(Duration.ofMillis(1000),
                () -> {
                    TimeUnit.MILLISECONDS.sleep(100);
                    System.out.println("operation completed.");
                });
    }

    @Test
    void testTimeoutPeemptively(){
        // doesn't wait for operation completion
        assertTimeoutPreemptively(Duration.ofMillis(1000),
                () -> {
                    TimeUnit.MILLISECONDS.sleep(100);
                    System.out.println("operation preemptively completed.");
                });
    }

    @Test
    void testAssumptonTrue() {
        // this doesn't fails the test, only mask it as ignored
        assumeTrue("someEnvValue".equals(System.getenv("someEnv")));
    }

    @Test
    @EnabledOnOs(OS.MAC)
    void testOnMacOS() {
        assumeTrue("mac os x".equalsIgnoreCase(System.getProperty("os.name")));
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void testOnWindowsOS() {
        assumeTrue("win 32".equalsIgnoreCase(System.getProperty("os.name")));
    }

    @Test
    @EnabledOnJre(JRE.OTHER)
    void testOnJre15() {
        assumeTrue("15.0.2".equalsIgnoreCase(System.getProperty("java.version")));
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "USER", matches = "renatorodrigues")
    void testOnEnvVariable() {
        assertTrue("renatorodrigues".equalsIgnoreCase(System.getenv("USER")));
    }
}