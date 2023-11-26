package nextstep.courses.domian;

import nextstep.courses.CannotRecruitException;
import nextstep.courses.domain.SessionAmount;
import nextstep.courses.domain.SessionEnrolmentInfo;
import nextstep.courses.domain.SessionStatusType;
import nextstep.courses.domain.SessionStudentInfo;
import nextstep.courses.domain.Students;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class SessionEnrolmentInfoTest {

    @Test
    @DisplayName("강의가 모집중이며, 3명의 정원인 강의에 2명이 존재하고, 강의금액에 맞는 금액을 지불한 경우 정상 적으로 신청이되고, 모집인원은 꽉차게 된다.")
    void enrolment_유료() {
        Students students = new Students(new ArrayList<>(List.of(NsUserTest.SANJIGI, NsUserTest.JAVAJIGI)));
        SessionStudentInfo sessionStudentInfo = new SessionStudentInfo(students, 3);
        SessionAmount sessionAmount = new SessionAmount(30_000L);
        NsUser newUser = new NsUser(3L, "test", "test", "test", "test");

        SessionEnrolmentInfo sessionEnrolmentInfo = new SessionEnrolmentInfo(sessionStudentInfo, SessionStatusType.RECRUITMENT, sessionAmount, false);
        sessionEnrolmentInfo.payEnrolment(newUser, 30_000L);

        Assertions.assertThat(sessionStudentInfo.isMaxStudents()).isTrue();
    }

    @Test
    @DisplayName("강의가 모집중이며 무료강의인 경우 인원 제한없이 수강이 가능하다.")
    void enrolment_무료() {
        Students students = new Students(new ArrayList<>(List.of(NsUserTest.SANJIGI, NsUserTest.JAVAJIGI)));
        SessionStudentInfo sessionStudentInfo = new SessionStudentInfo(students);
        SessionAmount sessionAmount = new SessionAmount();
        NsUser newUser = new NsUser(3L, "test", "test", "test", "test");

        SessionEnrolmentInfo sessionEnrolmentInfo = new SessionEnrolmentInfo(sessionStudentInfo, SessionStatusType.RECRUITMENT, sessionAmount, true);
        sessionEnrolmentInfo.freeEnrolment(newUser);
    }

    @Test
    @DisplayName("강의가 모집중이지 않은 경우 오류가 발생한다.")
    void enrolment_종료() {
        Students students = new Students(new ArrayList<>(List.of(NsUserTest.SANJIGI, NsUserTest.JAVAJIGI)));
        SessionStudentInfo sessionStudentInfo = new SessionStudentInfo(students);
        SessionAmount sessionAmount = new SessionAmount();
        NsUser newUser = new NsUser(3L, "test", "test", "test", "test");

        SessionEnrolmentInfo sessionEnrolmentInfo = new SessionEnrolmentInfo(sessionStudentInfo, SessionStatusType.END, sessionAmount, true);
        Assertions.assertThatThrownBy(() -> sessionEnrolmentInfo.freeEnrolment(newUser))
                .isInstanceOf(CannotRecruitException.class)
                .hasMessage("현재 강의가 모집중인 상태가 아닙니다.");
    }

    @Test
    @DisplayName("유료강의의 경우 결제 금액이 일치하지 않으면 오류가 발생한다.")
    void enrolment_금액_불일치() {
        Students students = new Students(new ArrayList<>(List.of(NsUserTest.SANJIGI, NsUserTest.JAVAJIGI)));
        SessionStudentInfo sessionStudentInfo = new SessionStudentInfo(students, 3);
        SessionAmount sessionAmount = new SessionAmount(30_000L);
        NsUser newUser = new NsUser(3L, "test", "test", "test", "test");

        SessionEnrolmentInfo sessionEnrolmentInfo = new SessionEnrolmentInfo(sessionStudentInfo, SessionStatusType.RECRUITMENT, sessionAmount, false);;
        Assertions.assertThatThrownBy(() -> sessionEnrolmentInfo.payEnrolment(newUser, 20_000L))
                .isInstanceOf(CannotRecruitException.class)
                .hasMessage("결제금액과 강의금액이 맞지 않습니다.");
    }

    @Test
    @DisplayName("유료 강의의 경우 수강인원이 모두 찬 경우 오류가 발생한다.")
    void enrolment_full_student() {
        Students students = new Students(new ArrayList<>(List.of(NsUserTest.SANJIGI, NsUserTest.JAVAJIGI)));
        SessionStudentInfo sessionStudentInfo = new SessionStudentInfo(students, 2);
        SessionAmount sessionAmount = new SessionAmount(30_000L);
        NsUser newUser = new NsUser(3L, "test", "test", "test", "test");

        SessionEnrolmentInfo sessionEnrolmentInfo = new SessionEnrolmentInfo(sessionStudentInfo, SessionStatusType.RECRUITMENT, sessionAmount, false);;
        Assertions.assertThatThrownBy(() -> sessionEnrolmentInfo.payEnrolment(newUser, 30_000L))
                .isInstanceOf(CannotRecruitException.class)
                .hasMessage("강의 최대 수강 인원이 모두 찼습니다.");
    }
}
