package nextstep.courses.domain.session;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import nextstep.courses.domain.cover.Image;
import nextstep.courses.domain.cover.ImageHeight;
import nextstep.courses.domain.cover.ImageSize;
import nextstep.courses.domain.cover.ImageType;
import nextstep.courses.domain.cover.ImageWidth;
import nextstep.courses.domain.session.impl.FreeSession;
import nextstep.courses.error.exception.DuplicateSessionException;
import org.junit.jupiter.api.Test;

class SessionsTest {

    @Test
    void Sessions는_강의를_추가할_수_있다() {
        Sessions sessions = new Sessions();
        Session session = new FreeSession(new SessionName("강의이름1"),
            LocalDateTime.now(),
            LocalDateTime.now(),
            new Image(new ImageSize(1),
                ImageType.JPG,
                new ImageWidth(300),
                new ImageHeight(200)),
            SessionStatus.PREPARING);
        sessions.addSession(new SessionName("강의이름1"), session);

        assertThat(sessions.findSession(new SessionName("강의이름1"))).isEqualTo(session);
    }

    @Test
    void Sessions는_이미_등록된_강의를_등록할_경우_예외가_발생한다() {
        Sessions sessions = new Sessions();
        Session session = new FreeSession(new SessionName("강의이름1"),
            LocalDateTime.now(),
            LocalDateTime.now(),
            new Image(new ImageSize(1),
                ImageType.JPG,
                new ImageWidth(300),
                new ImageHeight(200)),
            SessionStatus.PREPARING);
        sessions.addSession(new SessionName("강의이름1"), session);

        assertThat(sessions.findSession(new SessionName("강의이름1"))).isEqualTo(session);
        assertThatThrownBy(() -> sessions.addSession(new SessionName("강의이름1"), session))
            .isInstanceOf(DuplicateSessionException.class)
            .hasMessage("이미 등록된 강의입니다 입력값: 강의이름1");
    }

    @Test
    void Sessions는_강의를_삭제할_수_있다() {
        Sessions sessions = new Sessions();
        SessionName sessionName = new SessionName("강의이름2");
        Session session = new FreeSession(new SessionName("강의이름2"),
            LocalDateTime.now(),
            LocalDateTime.now(),
            new Image(new ImageSize(1),
                ImageType.JPG,
                new ImageWidth(300),
                new ImageHeight(200)),
            SessionStatus.PREPARING);

        sessions.addSession(sessionName, session);
        Session deletedSession = sessions.deleteSession(sessionName);

        assertThat(deletedSession).isEqualTo(session);
    }
}
