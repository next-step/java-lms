package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.Enrollment;
import nextstep.courses.domain.session.repository.EnrollmentRepository;
import nextstep.courses.record.EnrollmentRecord;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class EnrollmentRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(EnrollmentRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private EnrollmentRepository enrollmentRepository;

    @BeforeEach
    void setUp() {
        enrollmentRepository = new JdbcEnrollmentRespository(jdbcTemplate);
    }

    @Test
    void crud() {
        int count = enrollmentRepository.save(new Enrollment(NsUserTest.JAVAJIGI, 1L));
        assertThat(count).isEqualTo(1);

        List<EnrollmentRecord> enrollments = enrollmentRepository.findBySessionId(1L);
        assertThat(enrollments).hasSize(3);
    }


}
