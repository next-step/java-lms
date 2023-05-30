package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class StudentTest {
    public static final Student SESSION1_STUDENT1 = new Student(1,1L);
    public static final Student SESSION1_STUDENT2 = new Student(1,2L);


    @Test
    void testCreateStudent() {
        assertThat(SESSION1_STUDENT1.getSessionId()).isEqualTo(1);
    }
}