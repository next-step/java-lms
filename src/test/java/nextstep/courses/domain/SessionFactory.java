package nextstep.courses.domain;

import java.time.LocalDateTime;

public class SessionFactory {
    public static final LocalDateTime START_DATE_2023 = LocalDateTime.of(2023, 1, 1, 0, 0);
    public static final LocalDateTime END_DATE_2023 = LocalDateTime.of(2023, 12, 1, 0, 0);

    public static Session basePaidSession(){
        return new Session(
                START_DATE_2023,
                END_DATE_2023,
                SessionType.PAID,
                new Image(ImageTest.IMAGE_URL, 1024, 1024, ImageType.PNG),
                SessionStatus.OPENED,
                10,
                1000
        );
    }

    public static Session baseFreeSession(){
        return new Session(
                START_DATE_2023,
                END_DATE_2023,
                SessionType.FREE,
                new Image(ImageTest.IMAGE_URL, 1024, 1024, ImageType.PNG),
                SessionStatus.OPENED,
                0,
                0
        );
    }

    public static Session SESSION_유료_모집중_10명_1000원_최대사이즈이미지포함(LocalDateTime startDate, LocalDateTime endDate) {
        Session session = new Session(
                startDate,
                endDate,
                SessionType.PAID,
                new Image(ImageTest.IMAGE_URL, 1024, 1024, ImageType.PNG),
                SessionStatus.OPENED,
                10,
                1000
        );
        return session;
    }
}
