package nextstep.courses.domian;

import nextstep.courses.domain.Amount;
import nextstep.courses.domain.ApplyStatus;
import nextstep.courses.domain.CoverImage;
import nextstep.courses.domain.CoverImageFileName;
import nextstep.courses.domain.CoverImagePixel;
import nextstep.courses.domain.CoverImageSize;
import nextstep.courses.domain.CoverImages;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionDuration;
import nextstep.courses.domain.SessionEnrolment;
import nextstep.courses.domain.SessionStatusType;
import nextstep.courses.domain.SessionStudents;
import nextstep.courses.domain.Student;
import nextstep.courses.domain.Students;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTest {

    @Test
    @DisplayName("무료 강의에 신청했다면, 최대 수강인원과 상관없이 없다.")
    void enrolment_무료() {
        Session session = createFreeSession(new Students(), 0);

        session.enrolment(NsUserTest.JAVAJIGI, 0L);
        boolean actual = session.isFullStudents();

        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("최대 1명의 수강인원인 강의에 이미 한명이 승인이 되어있다면, 수강신청인원은 모두 차게된다.")
    void enrolment_유료() {
        Students students = new Students(List.of(new Student(NsUserTest.JAVAJIGI, ApplyStatus.APPROVAL)));
        Session session = createPaySession(students, 1, SessionStatusType.READY, 10_000L);

        boolean actual = session.isFullStudents();

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("유료 강의의 경우 강의 금액과 결제 금액이 다르다면 오류가 발생한다.")
    void enrolment_유료_금액불일치() {
        Session session = createPaySession(new Students(), 3, SessionStatusType.ONGOING, 30_000L);

        NsUser newUser = createNewUser();

        assertThatThrownBy(() -> session.enrolment(newUser, 20_000L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("결제금액과 강의금액이 맞지 않습니다.");
    }

    @Test
    @DisplayName("유료 강의의 경우 강의 인원수가 꽉 차있다면 신청이 불가능하다.")
    void enrolment_유료_인원수초과() {
        Students students = new Students(new ArrayList<>(List.of(new Student(NsUserTest.JAVAJIGI, ApplyStatus.APPROVAL))));
        Session session = createPaySession(students, 1, SessionStatusType.ONGOING, 30_000L);

        NsUser newUser = createNewUser();

        assertThatThrownBy(() -> session.enrolment(newUser, 20000L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("강의 최대 수강 인원이 모두 찼습니다.");
    }

    @Test
    @DisplayName("강의가 모집중이지 않은 경우 강의 신청시 오류가 발생한다.")
    void enrolment_not_recruitment() {
        Session session = createPaySession(new Students(), 3, SessionStatusType.END, 30_000L);

        NsUser newUser = createNewUser();

        assertThatThrownBy(() -> session.enrolment(newUser, 20000L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 강의가 모집중인 상태가 아닙니다.");
    }

    @Test
    @DisplayName("최대 수강인원 1명인 강의에 학생이 신청한 강의를 강사가 승인 한다면, 해당 강의 수강인원은 모두 차게 된다.")
    void approve_success() {
        Students students = new Students(List.of(new Student(NsUserTest.JAVAJIGI, ApplyStatus.APPLYING)));
        Session session = createPaySession(students, 1, SessionStatusType.READY, 10_000L);
        session.updateInstructor(1L);

        session.approve(new Student(NsUserTest.JAVAJIGI), 1L);

        boolean actual = session.isFullStudents();

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("최대 수강인원 1명인 강의에 학생이 신청한 강의를 강사가 거절 한다면, 해당 강의 수강인원은 가득찬 상태가 아니다.")
    void refuse_success() {
        Students students = new Students(List.of(new Student(NsUserTest.JAVAJIGI)));
        Session session = createPaySession(students, 1, SessionStatusType.READY, 10_000L);
        session.updateInstructor(1L);

        session.refuse(new Student(NsUserTest.JAVAJIGI), 1L);

        boolean actual = session.isFullStudents();

        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("해당 강의의 강사가 아닌사람이 수강승인을 할 경우 오류가 발생한다.")
    void approve_fail() {
        Students students = new Students(List.of(new Student(NsUserTest.JAVAJIGI, ApplyStatus.APPLYING)));
        Session session = createPaySession(students, 1, SessionStatusType.READY, 10_000L);
        session.updateInstructor(1L);

        assertThatThrownBy(() -> session.approve(new Student(NsUserTest.JAVAJIGI), 2L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 강의의 강사만 가능합니다.");
    }

    @Test
    @DisplayName("해당 강의의 강사가 아닌사람이 수강거절을 할 경우 오류가 발생한다.")
    void refuse_fail() {
        Students students = new Students(List.of(new Student(NsUserTest.JAVAJIGI, ApplyStatus.APPLYING)));
        Session session = createPaySession(students, 1, SessionStatusType.READY, 10_000L);
        session.updateInstructor(1L);

        assertThatThrownBy(() -> session.refuse(new Student(NsUserTest.JAVAJIGI), 2L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 강의의 강사만 가능합니다.");
    }

    private NsUser createNewUser() {
        return new NsUser(3L, "test", "test", "test", "test");
    }

    private Session createPaySession(Students students, int maxStudentCount, SessionStatusType sessionStatusType, Long amount) {
        SessionDuration sessionDuration = new SessionDuration(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3));
        CoverImage coverImage = new CoverImage(1L, new CoverImageFileName("test.png"), new CoverImageSize(300), new CoverImagePixel(300, 200));
        SessionStudents sessionStudents = new SessionStudents(students, maxStudentCount);
        SessionEnrolment sessionEnrolment = new SessionEnrolment(sessionStudents, sessionStatusType, new Amount(amount), false);

        return new Session(1L, sessionDuration, sessionEnrolment, new CoverImages(List.of(coverImage)));
    }

    private Session createFreeSession(Students students, int maxStudentCount) {
        SessionDuration sessionDuration = new SessionDuration(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3));
        CoverImage coverImage = new CoverImage(1L, new CoverImageFileName("test.png"), new CoverImageSize(300), new CoverImagePixel(300, 200));
        SessionStudents sessionStudents = new SessionStudents(students, maxStudentCount);
        SessionEnrolment sessionEnrolment = new SessionEnrolment(sessionStudents, SessionStatusType.ONGOING, new Amount(0L), true);

        return new Session(1L, sessionDuration, sessionEnrolment, new CoverImages(List.of(coverImage)));
    }
}
