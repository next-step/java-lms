package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class SessionTest {

    @Test
    @DisplayName("강의는 시작일을 가진다.")
    void startAt() {
        LocalDateTime startAt = LocalDateTime.MIN;
        LocalDateTime endAt = LocalDateTime.MAX;
        String coverImage = "커버이미정보";

        Session session = new Session(startAt, endAt, coverImage);

        assertThat(session.getStartAt()).isEqualTo(startAt);
    }

    @Test
    @DisplayName("강의는 종료일을 가진다.")
    void endAt() {
        LocalDateTime startAt = LocalDateTime.MIN;
        LocalDateTime endAt = LocalDateTime.MAX;
        String coverImage = "커버이미정보";

        Session session = new Session(startAt, endAt, coverImage);

        assertThat(session.getEndAt()).isEqualTo(endAt);
    }

    @Test
    @DisplayName("강의는 강의 커버 이미지 정보를 가진다.")
    void coverImage() {
        LocalDateTime startAt = LocalDateTime.MIN;
        LocalDateTime endAt = LocalDateTime.MAX;
        String coverImage = "커버이미정보";

        Session session = new Session(startAt, endAt, coverImage);

        assertThat(session.getCoverImage()).isEqualTo(coverImage);
    }

}
