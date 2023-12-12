package nextstep.courses.domain.session.enroll;

import nextstep.courses.domain.session.student.Student;
import nextstep.courses.domain.session.student.Students;
import nextstep.courses.dto.EnrolmentInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.session.enroll.RecruitingStatus.*;
import static org.assertj.core.api.Assertions.*;

class EnrolmentTest {

    @DisplayName("수강 신청 상태가 모집중이면 수강생을 등록한다.")
    @Test
    void enroll() {
        // given
        Enrolment enrolment = new Enrolment(new Students(), RECRUITING_ON);
        EnrolmentInfo enrolmentInfo = new EnrolmentInfo(1L, 1L, 1000L);

        // when
        Student student = enrolment.enroll(enrolmentInfo);

        // then
        assertThat(student.getEnrolmentId()).isEqualTo(1L);
    }

    @DisplayName("수강 신청 상태가 모집중이 아니면 예외를 발생시킨다.")
    @Test
    void validateEnrollStatus() {
        // given
        Enrolment enrolment = new Enrolment(new Students(), RECRUITING_OFF);
        EnrolmentInfo enrolmentInfo = new EnrolmentInfo(1L, 1L, 1000L);

        // when & then
        assertThatThrownBy(() -> enrolment.enroll(enrolmentInfo)).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("해당 강의는 현재 모집중이 아닙니다.");
    }
}