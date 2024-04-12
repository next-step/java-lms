package nextstep.courses.infrastructure;

import nextstep.courses.domain.Registration;
import nextstep.courses.domain.RegistrationRepository;
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
public class RegistrationRepositoryTest {
  private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private RegistrationRepository registrationRepository;

  @BeforeEach
  void setUp() {
    registrationRepository = new JdbcRegistrationRepository(jdbcTemplate);
  }

  @Test
  void crud() {
    Registration registration = new Registration(1L, 1L);

    int count = registrationRepository.save(registration);
    assertThat(count).isEqualTo(1);

    Registration savedRegistration = registrationRepository.findById(1L);
    assertThat(registration.sessionId()).isEqualTo(savedRegistration.sessionId());
    LOGGER.debug("Registration: {}", savedRegistration);

    List<Registration> savedRegistrations = registrationRepository.findByUserId(1L);
    assertThat(registration.sessionId()).isEqualTo(savedRegistrations.get(0).sessionId());
    LOGGER.debug("Registrations by userId: {}", savedRegistrations);

    savedRegistrations = registrationRepository.findBySessionId(1L);
    assertThat(registration.userId()).isEqualTo(savedRegistrations.get(0).userId());
    LOGGER.debug("Registrations by sessionId: {}", savedRegistrations);
  }
}
