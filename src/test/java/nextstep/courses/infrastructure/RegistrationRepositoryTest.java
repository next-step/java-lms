package nextstep.courses.infrastructure;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.Registration;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionCover;
import nextstep.courses.domain.repository.RegistrationRepository;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
class RegistrationRepositoryTest {
    @Autowired
    private RegistrationRepository registrationRepository;

    @Test
    void crud() {
        int id = registrationRepository.save(new Registration(new NsUser(1L, "1", "2", "3", "4")
                , Session.ofFree(1L, LocalDateTime.now(), LocalDateTime.now().plusMonths(1), new SessionCover(1L, 300, 200, 1024, new byte[100]), new Course())
                , 100000L));
        List<Registration> registrations = registrationRepository.findAllBySessionId(1L);
        assertThat(registrations.get(0).nsUser().getId()).isEqualTo(1L);
    }
}