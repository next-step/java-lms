package nextstep.courses.infrastructure;

import nextstep.courses.domain.FreeSession;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FreeSessionTest {

    private FreeSession freeSession;

    @Test
    void 수강신청_가능여부_확인() {
        freeSession = new FreeSession();
        assertTrue(freeSession.canEnroll(new Payment(0L)));
    }
}
