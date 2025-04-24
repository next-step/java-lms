package nextstep.courses.domain;

import nextstep.courses.domain.model.*;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class SessionTest {
    public static final Session SESSION1 = new Session(1L, CourseTest.COURSE1,
            new SessionPeriod(LocalDateTime.parse("2025-04-21T00:00"), LocalDateTime.parse("2025-05-21T00:00")),
            null, SessionStatus.OPEN, RecruitmentStatus.ON, 100_000L, 10, 1L,
            LocalDateTime.parse("2025-04-21T00:00"), null);

    public static Session createFreeSession(RecruitmentStatus status) {
        return Session.createFreeSession(CourseTest.createCourse(), LocalDateTime.now(), LocalDateTime.now().plusMonths(1), null, SessionStatus.READY, status, NsUserTest.JAVAJIGI);
    }

    public static Session createPaidSession(Long price, int capacity) {
        return Session.createPaidSession(CourseTest.createCourse(), new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusMonths(1)), null, SessionStatus.OPEN, RecruitmentStatus.ON, price, capacity, NsUserTest.JAVAJIGI);
    }

    @Test
    @DisplayName("선발절차가 있는 강의는 바로 수강할 수 없다.")
    void createSessionWithSelectionProcess() {
        Course course = CourseTest.createCourseWithSelection();
        Session session = new Session(null, course, new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusMonths(1)),
                Collections.emptyList(),
                SessionStatus.OPEN, RecruitmentStatus.ON, 0L, new Students(1), 1L, LocalDateTime.now(), LocalDateTime.now());
        course.addSession(session);

        assertThatCode(() -> session.apply(NsUserTest.JAVAJIGI)).doesNotThrowAnyException();
        assertThat(session.getApplicants()).contains(NsUserTest.JAVAJIGI);
    }

    @Test
    @DisplayName("선발절차가 있는 강의에 수강 신청을 할 수 있다.")
    void createSessionAndEnrollWithSelectionProcess() {
        Course course = CourseTest.createCourseWithSelection();
        Session session = new Session(null, course, new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusMonths(1)),
                Collections.emptyList(),
                SessionStatus.OPEN, RecruitmentStatus.ON, 0L, new Students(1), 1L, LocalDateTime.now(), LocalDateTime.now());
        course.addSession(session);
        System.out.println("course = " + course.hasSelection());

        assertThatCode(() -> session.apply(NsUserTest.JAVAJIGI)).doesNotThrowAnyException();
        assertThat(session.getApplicants()).hasSize(1);
    }

    @Test
    @DisplayName("강의는 수강 인원을 선발한다.")
    void selectStudents() {
        Course course = CourseTest.createCourseWithSelection();
        Session session = new Session(null, course, new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusMonths(1)),
                Collections.emptyList(),
                SessionStatus.OPEN, RecruitmentStatus.ON, 0L, new Students(1), 1L, LocalDateTime.now(), LocalDateTime.now());
        course.addSession(session);

        session.apply(NsUserTest.JAVAJIGI);
        assertThat(session.select(NsUserTest.JAVAJIGI)).isEqualTo(1);
        assertThat(session.getSelected()).contains(NsUserTest.JAVAJIGI);
        assertThat(session.getApplicants()).isEmpty();
    }

    @Test
    @DisplayName("강의는 선발 절차에 따라 최대 수강 인원을 선발한다.")
    void selectStudentsWithCapacity() {
        Course course = CourseTest.createCourseWithSelection();
        Session session = new Session(null, course, new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusMonths(1)),
                Collections.emptyList(),
                SessionStatus.OPEN, RecruitmentStatus.ON, 0L, new Students(2), 1L, LocalDateTime.now(), LocalDateTime.now());
        course.addSession(session);

        session.apply(NsUserTest.JAVAJIGI);
        session.apply(NsUserTest.SANJIGI);
        SelectStrategy strategy = () -> true;
        assertThat(session.select(strategy)).isEqualTo(2);
    }


    @Test
    @DisplayName("강사는 선발된 인원에 대해서만 수강 승인이 가능해야 한다.")
    void selectStudentsWithSelectionProcess() {
        Course course = CourseTest.createCourseWithSelection();
        Session session = new Session(null, course, new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusMonths(1)),
                Collections.emptyList(),
                SessionStatus.OPEN, RecruitmentStatus.ON, 0L, new Students(1), 1L, LocalDateTime.now(), LocalDateTime.now());
        course.addSession(session);

        session.apply(NsUserTest.JAVAJIGI);
        session.apply(NsUserTest.SANJIGI);
        session.select(NsUserTest.JAVAJIGI);

        assertThatCode(() -> session.approve(NsUserTest.JAVAJIGI)).doesNotThrowAnyException();
        assertThat(session.getStudents().include(NsUserTest.JAVAJIGI)).isTrue();
        assertThat(session.getApplicants()).doesNotContain(NsUserTest.JAVAJIGI);
        assertThatThrownBy(() -> session.approve(NsUserTest.SANJIGI)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("강사는 수강신청한 사람 중 선발되지 않은 사람은 수강을 취소할 수 있어야 한다.")
    void cancelStudentsWithSelectionProcess() {
        Course course = CourseTest.createCourseWithSelection();
        Session session = new Session(null, course, new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusMonths(1)),
                Collections.emptyList(),
                SessionStatus.OPEN, RecruitmentStatus.ON, 0L, new Students(1), 1L, LocalDateTime.now(), LocalDateTime.now());
        course.addSession(session);

        session.apply(NsUserTest.JAVAJIGI);
        session.apply(NsUserTest.SANJIGI);
        session.select(NsUserTest.JAVAJIGI);

        assertThatThrownBy(() -> session.cancel(NsUserTest.JAVAJIGI)).isInstanceOf(IllegalArgumentException.class);
        assertThatCode(() -> session.cancel(NsUserTest.SANJIGI)).doesNotThrowAnyException();
        assertThat(session.getApplicants()).isEmpty();
    }

    @Test
    @DisplayName("무료 강의는 최대 수강 인원 제한이 없다.")
    void createFreeSession() {
        Session session = createPaidSession(0L, Integer.MAX_VALUE);
        assertThatCode(() -> session.apply(NsUserTest.JAVAJIGI)).doesNotThrowAnyException();
        assertThat(session.getStudents().include(NsUserTest.JAVAJIGI)).isTrue();
    }

    @Test
    @DisplayName("유료 강의는 최대 수강 인원 제한이 있다.")
    void createPaidSession() {
        Session session = createPaidSession(0L, 0);
        assertThatThrownBy(() -> session.apply(NsUserTest.JAVAJIGI)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("강의는 하나 이상의 커버 이미지를 가질 수 있다.")
    void haveOneOrMoreSessionImages() {
        Course course = CourseTest.createCourse();
        Session session = new Session(null, course, new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusMonths(1)),
                List.of(new SessionImage("path0", new byte[0]), new SessionImage("path1", new byte[1])),
                SessionStatus.OPEN, RecruitmentStatus.ON, 100_000L, new Students(3), 1L, LocalDateTime.now(), LocalDateTime.now());
        assertThat(session.getImages()).hasSize(2);
    }

    @Test
    @DisplayName("강의 수강신청은 모집 상태가 모집중일 때 가능하다.")
    void registerOpenSession() {
        assertThatCode(() -> createFreeSession(RecruitmentStatus.ON).apply(NsUserTest.JAVAJIGI)).doesNotThrowAnyException();
        assertThatThrownBy(() -> createFreeSession(RecruitmentStatus.OFF).apply(NsUserTest.JAVAJIGI)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("결제 정보는 Payment 객체에 담겨 반한된다.")
    void enrollAndGetPayment() {
        Session session = createPaidSession(800_000L, 1);
        NsUser user = NsUserTest.createNsUser(3L, 800_000L);
        session.apply(user);
        assertThat(session.getPayment(user).getAmount()).isEqualTo(800_000L);
    }

}
