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
        int save = enrollmentRepository.save(new Enrollment(1L, 1L));

        assertThat(save).isEqualTo(1);
    }

    @Test
    void find() {
        enrollmentRepository.save(new Enrollment(1L, 1L));

        Enrollment byId = enrollmentRepository.findById(1L);

        assertThat(byId).isEqualTo(new Enrollment(1L, 1L));
    }
}
