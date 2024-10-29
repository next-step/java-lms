package nextstep.courses.domain;

import nextstep.courses.domain.session.CoverImage;
import nextstep.courses.domain.session.DateRange;
import nextstep.courses.domain.session.Status;
import nextstep.courses.domain.session.image.ImageFileSize;
import nextstep.courses.domain.session.image.ImageSize;
import nextstep.courses.domain.session.image.ImageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static nextstep.courses.domain.FreeSessionTest.FS1;
import static nextstep.courses.domain.PaidSessionTest.*;
import static nextstep.courses.domain.session.CoverImageTest.*;
import static nextstep.courses.domain.session.DateRangeTest.END;
import static nextstep.courses.domain.session.DateRangeTest.START;
import static org.assertj.core.api.Assertions.assertThat;

public class SessionTest {

    public static final FreeSession FREE_SESSION1 = new FreeSession(1L, 1L, CourseTest.C1.getId(), new DateRange(START, END), new CoverImage(SIZE, IMAGE_TYPE_TEXT, WIDTH, HEIGHT), Status.PREPARE, LocalDateTime.now(), LocalDateTime.now());
    public static final PaidSession PAID_SESSION1 = new PaidSession(1L, 1L, CourseTest.C1.getId(), new DateRange(START, END), new CoverImage(SIZE, IMAGE_TYPE_TEXT, WIDTH, HEIGHT), Status.PREPARE, MAX_REGISTER_COUNT, SESSION_AMOUNT, LocalDateTime.now(), LocalDateTime.now());

    private DateRange dateRange;
    private CoverImage coverImage;
    private ImageFileSize imageFileSize;
    private ImageType imageType;
    private ImageSize imageSize;
    private CoverImage wrappedCoverImage;
    private Status status;

    @BeforeEach
    void setUp() {
        dateRange = new DateRange(START, END);
        coverImage = new CoverImage(SIZE, IMAGE_TYPE_TEXT, WIDTH, HEIGHT);

        imageFileSize = new ImageFileSize(SIZE);
        imageType = ImageType.toImageType(IMAGE_TYPE_TEXT);
        imageSize = new ImageSize(WIDTH, HEIGHT);

        wrappedCoverImage = new CoverImage(
                imageFileSize,
                imageType,
                imageSize);

        status = Status.PREPARE;
    }

    @Test
    void createFreeSession() {
        FreeSession actual = new FreeSession(1L, 1L, CourseTest.C1.getId(), dateRange, coverImage, status, LocalDateTime.now(), LocalDateTime.now());
        FreeSession expected = new FreeSession(1L, 1L, CourseTest.C1.getId(), dateRange, wrappedCoverImage, status, LocalDateTime.now(), LocalDateTime.now());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void createPaidSession() {

        PaidSession actual = new PaidSession(1L, 1L, CourseTest.C1.getId(), dateRange, coverImage, status,
                MAX_REGISTER_COUNT,
                SESSION_AMOUNT,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        PaidSession expected = new PaidSession(1L, 1L, CourseTest.C1.getId(), dateRange, wrappedCoverImage, status,
                MAX_REGISTER_COUNT,
                SESSION_AMOUNT,
                LocalDateTime.now(),
                LocalDateTime.now());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void toParameters() {
        Object[] actual = PS1.toParameters();
        Object[] expected = FS1.toParameters();
        assertThat(actual).isEqualTo(expected);
    }
}
