package nextstep.courses.domain;

import nextstep.courses.domain.model.*;
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
            null, ProgressStatus.ACTIVE, RegistrationStatus.OPEN, 100_000L, 10, 1L,
            LocalDateTime.parse("2025-04-21T00:00"), null);

    public static Session createFreeSession(RegistrationStatus status) {
        return Session.createFreeSession(CourseTest.createCourse(), LocalDateTime.now(), LocalDateTime.now().plusMonths(1), null, ProgressStatus.SCHEDULED, status, NsUserTest.JAVAJIGI);
    }

    public static Session createPaidSession(Long price, int capacity) {
        return Session.createPaidSession(CourseTest.createCourse(), new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusMonths(1)), null, ProgressStatus.ACTIVE, RegistrationStatus.OPEN, price, capacity, NsUserTest.JAVAJIGI);
    }

    public static Session createPaidSession(Course course, Long price, int capacity) {
        return Session.createPaidSession(course, new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusMonths(1)), null, ProgressStatus.ACTIVE, RegistrationStatus.OPEN, price, capacity, NsUserTest.JAVAJIGI);
    }

    @Test
    @DisplayName("선발절차가 있는 강의는 바로 수강할 수 없다.")
    void createSessionWithSelectionProcess() {
        Course course = CourseTest.createCourseWithSelection();
        Session session = new Session(null, course, new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusMonths(1)),
                Collections.emptyList(),
                ProgressStatus.ACTIVE, RegistrationStatus.OPEN, 0L, new Registration(1), 1L, LocalDateTime.now(), LocalDateTime.now());

        assertThatCode(() -> session.apply(NsUserTest.JAVAJIGI)).doesNotThrowAnyException();
        assertThat(session.getStudentStatus(NsUserTest.JAVAJIGI)).isEqualTo(ApplicantStatus.APPLIED);
    }

    @Test
    @DisplayName("선발절차가 있는 강의에 수강 신청을 할 수 있다.")
    void createSessionAndEnrollWithSelectionProcess() {
        Course course = CourseTest.createCourseWithSelection();
        Session session = new Session(null, course, new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusMonths(1)),
                Collections.emptyList(),
                ProgressStatus.ACTIVE, RegistrationStatus.OPEN, 0L, new Registration(1), 1L, LocalDateTime.now(), LocalDateTime.now());

        assertThatCode(() -> session.apply(NsUserTest.JAVAJIGI)).doesNotThrowAnyException();
        assertThat(session.getStudentStatus(NsUserTest.JAVAJIGI)).isEqualTo(ApplicantStatus.APPLIED);
    }

    @Test
    @DisplayName("선발절차가 없는 강의에 수강 신청하면 바로 등록된다.")
    void createSessionAndEnrollWithEnrollmentProcess() {
        Session session = SessionTest.createFreeSession(RegistrationStatus.OPEN);
        session.apply(NsUserTest.JAVAJIGI);
        assertThat(session.getStudentStatus(NsUserTest.JAVAJIGI)).isEqualTo(ApplicantStatus.APPROVED);
    }

    @Test
    @DisplayName("강의는 수강 인원을 선발한다.")
    void selectStudents() {
        Course course = CourseTest.createCourseWithSelection();
        Session session = new Session(null, course, new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusMonths(1)),
                Collections.emptyList(),
                ProgressStatus.ACTIVE, RegistrationStatus.OPEN, 0L, new Registration(1), 1L, LocalDateTime.now(), LocalDateTime.now());

        session.apply(NsUserTest.JAVAJIGI);
        session.select(NsUserTest.JAVAJIGI);
        assertThat(session.getStudentStatus(NsUserTest.JAVAJIGI)).isEqualTo(ApplicantStatus.SELECTED);
    }


    @Test
    @DisplayName("무료 강의는 최대 수강 인원 제한이 없다.")
    void createFreeSession() {
        Session session = createPaidSession(0L, Integer.MAX_VALUE);
        assertThatCode(() -> session.apply(NsUserTest.JAVAJIGI)).doesNotThrowAnyException();
        assertThat(session.getStudentStatus(NsUserTest.JAVAJIGI)).isEqualTo(ApplicantStatus.APPROVED);
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
                ProgressStatus.ACTIVE, RegistrationStatus.OPEN, 100_000L, new Registration(3), 1L, LocalDateTime.now(), LocalDateTime.now());
        assertThat(session.getImages()).hasSize(2);
    }

    @Test
    @DisplayName("강의 수강신청은 모집 상태가 모집중일 때 가능하다.")
    void registerOpenSession() {
        assertThatCode(() -> createFreeSession(RegistrationStatus.OPEN).apply(NsUserTest.JAVAJIGI)).doesNotThrowAnyException();
        assertThatThrownBy(() -> createFreeSession(RegistrationStatus.CLOSE).apply(NsUserTest.JAVAJIGI)).isInstanceOf(IllegalArgumentException.class);
    }

}
