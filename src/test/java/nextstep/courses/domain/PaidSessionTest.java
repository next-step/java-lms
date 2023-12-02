package nextstep.courses.domain;

import nextstep.courses.exception.OverMaxStudentsException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PaidSessionTest {
    private static final LocalDate START_DATE = LocalDate.of(2023, 12, 01);
    private static final LocalDate END_DATE = LocalDate.of(2023, 12, 03);

    @Test
    @DisplayName("유료 강의 수강 신청 시 최대 수강 인원을 초과하면 예외를 던진다.")
    void register_over_students() {
        Session paidSession = new PaidSession(1L, CoverImageTest.IMAGE1, Status.NOT_OPEN, START_DATE, END_DATE, 1);

        paidSession.register(NsUserTest.JAVAJIGI);
        
        assertThatThrownBy(() -> paidSession.register(NsUserTest.SANJIGI))
                .isInstanceOf(OverMaxStudentsException.class);
    }
}
