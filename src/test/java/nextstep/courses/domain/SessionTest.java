package nextstep.courses.domain;

import nextstep.courses.InvalidImageFormatException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SessionTest {

    public static Session session = new Session("과제4 - 레거시 리팩토링");

    @Test
    @DisplayName("강의는 강의 커버 이미지 정보를 가진다.")
    void saveSessionImageTest() throws InvalidImageFormatException {
        assertThat(session.hasImage()).isFalse();

        SessionImage 강의_이미지 = SessionImage.nameOf("강의 이미지");
        session.imageOf(강의_이미지);

        assertThat(session.hasImage()).isTrue();
    }
}
