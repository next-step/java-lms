package nextstep.courses.domain;

import nextstep.courses.domain.session.CoverImage;
import nextstep.courses.domain.session.DateRange;
import nextstep.courses.domain.session.Status;
import nextstep.courses.domain.session.image.ImageFileSize;
import nextstep.courses.domain.session.image.ImageType;
import nextstep.courses.domain.session.image.ImageSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.PaidSessionTest.MAX_REGISTER_COUNT;
import static nextstep.courses.domain.PaidSessionTest.SESSION_AMOUNT;
import static nextstep.courses.domain.session.CoverImageTest.*;
import static nextstep.courses.domain.session.DateRangeTest.END;
import static nextstep.courses.domain.session.DateRangeTest.START;
import static org.assertj.core.api.Assertions.assertThat;

public class SessionTest {

    public static final FreeSession FREE_SESSION1 = new FreeSession(1L, 1L, new DateRange(START, END), new CoverImage(SIZE, IMAGE_TYPE_TEXT, WIDTH, HEIGHT), Status.PREPARE);
    public static final PaidSession PAID_SESSION1 = new PaidSession(1L, 1L, new DateRange(START, END), new CoverImage(SIZE, IMAGE_TYPE_TEXT, WIDTH, HEIGHT), Status.PREPARE, MAX_REGISTER_COUNT, SESSION_AMOUNT);

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

        FreeSession actual = new FreeSession(1L, 1L, dateRange, coverImage, status);
        FreeSession expected = new FreeSession(1L, 1L, dateRange, wrappedCoverImage, status);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void createPaidSession() {

        PaidSession actual = new PaidSession(1L, 1L, dateRange, coverImage, status,
                MAX_REGISTER_COUNT,
                SESSION_AMOUNT);

        PaidSession expected = new PaidSession(1L, 1L, dateRange, wrappedCoverImage, status,
                MAX_REGISTER_COUNT,
                SESSION_AMOUNT);

        assertThat(actual).isEqualTo(expected);

    }
}
