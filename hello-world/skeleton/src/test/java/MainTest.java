import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class MainTest {

    private static final String EXPECTED = "Hello, World!";

    /**
     * 표준 출력을 가로채 Main.main()이 출력한 내용을 반환한다.
     */
    private String runMain() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        try {
            System.setOut(new PrintStream(buffer, true, StandardCharsets.UTF_8));
            Main.main(new String[0]);
        } finally {
            System.setOut(originalOut);
        }

        return buffer.toString(StandardCharsets.UTF_8);
    }

    @Test
    void 표준_출력으로_기대_문자열을_출력한다() {
        assertEquals(EXPECTED, runMain().trim());
    }

    @Test
    void 아무것도_출력하지_않으면_안_된다() {
        assertFalse(runMain().isBlank(), "표준 출력이 비어 있습니다.");
    }

    @Test
    void 기대_문자열_외의_추가_출력이_없다() {
        String[] lines = runMain().trim().split("\\R");

        assertEquals(1, lines.length, "출력은 한 줄이어야 합니다.");
        assertEquals(EXPECTED, lines[0]);
    }

    @Test
    void 실행_중_예외가_발생하지_않는다() {
        assertDoesNotThrow(this::runMain);
    }
}
