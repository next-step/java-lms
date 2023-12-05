package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.List;

import static nextstep.courses.domain.ImageTest.IMAGE_URL;

public class SessionFactory {
    public static final LocalDateTime START_DATE_2023 = LocalDateTime.of(2023, 1, 1, 0, 0);
    public static final LocalDateTime END_DATE_2023 = LocalDateTime.of(2023, 12, 1, 0, 0);

    public static Session basePaidSession(){
        return new Session(
                START_DATE_2023,
                END_DATE_2023,
                SessionType.PAID,
                SessionOpenStatus.OPENED,
                10,
                1000,
                SessionProgressStatus.READY,
                List.of(new Image(IMAGE_URL, Size.MAXIMUM_WIDTH, Size.MAXIMUM_HEIGHT, ImageType.PNG)));
    }

    public static Session baseFreeSession(){
        return new Session(
                START_DATE_2023,
                END_DATE_2023,
                SessionType.FREE,
                SessionOpenStatus.OPENED,
                0,
                0,
                SessionProgressStatus.READY,
                List.of(new Image(IMAGE_URL, Size.MAXIMUM_WIDTH, Size.MAXIMUM_HEIGHT, ImageType.PNG)));
    }

    public static Session SESSION_유료_모집중_10명_1000원_최대사이즈이미지포함(LocalDateTime startDate, LocalDateTime endDate) {
        Session session = new Session(
                startDate,
                endDate,
                SessionType.PAID,
                SessionOpenStatus.OPENED,
                10,
                1000,
                SessionProgressStatus.READY,
                List.of(new Image(IMAGE_URL, Size.MAXIMUM_WIDTH, Size.MAXIMUM_HEIGHT, ImageType.PNG)));
        return session;
    }
}
