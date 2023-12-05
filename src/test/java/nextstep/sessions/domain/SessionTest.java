package nextstep.sessions.domain;

import nextstep.images.domain.Image;
import nextstep.images.domain.ImageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTest {
    Long id;
    LocalDateTime startedDate;
    LocalDateTime endedDate;
    Image image;
    SessionType sessionType;
    @BeforeEach
    void setUp() {
        id = 1L;

        long size = 1024;
        int width = 300;
        int height = 200;
        ImageType imageType = ImageType.PNG;
        image = new Image(id, size, width, height, imageType);
        sessionType = SessionType.PAY;

        startedDate = LocalDateTime.of(2023, 12, 1, 0, 0, 0);
        endedDate = LocalDateTime.of(2023, 12, 30, 0, 0, 0);
    }

    @Test
    @DisplayName("강의 시작일은 종료일을 넘을 수 없다.")
    void test1() {
        startedDate = LocalDateTime.of(2023, 12, 30, 0, 0, 0);
        endedDate = LocalDateTime.of(2023, 12, 1, 0, 0, 0);
        assertThatThrownBy(() -> new FreeSession(id, startedDate, endedDate, image, sessionType))
                .hasMessageContaining("강의 시작일은 종료일을 넘을 수 없습니다.");
    }

    @Test
    @DisplayName("강의가 종료되면 신청할 수 없다.")
    void test2() {
        FreeSession freeSession = new FreeSession(id, startedDate, endedDate, image, sessionType);
        freeSession.changeSessionStatus(Status.CLOSED);
        assertThatThrownBy(freeSession::enrolment)
                .hasMessageContaining("강의가 모집중이 아닙니다.");
    }
}