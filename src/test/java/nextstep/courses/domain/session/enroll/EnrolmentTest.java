package nextstep.courses.domain.session.enroll;

import nextstep.courses.domain.session.student.SelectionStatus;
import nextstep.courses.domain.session.student.SessionStudent;
import nextstep.courses.domain.session.student.SessionStudents;
import nextstep.courses.dto.EnrolmentInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.session.PayType.*;
import static nextstep.courses.domain.session.SessionStatus.*;
import static nextstep.courses.domain.session.enroll.RecruitingStatus.*;
import static org.assertj.core.api.Assertions.*;

class EnrolmentTest {

    @DisplayName("수강 신청 상태가 모집중이면 수강생을 등록한다.")
    @Test
    void enroll() {
        // given
        Enrolment enrolment = Enrolment.fromFreeSession(new SessionStudents(), PROGRESS, RECRUITING_ON);
        EnrolmentInfo enrolmentInfo = new EnrolmentInfo(1L, 1L, 1000L);

        // when
        SessionStudent student = enrolment.enroll(enrolmentInfo);

        // then
        assertThat(student.getSessionId()).isEqualTo(1L);
    }

    @DisplayName("강의 모집 상태가 모집중이 아니면 예외를 발생시킨다.")
    @Test
    void validateRecruitingStatus() {
        // given
        Enrolment enrolment = Enrolment.fromFreeSession(new SessionStudents(), PROGRESS, RECRUITING_OFF);
        EnrolmentInfo enrolmentInfo = new EnrolmentInfo(1L, 1L, 1000L);

        // when & then
        assertThatThrownBy(() -> enrolment.enroll(enrolmentInfo)).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("해당 강의는 현재 모집중이 아닙니다.");
    }

    @DisplayName("강의 상태가 진행 중이 아니면 예외를 발생시킨다.")
    @Test
    void validateSessionStatus() {
        // given
        Enrolment enrolment = Enrolment.fromFreeSession(new SessionStudents(), PREPARE, RECRUITING_ON);
        EnrolmentInfo enrolmentInfo = new EnrolmentInfo(1L, 1L, 1000L);

        // when & then
        assertThatThrownBy(() -> enrolment.enroll(enrolmentInfo)).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("해당 강의는 현재 준비중입니다.");
    }

    @DisplayName("유료 강의인 경우 결제 금액이 강의 금액과 일치하지 않으면 예외를 발생시킨다.")
    @Test
    void validatePayAmount() {
        // given
        Enrolment enrolment = Enrolment.fromPaySession(new SessionStudents(), PROGRESS, RECRUITING_ON, 10000L, 100);
        EnrolmentInfo enrolmentInfo = new EnrolmentInfo(1L, 1L, 12000L);

        // when & then
        assertThatThrownBy(() -> enrolment.enroll(enrolmentInfo)).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("결제 금액이 강의 금액과 일치하지 않습니다. 강의 금액 :: 10,000원");
    }

    @DisplayName("유료 강의인 경우 수강 신청 인원이 제한 인원수를 넘으면 예외를 발생시킨다.")
    @Test
    void validateCapacity() {
        // given
        SessionStudents sessionStudents = new SessionStudents();
        sessionStudents.add(new SessionStudent(1L, 1L, SelectionStatus.APPROVAL));
        sessionStudents.add(new SessionStudent(1L, 2L, SelectionStatus.APPROVAL));
        sessionStudents.add(new SessionStudent(1L, 3L, SelectionStatus.APPROVAL));

        Enrolment enrolment = Enrolment.fromPaySession(sessionStudents, PROGRESS, RECRUITING_ON, 10000L, 3);
        EnrolmentInfo enrolmentInfo = new EnrolmentInfo(1L, 4L, 10000L);

        // when & then
        assertThatThrownBy(() -> enrolment.enroll(enrolmentInfo)).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("현재 수강 가능한 모든 인원수가 채워졌습니다.");
    }
}