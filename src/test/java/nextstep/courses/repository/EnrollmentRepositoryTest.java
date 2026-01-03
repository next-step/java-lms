package nextstep.courses.repository;

import nextstep.courses.domain.Enrollment;
import nextstep.courses.infrastructure.JdbcEnrollmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(JdbcEnrollmentRepository.class)
public class EnrollmentRepositoryTest {

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Test
    void save() {
        Enrollment savedEnrollment = enrollmentRepository.save(new Enrollment(1L, 1L));

        assertThat(savedEnrollment).isNotNull();
    }

    @Test
    void find() {
        Enrollment saveEnrollment = enrollmentRepository.save(new Enrollment(1L, 1L));

        Enrollment enrollment = enrollmentRepository.findById(saveEnrollment.getId());

        assertThat(enrollment).isEqualTo(new Enrollment(1L, 1L));
    }
}
