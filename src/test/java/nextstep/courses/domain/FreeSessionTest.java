package nextstep.courses.domain;

import nextstep.courses.domain.Image.CoverImage;
import nextstep.courses.exception.SessionStateException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FreeSessionTest {
    @Test
    @DisplayName("강의 수강신청은 강의 상태가 모집중일 때만 가능하다.")
    void 수강신청_모집중_에러() {

        LocalDate startDate = LocalDate.of(2023, 12, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 29);
        FreeSession freeSession = new FreeSession(
                new CoverImage(1000_000, "gif", 300, 200),
                startDate, endDate,
                SessionState.PREPARING
        );

        assertThatThrownBy(() -> freeSession.apply(NsUserTest.SANJIGI))
                .isInstanceOf(SessionStateException.class);


    }
}
