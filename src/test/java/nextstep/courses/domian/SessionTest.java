package nextstep.courses.domian;

import nextstep.courses.domain.Amount;
import nextstep.courses.domain.Apply;
import nextstep.courses.domain.CoverImage;
import nextstep.courses.domain.CoverImageFileName;
import nextstep.courses.domain.CoverImagePixel;
import nextstep.courses.domain.CoverImageSize;
import nextstep.courses.domain.CoverImages;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionDuration;
import nextstep.courses.domain.SessionEnrolment;
import nextstep.courses.domain.SessionStatusType;
import nextstep.courses.domain.SessionStudent;
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
    @DisplayName("강의는 싱청하면 신청대기 상태를 가진다.")
    void enrolment() {
        Students students = new Students(new ArrayList<>(List.of(NsUserTest.SANJIGI, NsUserTest.JAVAJIGI)));
        Session session = createFreeSession(students);

        NsUser newUser = createNewUser();
        Apply actual = session.enrolment(newUser, 0L);

        assertThat(actual).isEqualTo(Apply.defaultApply(session, newUser));
    }

    @Test
    @DisplayName("유료 강의의 경우 강의 금액과 결제 금액이 다르다면 오류가 발생한다.")
    void enrolment_유료_금액불일치() {
        Students students = new Students(new ArrayList<>(List.of(NsUserTest.SANJIGI, NsUserTest.JAVAJIGI)));
        Session session = createPaySession(students, 3, SessionStatusType.ONGOING, 30_000L);

        NsUser newUser = createNewUser();

        assertThatThrownBy(() -> session.enrolment(newUser, 20_000L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("결제금액과 강의금액이 맞지 않습니다.");
    }

    @Test
    @DisplayName("유료 강의의 경우 강의 인원수가 꽉 차있다면 신청이 불가능하다.")
    void enrolment_유료_인원수초과() {
        Students students = new Students(new ArrayList<>(List.of(NsUserTest.SANJIGI, NsUserTest.JAVAJIGI)));
        Session session = createPaySession(students, 2, SessionStatusType.ONGOING, 30_000L);

        NsUser newUser = createNewUser();

        assertThatThrownBy(() -> session.enrolment(newUser, 20000L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("강의 최대 수강 인원이 모두 찼습니다.");
    }

    @Test
    @DisplayName("강의가 모집중이지 않은 경우 강의 신청시 오류가 발생한다.")
    void enrolment_not_recruitment() {
        Students students = new Students(new ArrayList<>(List.of(NsUserTest.SANJIGI, NsUserTest.JAVAJIGI)));
        Session session = createPaySession(students, 3, SessionStatusType.END, 30_000L);

        NsUser newUser = createNewUser();

        assertThatThrownBy(() -> session.enrolment(newUser, 20000L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 강의가 모집중인 상태가 아닙니다.");
    }

    private NsUser createNewUser() {
        return new NsUser(3L, "test", "test", "test", "test");
    }

    private Session createPaySession(Students students, int maxStudentCount, SessionStatusType sessionStatusType, Long amount) {
        SessionDuration sessionDuration = new SessionDuration(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3));
        CoverImage coverImage = new CoverImage(1L, new CoverImageFileName("test.png"), new CoverImageSize(300), new CoverImagePixel(300, 200));
        SessionStudent sessionStudent = new SessionStudent(students, maxStudentCount);
        SessionEnrolment sessionEnrolment = new SessionEnrolment(sessionStudent, sessionStatusType, new Amount(amount), false);

        return new Session(1L, sessionDuration, sessionEnrolment, new CoverImages(List.of(coverImage)));
    }

    private Session createFreeSession(Students students) {
        SessionDuration sessionDuration = new SessionDuration(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3));
        CoverImage coverImage = new CoverImage(1L, new CoverImageFileName("test.png"), new CoverImageSize(300), new CoverImagePixel(300, 200));
        SessionStudent sessionStudent = new SessionStudent(students);
        SessionEnrolment sessionEnrolment = new SessionEnrolment(sessionStudent, SessionStatusType.ONGOING, new Amount(0L), true);

        return new Session(1L, sessionDuration, sessionEnrolment, new CoverImages(List.of(coverImage)));
    }
}
