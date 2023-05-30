package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class SessionTest {
    @Test
    public void add_NotOpen() {
        Student student = new Student(111L);
        Session session = new Session(1L, "제목", LocalDateTime.now(), LocalDateTime.now(), "url", true, SessionStatus.CLOSED, new Students(), 30L);
        assertThatIllegalStateException().isThrownBy(() -> session.add(student));
    }

    @Test
    public void add_Full() {
        Session session = new Session(1L, "제목", LocalDateTime.now(), LocalDateTime.now(), "url", true, SessionStatus.OPEN, new Students(), 2L);
        session.add(new Student(111L));
        session.add(new Student(222L));
        Student student = new Student(333L);
        assertThatIllegalStateException().isThrownBy(() -> session.add(student));
    }
}
