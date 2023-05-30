package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class StudentTest {

    @Test
    void testCreateStudent() {
        assertThat(new Student(1,1L).getSessionId()).isEqualTo(1);
    }
}