package nextstep.courses.infrastructure;

import nextstep.courses.domain.PaidSession;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PaidSessionTest {

    private PaidSession paidSession;

    @Test
    void 수강신청_가능여부_확인__강의_수강료_확인() {
        paidSession = new PaidSession(3, 2, 20000, 20000);
        assertTrue(paidSession.canEnroll());
        paidSession = new PaidSession(3, 2, 20000, 15000);
    }

    @Test
    void 수강신청_가능여부_확인__인원초과_확인() {

        paidSession = new PaidSession(3, 2, 20000, 20000);
        assertTrue(paidSession.canEnroll());
        paidSession = new PaidSession(2, 2, 20000, 20000);
        assertFalse(paidSession.canEnroll());
    }

}
