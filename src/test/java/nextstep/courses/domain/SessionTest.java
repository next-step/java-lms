package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        assertThatThrownBy(() -> session1.enroll(new Student(1L, JAVAJIGI)))
                .isInstanceOf(CannotEnrollException.class)
                .hasMessageContaining("강의가 모집중이 아닙니다.");

    }

    @Test
    @DisplayName("강의의 모집 정원이 초과했을 때 익셉션을 반환한다.")
    public void isOverMaxStudent() throws CannotEnrollException {
        session1.changeStatue(Status.RECRUITING);
        session1.enroll(new Student(1L, JAVAJIGI));
        session1.enroll(new Student(2L, JAVAJIGI));
        session1.enroll(new Student(3L, JAVAJIGI));
        session1.enroll(new Student(4L, JAVAJIGI));
        session1.enroll(new Student(5L, JAVAJIGI));
        session1.enroll(new Student(6L, JAVAJIGI));
        session1.enroll(new Student(7L, JAVAJIGI));
        session1.enroll(new Student(8L, JAVAJIGI));
        session1.enroll(new Student(9L, JAVAJIGI));
        session1.enroll(new Student(10L, JAVAJIGI));
        assertThatThrownBy(() -> session1.enroll(new Student(11L, JAVAJIGI)))
                .isInstanceOf(CannotEnrollException.class)
                .hasMessageContaining("수강 신청 최대 인원을 초과했습니다.");

    }
}
