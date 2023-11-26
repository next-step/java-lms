package nextstep.courses.domain.strategy;

import nextstep.courses.domain.Amount;
import nextstep.courses.domain.Student;
import nextstep.courses.exception.SessionFullException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PaidEnrollmentStrategyTest {

    @Test
    @DisplayName("유료 강의 최대 인원까지 차오른 강의를 신청하면 예외 처리 한다")
    void isSupport() {
        PaidEnrollmentStrategy paidEnrollmentStrategy = new PaidEnrollmentStrategy(0, new Amount(10000L));

        assertThrows(SessionFullException.class, () -> paidEnrollmentStrategy.enroll(new Student(), 10000L),
                "수강 신청 인원이 마감 되었습니다.");
    }

}
