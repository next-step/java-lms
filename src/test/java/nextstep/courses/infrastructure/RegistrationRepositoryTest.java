package nextstep.courses.infrastructure;

import nextstep.courses.domain.RegistrationRepository;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
class RegistrationRepositoryTest {
    @Autowired
    private RegistrationRepository registrationRepository;

    @Test
    void findParticipants() {
        List<NsUser> userList = registrationRepository.findParticipantsBySessionId(100L);
        assertThat(userList.get(0).getId()).isEqualTo(1L);
    }
}