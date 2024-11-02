package nextstep.courses.tobe.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class InstructorTest {
    private Instructor instructor;

    @BeforeEach
    void setUp() {
        instructor = new Instructor(0L, "javajigi", "password", "name", "javajigi@slipp.net", 1L, LocalDateTime.now());
    }

    @Test
    void create() {
        Instructor actual = new Instructor("javajigi", "password", "name", "javajigi@slipp.net", 1L);
        Assertions.assertThat(actual).isEqualTo(instructor);
    }


}
