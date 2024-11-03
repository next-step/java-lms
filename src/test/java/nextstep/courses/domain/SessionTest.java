package nextstep.courses.domain;

import nextstep.courses.domain.session.DateRange;
import nextstep.courses.domain.session.Status;
import nextstep.courses.domain.session.image.ImageFileSize;
import nextstep.courses.domain.session.image.ImageSize;
import nextstep.courses.domain.session.image.ImageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static nextstep.courses.domain.PaidSessionTest.*;
import static nextstep.courses.domain.session.CoverImageTest.*;
import static nextstep.courses.domain.session.DateRangeTest.END;
import static nextstep.courses.domain.session.DateRangeTest.START;
import static org.assertj.core.api.Assertions.assertThat;

public class SessionTest {

    public static final FreeSession FREE_SESSION1 = new FreeSession(1L, CourseTest.C1.getId(), new DateRange(START, END), new CoverImage(SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200), Status.PREPARE, 1L, LocalDateTime.now(), LocalDateTime.now());
    public static final PaidSession PAID_SESSION1 = new PaidSession(1L, CourseTest.C1.getId(), new DateRange(START, END), new CoverImage(SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200), Status.PREPARE, MAX_REGISTER_COUNT, SESSION_AMOUNT, 1L, LocalDateTime.now(), LocalDateTime.now());

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
        coverImage =new CoverImage(SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200);

        imageFileSize = new ImageFileSize(SIZE_1024);
        imageType = ImageType.toImageType(IMAGE_TYPE_TEXT_GIF);
        imageSize = new ImageSize(WIDTH_300, HEIGHT_200);

        wrappedCoverImage = new CoverImage(
                imageFileSize,
                imageType,
                imageSize);

        status = Status.PREPARE;
    }

    @Test
    void createFreeSession() {
        FreeSession actual = new FreeSession(1L, CourseTest.C1.getId(), dateRange, coverImage, status, 1L, LocalDateTime.now(), LocalDateTime.now());
        FreeSession expected = new FreeSession(1L, CourseTest.C1.getId(), dateRange, wrappedCoverImage, status, 1L, LocalDateTime.now(), LocalDateTime.now());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void createPaidSession() {

        PaidSession actual = new PaidSession(1L, CourseTest.C1.getId(), dateRange, coverImage, status,
                MAX_REGISTER_COUNT,
                SESSION_AMOUNT,
                1L,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        PaidSession expected = new PaidSession(1L, CourseTest.C1.getId(), dateRange, wrappedCoverImage, status,
                MAX_REGISTER_COUNT,
                SESSION_AMOUNT,
                1L,
                LocalDateTime.now(),
                LocalDateTime.now());

        assertThat(actual).isEqualTo(expected);
    }
}