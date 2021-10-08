package guru.springframework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MockTest {

    @Mock
    Map<String, Object> injectedMockMap;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void inlineMock() {
        Map<?,?> map = Mockito.mock(Map.class);
        assertEquals(0, map.size());
    }

    @Test
    void injectedMock() {
        assertEquals(0, injectedMockMap.size());
    }
}
