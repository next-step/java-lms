package nextstep.session.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static nextstep.session.domain.fixture.SessionImageFixture.sessionImageFixture;
import static nextstep.users.domain.fixture.NsUserFixture.STUDENT_1;
import static nextstep.users.domain.fixture.NsUserFixture.STUDENT_2;
import static nextstep.users.domain.fixture.NsUserFixture.STUDENT_3;
import static org.assertj.core.api.Assertions.assertThat;

class FreeSessionTest {
    LocalDate today;

    @BeforeEach
    void setup() {
        // given
        today = LocalDate.now();
    }

    @Test
    @DisplayName("수강신청 / 정원 무제한 / 성공")
    void 수강신청_정원무제한_성공() {
        // given
        FreeSession session = FreeSession.create(1L, today, today.plusDays(1), sessionImageFixture);
        session.changeRecruit(SessionRecruitStatus.OPEN);

        // when
        session.enroll(STUDENT_1);
        session.enroll(STUDENT_2);
        session.enroll(STUDENT_3);

        // then
        assertThat(session.enrolledNumber()).isEqualTo(3);
    }

}