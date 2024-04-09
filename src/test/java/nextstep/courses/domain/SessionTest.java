package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionTest {

    private static final LocalDate startedAt = LocalDate.of(2024, 1, 1);
    private static final LocalDate endedAt = LocalDate.of(2024, 1, 31);
    private static final SessionImage image = new SessionImage(300, 200, 1_000_000, new SessionImageType("jpg"));
    private static final SessionProgress progress = new SessionProgress("준비중");

    @Test
    void 강의_생성() {
        assertThat(new Session(startedAt, endedAt, image, progress, true))
                .isEqualTo(new Session(startedAt, endedAt, image, progress, true));
    }

}
