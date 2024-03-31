package nextstep.courses.domain;

import static nextstep.courses.domain.SessionCoverImageTest.SAMPLE_COVER_IMAGE;
import static nextstep.qna.domain.TestFixtures.FIXED_DATE_TIME;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.courses.InvalidSessionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SessionTest {

    @Test
    @DisplayName("강의 데이터가 유효하면 성공적으로 생성된다")
    void new_success() {
        assertThatNoException()
            .isThrownBy(
                () ->  new Session(
                    FIXED_DATE_TIME, FIXED_DATE_TIME.plusDays(1),
                    SAMPLE_COVER_IMAGE,
                    FIXED_DATE_TIME
                )
            );
    }

    @Test
    @DisplayName("강의 종료일이 시작일보다 앞서면 예외가 발생한다")
    void new_fail_for_invalid_date() {
        assertThatThrownBy(
            () ->  new Session(
                FIXED_DATE_TIME.plusDays(1), FIXED_DATE_TIME,
                SAMPLE_COVER_IMAGE,
                FIXED_DATE_TIME
            )
        ).isInstanceOf(InvalidSessionException.class);
    }
}
