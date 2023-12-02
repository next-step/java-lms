package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EnrolmentTest {

    @Test
    @DisplayName("강의 수강신청은 강의 상태가 준비중일 때 수강신청을 하면 예외가 발생한다.")
    void 강의_수강신청은_강의_상태가_준비중일_때_수강신청을_하면_예외가_발생한다() {
        Session session = new Session(SessionStatus.PREPARING);
        Enrolment enrolment = new Enrolment(session);
        assertThatThrownBy(enrolment::register)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("강의 수강신청은 강의 상태가 종료중일 때 수강신청을 하면 예외가 발생한다.")
    void 강의_수강신청은_강의_상태가_종료중일_때_수강신청을_하면_예외가_발생한다() {
        Session session = new Session(SessionStatus.CLOSE);
        Enrolment enrolment = new Enrolment(session);
        assertThatThrownBy(enrolment::register)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("강의 수강신청을 하면 강의 수강생 수가 1 증가한다.")
    void 강의_수강신청을_하면_강의_수강생_수가_1_증가한다() {
        Session session = new Session(SessionStatus.RECRUITING);
        Enrolment enrolment = new Enrolment(session);
        enrolment.register();
        assertThat(session.getStudentCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("유료_강의 수강신청을 하면 강의 수강생 수가 1 증가한다.")
    void 유료_강의_수강신청을_하면_강의_수강생_수가_1_증가한다() {
        Session session = new PaidSession(SessionStatus.RECRUITING);
        Enrolment enrolment = new Enrolment(session);
        enrolment.register();
        assertThat(session.getStudentCount()).isEqualTo(1);
    }
}
