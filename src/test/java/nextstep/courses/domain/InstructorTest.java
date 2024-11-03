package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class InstructorTest {

    public static final Instructor IN1 = new Instructor(1L, "javajigi", "password", "name", "javajigi@slipp.net", LocalDateTime.now(), LocalDateTime.now());
    public static final Instructor IN2 = new Instructor(2L, "sanjigi", "password", "name", "sanjigi@slipp.net", LocalDateTime.now(), LocalDateTime.now());

    private Instructor instructor;

    @BeforeEach
    void setUp() {
        instructor = new Instructor(0L, "javajigi", "password", "name", "javajigi@slipp.net", LocalDateTime.now(), LocalDateTime.now());
    }

    @Test
    void create() {
        Instructor actual = new Instructor("javajigi", "password", "name", "javajigi@slipp.net");
        Assertions.assertThat(actual).isEqualTo(instructor);
    }

    @Test
    void getter() {
        Instructor actual = new Instructor(1L, "javajigi", "password", "name", "javajigi@slipp.net", LocalDateTime.now(), LocalDateTime.now());
        long actualInstructorId = actual.getId();

        Assertions.assertThat(actualInstructorId).isEqualTo(1L);
    }
}
