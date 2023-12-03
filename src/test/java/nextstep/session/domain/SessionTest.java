package nextstep.session.domain;

import nextstep.users.domain.fixture.NsUserFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static nextstep.session.domain.fixture.SessionImageFixture.sessionImageFixture;
import static nextstep.users.domain.fixture.NsUserFixture.STUDENT_1;
import static nextstep.users.domain.fixture.NsUserFixture.STUDENT_2;
import static nextstep.users.domain.fixture.NsUserFixture.STUDENT_3;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTest {
    LocalDate today;

    @BeforeEach
    void setup() {
        // given
        today = LocalDate.now();
    }

    @Test
    @DisplayName("수강신청 / 모집 중 / 수강 성공")
    void 수강신청_모집중_성공() {
        // given
        Session session = Session.create(1, 1L, today, today.plusDays(1), sessionImageFixture);
        session.changeStatus(SessionStatus.RECRUITING);

        // when
        session.enroll(STUDENT_1);
        session.enroll(NsUserFixture.STUDENT_2);

        // then
        assertThat(session.getStudents()).hasSize(2);
    }

    @Test
    @DisplayName("수강신청 / 모집 중 아님 / IllegalStateException")
    void 수강신청_모집중아님_실패() {
        // given
        Session session = Session.create(1, 1L, today, today.plusDays(1), sessionImageFixture);

        // expect
        assertThatThrownBy(() -> session.enroll(STUDENT_1))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("수강신청 / 무료 정원 무제한 / 성공")
    void 수강신청_무료_성공() {
        // given
        Session session = Session.create(1, 1L, today, today.plusDays(1), sessionImageFixture, SessionType.FREE, null);
        session.changeStatus(SessionStatus.RECRUITING);

        // when
        session.enroll(STUDENT_1);
        session.enroll(STUDENT_2);
        session.enroll(STUDENT_3);

        // then
        assertThat(session.getStudents()).hasSize(3);
    }

    @Test
    @DisplayName("수강신청 / 유료 정원 2명 / 성공")
    void 수강신청_유료_성공() {
        // given
        Session session = Session.create(1, 1L, today, today.plusDays(1), sessionImageFixture, SessionType.PAID, 2);
        session.changeStatus(SessionStatus.RECRUITING);

        // when
        session.enroll(STUDENT_1);
        session.enroll(STUDENT_2);

        // then
        assertThat(session.getStudents()).hasSize(2);
    }

    @Test
    @DisplayName("수강신청 / 유료 정원 1명, 2명 신청 / IllegalStateException")
    void 수강신청_유료_정원초과_실패() {
        // given
        Session session = Session.create(1, 1L, today, today.plusDays(1), sessionImageFixture, SessionType.PAID, 1);
        session.changeStatus(SessionStatus.RECRUITING);

        // when
        session.enroll(STUDENT_1);

        // then
        assertThatThrownBy(() -> session.enroll(STUDENT_2))
                .isInstanceOf(IllegalStateException.class);
    }
}