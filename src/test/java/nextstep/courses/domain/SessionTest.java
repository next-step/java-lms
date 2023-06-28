package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.SessionBuilder.session;
import static org.assertj.core.api.Assertions.*;

public class SessionTest {


    @Test
    void 이미지정보() {
        Session session = session()
                .image("image.jpeg")
                .build();

        assertThat(session.getImage()).isNotNull();
    }

    @Test
    void 무료강의() {
        Session session = session()
                .lectureType(LectureType.FREE)
                .build();

        assertThat(session.getLectureType()).isEqualTo(LectureType.FREE);
    }

    @Test
    void 유료강의() {
        Session session = session()
                .lectureType(LectureType.PAID)
                .build();

        assertThat(session.getLectureType()).isEqualTo(LectureType.PAID);
    }
}
