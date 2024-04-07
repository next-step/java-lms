package nextstep;

import courses.infrastructure.CourseRepositoryTest;
import courses.infrastructure.RegistrationRepositoryTest;
import courses.infrastructure.SessionImageRepositoryTest;
import courses.infrastructure.SessionRepositoryTest;
import nextstep.users.infrastructure.UserRepositoryTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {
        CourseRepositoryTest.class, RegistrationRepositoryTest.class,
        SessionImageRepositoryTest.class, SessionRepositoryTest.class,
        UserRepositoryTest.class
})
class NextstepApplicationTest {
    @Test
    void contextLoads() {
    }
}
