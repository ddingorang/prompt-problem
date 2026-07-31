import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PhoneNumberFormatterTest {

    private final PhoneNumberFormatter formatter = new PhoneNumberFormatter();

    @Test
    void 지역번호_2자리와_국번_3자리를_마스킹한다() {
        assertEquals("02-***-4567", formatter.maskPhoneNumber("02-123-4567"));
    }

    @Test
    void 지역번호_2자리와_국번_4자리를_마스킹한다() {
        assertEquals("02-****-5678", formatter.maskPhoneNumber("02-1234-5678"));
    }

    @Test
    void 휴대전화_번호를_마스킹한다() {
        assertEquals("010-****-5678", formatter.maskPhoneNumber("010-1234-5678"));
    }

    @Test
    void 인터넷전화_번호를_마스킹한다() {
        assertEquals("070-****-5432", formatter.maskPhoneNumber("070-9876-5432"));
    }

    @Test
    void 명세에_없는_구간_길이에도_같은_규칙을_적용한다() {
        assertEquals("031-***-7890", formatter.maskPhoneNumber("031-456-7890"));
        assertEquals("0505-****-5678", formatter.maskPhoneNumber("0505-1234-5678"));
    }
}
