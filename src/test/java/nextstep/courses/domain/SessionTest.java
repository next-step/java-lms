package nextstep.courses.domain;

import nextstep.courses.domain.vo.session.CoverImage;
import nextstep.courses.domain.vo.session.DateRange;
import nextstep.courses.domain.vo.session.Status;
import nextstep.courses.domain.vo.session.image.ImageSize;
import nextstep.courses.domain.vo.session.image.ImageType;
import nextstep.courses.domain.vo.session.image.ImageWidthHeight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.PaidSessionTest.MAX_REGISTER_COUNT;
import static nextstep.courses.domain.PaidSessionTest.SESSION_AMOUNT;
import static nextstep.courses.domain.vo.session.CoverImageTest.*;
import static nextstep.courses.domain.vo.session.DateRangeTest.END;
import static nextstep.courses.domain.vo.session.DateRangeTest.START;
import static org.assertj.core.api.Assertions.assertThat;

public class SessionTest {

    public static final FreeSession FREE_SESSION1 = new FreeSession(1L, 1L, new DateRange(START, END), new CoverImage(SIZE, IMAGE_TYPE_TEXT, WIDTH, HEIGHT), Status.PREPARE);
    public static final PaidSession PAID_SESSION1 = new PaidSession(1L, 1L, new DateRange(START, END), new CoverImage(SIZE, IMAGE_TYPE_TEXT, WIDTH, HEIGHT), Status.PREPARE, MAX_REGISTER_COUNT, SESSION_AMOUNT);

    private DateRange dateRange;
    private CoverImage coverImage;
    private ImageSize imageSize;
    private ImageType imageType;
    private ImageWidthHeight imageWidthHeight;
    private CoverImage wrappedCoverImage;
    private Status status;

    @BeforeEach
    void setUp() {
        dateRange = new DateRange(START, END);
        coverImage = new CoverImage(SIZE, IMAGE_TYPE_TEXT, WIDTH, HEIGHT);

        imageSize = new ImageSize(SIZE);
        imageType = ImageType.toImageType(IMAGE_TYPE_TEXT);
        imageWidthHeight = new ImageWidthHeight(WIDTH, HEIGHT);

        wrappedCoverImage = new CoverImage(
                imageSize,
                imageType,
                imageWidthHeight);

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
