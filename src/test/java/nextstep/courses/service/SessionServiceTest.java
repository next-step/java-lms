package nextstep.courses.service;

import nextstep.courses.domain.session.Image;
import nextstep.courses.domain.session.Period;
import nextstep.courses.domain.session.Session;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SessionServiceTest {

    @Autowired
    private SessionService sessionService;

    @Test
    @DisplayName("유료, 무료 강의를 생성한다.")
    @Transactional
    public void save() {
        Session paidSession = Session.ofPaid(Period.from(), Image.from(), 1_000L, 1000L);
        Session freeSession = Session.ofFree(Period.from(), Image.from());

        assertThat(sessionService.save(paidSession)).isEqualTo(1);
        assertThat(sessionService.save(freeSession)).isEqualTo(1);
    }

    @Test
    @DisplayName("강의를 조회한다, 강의정보, 이미지, 수강생 목록이 포함되어 있다.")
    public void findById() {
        Session session = sessionService.findById(1000L);

        assertThat(session.id()).isEqualTo(1000L);
        assertThat(session.image().id()).isEqualTo(1000L);
        assertThat(session.students().size()).isEqualTo(2);
    }
//
//    @Test
//    @DisplayName("유료 강의는 최대 수강 인원을 초과할 수 없다.")
//    public void findById() {
//        Session paidSession = Session.ofPaid(Period.from(), Image.from(), 1_000L, 1000L);
//        assertThat(sessionService.save(paidSession)).isEqualTo(1);
//    }
}
