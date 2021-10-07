package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.TimingExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

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
        assertTimeout(Duration.ofMillis(100),
                () -> {
                    TimeUnit.MILLISECONDS.sleep(50);
                    System.out.println("operation completed.");
                });
    }

    @Test
    void testTimeoutPeemptively(){
        // doesn't wait for operation completion
        assertTimeoutPreemptively(Duration.ofMillis(100),
                () -> {
                    TimeUnit.MILLISECONDS.sleep(50);
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
        assumeTrue("mac os x".equalsIgnoreCase(System.getenv("os.name")));
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void testOnWindowsOS() {
        assumeTrue("win 32".equalsIgnoreCase(System.getenv("os.name")));
    }

    @Test
    @EnabledOnJre(JRE.JAVA_8)
    void testOnJre8() {
        assumeTrue("1.8.0".equalsIgnoreCase(System.getenv("java.version")));
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "USER", matches = "renatorodrigues")
    void testOnEnvVariable() {
        assertTrue("renatorodrigues".equalsIgnoreCase(System.getenv("USER")));
    }
}