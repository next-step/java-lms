package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionTest {

    public static final LocalDate startAt = LocalDate.of(2024, 1, 1);
    public static final LocalDate endAt = LocalDate.of(2024, 1, 31);
    public static final SessionImage image = new SessionImage(300, 200, 1_000_000, new SessionImageType("jpg"));
    public static final SessionProgress progress = new SessionProgress("준비중");
    public static final SessionType type = new SessionType(true);

    @Test
    void 강의_생성() {
        assertThat(new Session(1L, startAt, endAt, image, progress, type))
                .isEqualTo(new Session(1L, startAt, endAt, image, progress, type));
    }

}
