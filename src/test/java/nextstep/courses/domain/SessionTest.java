package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static nextstep.courses.domain.StudentsTest.students;
import static org.assertj.core.api.Assertions.*;

public class SessionTest {
    public static final Session session1 = new Session(1L,
            1L,
            new SessionInfo("자바1기", 1L, "image_url"),
            LocalDateTime.now(),
            LocalDateTime.now().plusDays(30),
            new Price(false, 100_000),
            Status.RECRUITING,
            new Students(10));

    @Test
    @DisplayName("강의가 모집중이 아닐 때는 익셉셥을 반환한다.")
    public void sessionStatusExceptionTest() throws CannotEnrollException {
        session1.changeStatue(Status.WAITING);
        assertThatThrownBy(() -> session1.enroll(new Student(1L, "gildong")))
                .isInstanceOf(CannotEnrollException.class)
                .hasMessageContaining("강의가 모집중이 아닙니다.");

    }

    @Test
    @DisplayName("강의의 모집 정원이 초과했을 때 익셉션을 반환한다.")
    public void isOverMaxStudent() throws CannotEnrollException {
        session1.changeStatue(Status.RECRUITING);
        session1.enroll(new Student(1L, "학생1"));
        session1.enroll(new Student(2L, "학생2"));
        session1.enroll(new Student(3L, "학생3"));
        session1.enroll(new Student(4L, "학생4"));
        session1.enroll(new Student(5L, "학생5"));
        session1.enroll(new Student(6L, "학생6"));
        session1.enroll(new Student(7L, "학생7"));
        session1.enroll(new Student(8L, "학생8"));
        session1.enroll(new Student(9L, "학생9"));
        session1.enroll(new Student(10L, "학생10"));
        assertThatThrownBy(() -> session1.enroll(new Student(11L, "학생11")))
                .isInstanceOf(CannotEnrollException.class)
                .hasMessageContaining("수강 신청 최대 인원을 초과했습니다.");

    }
}
