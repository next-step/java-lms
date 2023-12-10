package nextstep.courses.domain;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class SessionTest {

    @Test
    void 강의는_강의_커버_이미지_정보를_가진다() {
        // given
        Session session = new Session();
        Image image = new Image(1);

        // when
        session.setCoverImage(image);

        // then
        assertThat(session.getImage()).isEqualTo(image);
    }
}
