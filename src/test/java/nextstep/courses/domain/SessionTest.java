package nextstep.courses.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {
    Session session;
    List<Student> students;

    @BeforeEach
    void setUp() {
        students = new ArrayList<>();
        session = Session.of(LocalDateTime.now(), LocalDateTime.now().plusYears(1), "https://ianUrl",
                SessionType.FREE, SessionStatus.READY, students);
    }

    @Test
    void startDateEndDateIsNotNull() {
        assertThat(session.getStartDate()).isNotNull();
        assertThat(session.getEndDate()).isNotNull();
    }

    @Test
    void coverImageUrlIsNotNull() {
        assertThat(session.getCoverImageUrl()).isNotNull();
    }

    @Test
    void sessionTypeIsFreeOrPremium() {
        assertThat(session.getType())
                .isNotNull()
                .isIn(SessionType.values());
    }

    @Test
    void sessionStatus() {
        assertThat(session.getStatus())
                .isNotNull()
                .isIn(SessionStatus.values());
    }

    @Test
    void enrollNotOpenedFailure() {
        session = Session.of(LocalDateTime.now(), LocalDateTime.now().plusYears(1), "https://ianUrl",
                SessionType.FREE, SessionStatus.READY, students);
        assertThatThrownBy(() -> session.enroll()).isInstanceOf(SessionNotOpenedException.class);


        session = Session.of(LocalDateTime.now(), LocalDateTime.now().plusYears(1), "https://ianUrl",
                SessionType.FREE, SessionStatus.CLOSED, students);
        assertThatThrownBy(() -> session.enroll()).isInstanceOf(SessionNotOpenedException.class);
    }

    @Test
    void enrollOpenedSuccess() throws SessionNotOpenedException, SessionMaxStudentsExceedException {
        session = Session.of(LocalDateTime.now(), LocalDateTime.now().plusYears(1), "https://ianUrl",
                SessionType.FREE, SessionStatus.OPENED, students);
        assertThat(session.enroll()).isTrue();
    }

    @Test
    void enrollMaximumStudentFailed() {
        session = Session.of(LocalDateTime.now(), LocalDateTime.now().plusYears(1), "https://ianUrl",
                SessionType.FREE, SessionStatus.OPENED,
                makeStudents(50));
        assertThatThrownBy(() -> session.enroll()).isInstanceOf(SessionMaxStudentsExceedException.class);
    }

    @Test
    void enrollMaximumStudentSuccess() throws SessionMaxStudentsExceedException, SessionNotOpenedException {
        session = Session.of(LocalDateTime.now(), LocalDateTime.now().plusYears(1), "https://ianUrl",
                SessionType.FREE, SessionStatus.OPENED,
                makeStudents(49));
        assertThat(session.enroll()).isTrue();
    }

    List<Student> makeStudents(int number) {
        List<Student> students = new ArrayList<>();
        for(int i = 0; i < number; i++){
            students.add(new Student());
        }
        return students;
    }
}
