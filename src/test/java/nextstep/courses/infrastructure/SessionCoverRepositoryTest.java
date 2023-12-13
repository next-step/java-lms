package nextstep.courses.infrastructure;

import nextstep.courses.domain.SessionCover;
import nextstep.courses.domain.SessionCoverRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class SessionCoverRepositoryTest {
    @Autowired
    private SessionCoverRepository sessionCoverRepository;

    @Test
    void save_and_findById() {
        int id = sessionCoverRepository.save(new SessionCover(new byte[100]));
        SessionCover sessionCover = sessionCoverRepository.findById((long) id);
        assertThat(sessionCover.id()).isEqualTo(id);
    }
}