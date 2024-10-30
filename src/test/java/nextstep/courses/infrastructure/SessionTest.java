package nextstep.courses.infrastructure;

import nextstep.courses.domain.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SessionTest {

    private Session session;

    @BeforeEach
    void setUp() {
        session = new Session();
    }

    @Test
    void 커버_이미지_확인() {
        assertTrue(session.isValidCoverImage());
    }

    @Test
    void 수강신청_가능여부_확인__인원초과_확인() {
//        session.setEnrollmentCount(0);
//        assertTrue(session.isFull());
        assertTrue(session.canEnroll());
    }

    @Test
    void 수강신청_가능여부_확인__강의_수강료_확인() {
//        session.setTuitionFee(20000);
//        assertTrue(session.isTuitionPaid(20000)); //수강생이 결제한 금액
        assertTrue(session.canEnroll());
    }

    @Test
    void 수강신청_가능여부_확인__강의_상태_모집중() {
//        assertTrue(session.isRecruiting());
        assertTrue(session.canEnroll());
    }

}
