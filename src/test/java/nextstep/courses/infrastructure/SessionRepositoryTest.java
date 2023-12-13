package nextstep.courses.infrastructure;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionCover;
import nextstep.courses.domain.SessionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class SessionRepositoryTest {

    @Autowired
    private SessionRepository sessionRepository;

    @Test
    void crud() {
        int id = sessionRepository.save(Session.ofFree(3L, LocalDateTime.now(), LocalDateTime.now().plusMonths(1), new SessionCover(100L, 300, 200, 1024, new byte[100]), new Course(100L,"제목", 3L)));
        Session session = sessionRepository.findById((long) id);
        assertThat(session.id()).isEqualTo(id);
    }
}