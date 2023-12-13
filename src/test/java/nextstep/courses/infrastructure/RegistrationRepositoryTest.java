package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
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
                , Session.ofFree(3L, LocalDateTime.now(), LocalDateTime.now().plusMonths(1), new SessionCover(100L, 300, 200, 1024, new byte[100]), new Course())
                , 100000L));
        List<NsUser> userList = registrationRepository.findParticipantsBySessionId(100L);
        assertThat(userList.get(0).getId()).isEqualTo(1L);
    }
}