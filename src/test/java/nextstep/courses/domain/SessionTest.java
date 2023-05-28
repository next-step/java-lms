package nextstep.courses.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SessionTest {

    private Session session;
    @BeforeEach
    void setUp() {
        session = new Session(1L,"2021-01-01", "2021-01-02", Image.DEFAULT_IMAGE_URL);
    }

    @Test
    void test_강의는_기간을_갖는다() {
        //when
        Period period = session.getPeriod();

        //then
        assertThat(period).isNotNull();
    }

    @Test
    void test_강의_생성시_이미지_정보를_갖는다() {
        // when
        Image image = session.getImage();

        // then
        assertThat(image).isNotNull();
    }
}
