package nextstep.courses.domian;

import nextstep.courses.domain.Amount;
import nextstep.courses.domain.ApplyStatus;
import nextstep.courses.domain.RecruitmentStatusType;
import nextstep.courses.domain.SessionEnrolment;
import nextstep.courses.domain.SessionStatus;
import nextstep.courses.domain.SessionStatusType;
import nextstep.courses.domain.SessionStudents;
import nextstep.courses.domain.Student;
import nextstep.courses.domain.Students;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class SessionEnrolmentTest {

    @Test
    @DisplayName("1명의 승인된 학생이 있는강의에서 새로운 학생이 정상적으로 신청할 경우 수강신청 인원은 2명이된다.")
    void enrolment_유료() {

        Students students = new Students(new ArrayList<>(List.of(new Student(NsUserTest.JAVAJIGI, ApplyStatus.APPROVAL))));
        SessionStudents sessionStudents = new SessionStudents(students, 2);
        SessionEnrolment sessionEnrolment = new SessionEnrolment(sessionStudents, new SessionStatus(SessionStatusType.READY, RecruitmentStatusType.RECRUITING), new Amount(10_000L), false);

        sessionEnrolment.enrolment(NsUserTest.SANJIGI, 10_000L);
        int actual = sessionEnrolment.applyStudentsCount();

        assertThat(actual).isEqualTo(2);
    }

    @Test
    @DisplayName("강의가 모집중이지 않은 경우 오류가 발생한다.")
    void enrolment_종료() {
        SessionStudents sessionStudents = new SessionStudents(new Students());
        Amount amount = new Amount();

        SessionEnrolment sessionEnrolment = new SessionEnrolment(sessionStudents, SessionStatusType.END, amount, true);
        Assertions.assertThatThrownBy(() -> sessionEnrolment.enrolment(NsUserTest.JAVAJIGI, 0L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 강의가 모집중인 상태가 아닙니다.");
    }

    @Test
    @DisplayName("유료강의의 경우 결제 금액이 일치하지 않으면 오류가 발생한다.")
    void enrolment_금액_불일치() {
        SessionStudents sessionStudents = new SessionStudents(new Students(), 3);
        Amount amount = new Amount(30_000L);

        SessionEnrolment sessionEnrolment = new SessionEnrolment(sessionStudents, SessionStatusType.ONGOING, amount, false);;

        Assertions.assertThatThrownBy(() -> sessionEnrolment.enrolment(NsUserTest.JAVAJIGI, 20_000L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("결제금액과 강의금액이 맞지 않습니다.");
    }

    @Test
    @DisplayName("유료 강의의 경우 수강인원이 모두 찬 경우 오류가 발생한다.")
    void enrolment_full_student() {
        SessionStudents sessionStudents = new SessionStudents(new Students(List.of(new Student(NsUserTest.JAVAJIGI, ApplyStatus.APPROVAL))), 1);
        Amount amount = new Amount(30_000L);

        SessionEnrolment sessionEnrolment = new SessionEnrolment(sessionStudents, SessionStatusType.ONGOING, amount, false);;

        Assertions.assertThatThrownBy(() -> sessionEnrolment.enrolment(NsUserTest.JAVAJIGI, 30_000L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("강의 최대 수강 인원이 모두 찼습니다.");
    }
}
