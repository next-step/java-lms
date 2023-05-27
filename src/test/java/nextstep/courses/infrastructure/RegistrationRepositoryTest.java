package nextstep.courses.infrastructure;

import nextstep.courses.domain.ragistration.Registration;
import nextstep.courses.domain.ragistration.RegistrationRepository;
import nextstep.courses.domain.ragistration.RegistrationType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class RegistrationRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RegistrationRepository registrationRepository;

    @BeforeEach
    void setUp() {
        registrationRepository = new JdbcRegistrationRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        Registration registration = new Registration(1L, 2L, 3L, 4L, RegistrationType.RECEIPT, LocalDateTime.now(), null);
        int count = registrationRepository.save(registration);
        assertThat(count).isEqualTo(1);

        Registration savedRegistration = registrationRepository.findByCourseIdAndSessionIdAndUserId(2L, 3L, 4L);
        assertThat(savedRegistration.getRegistrationType())
                .isEqualTo(RegistrationType.RECEIPT);
    }
}
