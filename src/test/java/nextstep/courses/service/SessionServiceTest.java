package nextstep.courses.service;

import nextstep.courses.domain.session.Session;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SessionServiceTest {

    @Autowired
    private SessionService sessionService;

    @Test
    @DisplayName("강의를 조회한다, 강의정보, 이미지, 수강생 목록이 포함되어 있다.")
    public void findById() {
        Session session = sessionService.findById(1000L);

        assertThat(session.id()).isEqualTo(1000L);
        assertThat(session.image().id()).isEqualTo(1000L);
        assertThat(session.students().size()).isEqualTo(2);
    }
}
