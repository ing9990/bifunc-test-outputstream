import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;


public class BiTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @DisplayName("String, Integer 를 받아 String을 반환하는 BiFunction")
    @Test
    void concat_with_bifunction() {
        BiFunction<String, Integer, String> concat = (s, i) -> s + " is " + i + " years old";

        String result = concat.apply("Taewoo", 20);

        Assertions.assertEquals(result, "Taewoo is 20 years old");
    }

    @DisplayName("String, Integer 를 아무것도 반환하지 않는 BiConsumer")
    @Test
    void concat_with_biconsumer() {
        // Given
        BiConsumer<String, Integer> print = (s1, s2) -> System.out.println(s1 + " is " + s2 + " years old");
        String name = "Taewoo";
        int age = 21;

        // When
        print.accept(name, age);

        // Then
        Assertions.assertEquals(outputStreamCaptor.toString().trim(), "Taewoo is 21 years old");
    }
}

