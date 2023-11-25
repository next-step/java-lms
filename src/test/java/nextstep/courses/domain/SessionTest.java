package nextstep.courses.domain;

import nextstep.courses.domain.type.SessionStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SessionTest {

    @Test
    @DisplayName("강의는 강의 커버 이미지 정보를 가진다")
    public void session_image() {
        Image image = new Image(1, "JPG", 300, 200);
        assertThat(new Session(LocalDate.now(), LocalDate.now(), image, SessionStatus.READY)).extracting(Session::image).isEqualTo(image);
    }

}
