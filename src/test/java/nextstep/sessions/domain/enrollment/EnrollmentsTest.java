package nextstep.sessions.domain.enrollment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EnrollmentsTest {

    private Enrollments enrollments;

    @BeforeEach
    void setUp() {
        enrollments = new Enrollments();
    }

    @Test
    void addEnrollment() {
        enrollments.add(EnrollmentTest.E1);
        assertThat(enrollments.size()).isEqualTo(1);
    }

    @Test
    void whenDuplicateEnrollment_thenThrows() {
        enrollments.add(EnrollmentTest.E1);
        assertThatThrownBy(() -> enrollments.add(EnrollmentTest.E1))
                .hasMessageContaining(Enrollments.ERROR_ALREADY_ENROLLED);
    }

}