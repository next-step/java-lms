package nextstep.courses.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class CourseTest {

    private Course course;
    private Session session1;
    private Session session2;
    private Session session3;

    @BeforeEach
    void setUp() {
        this.course = new Course();
        Long courseId = 1L;
        SessionInfo sessionInfo = new SessionInfo(courseId, 1L, "title1", "coverImageInfo", SessionType.FREE);
        SessionStatus sessionStatus = SessionStatus.OPENED;
        SessionEnrollment sessionEnrollment = new SessionEnrollment(sessionStatus, 3L);
        SessionTimeLine sessionTimeLine = new SessionTimeLine(LocalDateTime.now(), LocalDateTime.now().plusDays(10));

        session1 = new Session(sessionInfo, sessionEnrollment, sessionTimeLine);
        session2 = new Session(sessionInfo, sessionEnrollment, sessionTimeLine);
        session3 = new Session(sessionInfo, sessionEnrollment, sessionTimeLine);

        course.addSession(session1);
        course.addSession(session2);
        course.addSession(session3);
    }

    @Test
    @DisplayName("기수에 따른 강의를 조회할 수 있다.")
    void findSession_FromCourse_ContainExactly() {
        Session session = course.getSession(1);

        assertThat(session).isEqualTo(this.session1);
    }

    @ParameterizedTest
    @DisplayName("기수에 따른 존재하는 강의를 조회시 예외를 던지지 않는다.")
    @ValueSource(ints = {1, 2, 3})
    void findSession_FromCourseInExists_NoException(int generation) {
        assertThatNoException()
                .isThrownBy(() -> course.getSession(generation));
    }

    @ParameterizedTest
    @DisplayName("유효하지 않는 기수(음수, 0)를 조회시 예외를 던진다.")
    @ValueSource(ints = {-2, -1, 0})
    void findSession_ByInvalidValue_ThrowException(int generation) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> course.getSession(generation))
                .withMessageContaining("기수는 1 기수 이상부터 시작합니다.");
    }

    @Test
    @DisplayName("존재하지 않는 기수 강의를 조회시 예의를 던진다.")
    void findSession_IfNotExists_ThrowException() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> course.getSession(4))
                .withMessageContaining("해당 기수의 강의는 존재하지 않습니다.");

    }
}
