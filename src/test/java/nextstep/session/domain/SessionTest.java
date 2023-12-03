package nextstep.session.domain;

import nextstep.users.domain.fixture.NsUserFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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
    void 강의_생성() {
        // expect
        assertThat(Session.create(1, 1L, today, today.plusDays(1), "imageURL", SessionType.FREE))
                .isInstanceOf(Session.class);
    }

    @Test
    @DisplayName("수강신청 / 모집 중 / 수강 성공")
    void 수강신청_모집중_성공() {
        // given
        Session session = Session.create(1, 1L, today, today.plusDays(1), "imageURL", SessionType.FREE);
        session.changeStatus(SessionStatus.RECRUITING);

        // when
        session.enroll(NsUserFixture.STUDENT_1);
        session.enroll(NsUserFixture.STUDENT_2);

        // then
        Assertions.assertThat(session.getStudents()).hasSize(2);
    }

    @Test
    @DisplayName("수강신청 / 모집 중 아님 / IllegalStateException")
    void 수강신청_모집중아님_실패() {
        // given
        Session session = Session.create(1, 1L, today, today.plusDays(1), "imageURL", SessionType.FREE);

        // expect
        assertThatThrownBy(() -> session.enroll(NsUserFixture.STUDENT_1))
                .isInstanceOf(IllegalStateException.class);
    }
}