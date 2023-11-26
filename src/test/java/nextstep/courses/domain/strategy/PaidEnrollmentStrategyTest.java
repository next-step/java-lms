package nextstep.courses.domain.strategy;

import nextstep.courses.domain.FileSize;
import nextstep.courses.domain.ImageSize;
import nextstep.courses.domain.Period;
import nextstep.courses.domain.Thumbnail;
import nextstep.courses.exception.SessionFullException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PaidEnrollmentStrategyTest {

    @Test
    @DisplayName("유료 강의 최대 인원까지 차오른 강의를 신청하면 예외 처리 한다")
    void isSupport() {
        Period period = new Period(LocalDate.of(2023, 11, 24),
                LocalDate.of(2023, 11, 24));
        Thumbnail thumbnail = new Thumbnail("테스트",
                "/home/test.png",
                new FileSize(1024L),
                new ImageSize(300L,
                        200L));
        PaidEnrollmentStrategy paidEnrollmentStrategy = new PaidEnrollmentStrategy(0);

        assertThrows(SessionFullException.class, () -> paidEnrollmentStrategy.enrol(new NsUser()),
                "수강 신청 인원이 마감 되었습니다.");
    }

}
