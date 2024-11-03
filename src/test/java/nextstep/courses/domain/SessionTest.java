package nextstep.courses.domain;

import nextstep.courses.ProcessEndedException;
import nextstep.courses.domain.session.Category;
import nextstep.courses.domain.session.DateRange;
import nextstep.courses.domain.session.Status;
import nextstep.courses.domain.session.image.ImageFileSize;
import nextstep.courses.domain.session.image.ImageSize;
import nextstep.courses.domain.session.image.ImageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static nextstep.courses.domain.CoverImageTest.*;
import static nextstep.courses.domain.InstructorTest.IN1;
import static nextstep.courses.domain.PaidSessionTest.MAX_REGISTER_COUNT;
import static nextstep.courses.domain.PaidSessionTest.SESSION_AMOUNT;
import static nextstep.courses.domain.ProcessStatus.ENDED;
import static nextstep.courses.domain.ProcessStatus.READY;
import static nextstep.courses.domain.RecruitmentStatus.CLOSED;
import static nextstep.courses.domain.RecruitmentStatus.OPEN;
import static nextstep.courses.domain.Session.NOT_ALLOWED_PROCESS_ENDED_RECRUITMENT_OPEN_MESSAGE;
import static nextstep.courses.domain.session.DateRangeTest.END;
import static nextstep.courses.domain.session.DateRangeTest.START;
import static nextstep.courses.domain.session.Status.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {

    public static final FreeSession FREE_SESSION1 = new FreeSession(1L, CourseTest.C1.getId(), new DateRange(START, END),
            new CoverImage(1L, SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200), PREPARE,
            List.of(new CoverImage(1L, CoverImageTest.SIZE_1024, CoverImageTest.IMAGE_TYPE_TEXT_GIF, CoverImageTest.WIDTH_300, CoverImageTest.HEIGHT_200)), IN1, READY, CLOSED,
            1L, LocalDateTime.now(), LocalDateTime.now());
    public static final PaidSession PAID_SESSION1 = new PaidSession(1L, CourseTest.C1.getId(), new DateRange(START, END),
            new CoverImage(1L, SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200), PREPARE,
            List.of(new CoverImage(1L, SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200)), IN1, READY, CLOSED,
            MAX_REGISTER_COUNT, SESSION_AMOUNT, 1L, LocalDateTime.now(), LocalDateTime.now());

    private DateRange dateRange;
    private List<CoverImage> coverImages;
    private ImageFileSize imageFileSize1;
    private ImageType imageType1;
    private ImageSize imageSize1;
    private ImageFileSize imageFileSize2;
    private ImageType imageType2;
    private ImageSize imageSize2;
    private List<CoverImage> wrappedCoverImages;
    private ProcessStatus processStatus;
    private RecruitmentStatus recruitmentStatus;
    private CoverImage coverImage1;
    private CoverImage coverImage2;
    private CoverImage wrappedCoverImage1;
    private CoverImage wrappedCoverImage2;
    private long courseId;
    private CoverImage coverImage;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @BeforeEach
    void setUp() {
        courseId = CourseTest.C1.getId();
        dateRange = new DateRange(START, END);
        createdAt = LocalDateTime.of(2024, 10, 26, 10, 0);
        updatedAt = LocalDateTime.of(2024, 11, 26, 10, 0);

        coverImage1 = new CoverImage(1L, SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200);
        coverImage2 = new CoverImage(1L, SIZE_512, IMAGE_TYPE_TEXT_JPG, WIDTH_450, HEIGHT_300);
        coverImages = List.of(coverImage1, coverImage2);

        imageFileSize1 = new ImageFileSize(SIZE_1024);
        imageType1 = IMAGE_TYPE_GIF;
        imageSize1 = new ImageSize(WIDTH_300, HEIGHT_200);

        imageFileSize2 = new ImageFileSize(SIZE_512);
        imageType2 = IMAGE_TYPE_JPG;
        imageSize2 = new ImageSize(WIDTH_450, HEIGHT_300);

        wrappedCoverImage1 = new CoverImage(1L, imageFileSize1, imageType1, imageSize1);
        wrappedCoverImage2 = new CoverImage(1L, imageFileSize2, imageType2, imageSize2);
        wrappedCoverImages = List.of(wrappedCoverImage1, wrappedCoverImage2);

        processStatus = READY;
        recruitmentStatus = CLOSED;

        coverImage = new CoverImage(1L, CoverImageTest.SIZE_1024, CoverImageTest.IMAGE_TYPE_TEXT_GIF, CoverImageTest.WIDTH_300, CoverImageTest.HEIGHT_200);
        status = PREPARE;
    }

    @Test
    void createFreeSession_성공() {
        FreeSession actual = new FreeSession(1L, courseId, dateRange,
                coverImage, status,
                coverImages, IN1, processStatus, recruitmentStatus,
                1L, createdAt, updatedAt);
        FreeSession expected = new FreeSession(1L, courseId, dateRange,
                coverImage, status,
                wrappedCoverImages, IN1, processStatus, recruitmentStatus,
                1L, createdAt, updatedAt);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void createFreeSession_ENDED_OPEN_실패() {
        assertThatThrownBy(() -> {
            new FreeSession(1L,
                    courseId, dateRange,
                    coverImage, status,
                    coverImages, IN1, ENDED, OPEN,
                    1L, createdAt, updatedAt);
        }).isInstanceOf(ProcessEndedException.class)
                .hasMessage(NOT_ALLOWED_PROCESS_ENDED_RECRUITMENT_OPEN_MESSAGE);
    }

    @Test
    void createPaidSession_성공() {
        PaidSession actual = new PaidSession(1L,
                courseId, dateRange,
                coverImage, status,
                coverImages, IN1, processStatus, recruitmentStatus,
                MAX_REGISTER_COUNT, SESSION_AMOUNT,
                1L, createdAt, updatedAt);
        PaidSession expected = new PaidSession(1L,
                courseId, dateRange,
                coverImage, status,
                wrappedCoverImages, IN1, processStatus, recruitmentStatus,
                MAX_REGISTER_COUNT, SESSION_AMOUNT,
                1L, createdAt, updatedAt);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void createPaidSession_ENDED_OPEN_실패() {
        assertThatThrownBy(() -> {
            new PaidSession(1L,
                    courseId, dateRange,
                    coverImage, status,
                    coverImages, IN1, ENDED, OPEN,
                    MAX_REGISTER_COUNT, SESSION_AMOUNT,
                    1L, createdAt, updatedAt);
        }).isInstanceOf(ProcessEndedException.class)
                .hasMessage(NOT_ALLOWED_PROCESS_ENDED_RECRUITMENT_OPEN_MESSAGE);
    }

    @Test
    void getter() {
        PaidSession paidSession = new PaidSession(1L,
                courseId, dateRange,
                coverImage, status,
                coverImages, IN1, READY, OPEN,
                MAX_REGISTER_COUNT, SESSION_AMOUNT,
                1L, createdAt, updatedAt);

        assertThat(paidSession.getId()).isEqualTo(1L);
        assertThat(paidSession.getCourseId()).isEqualTo(courseId);
        assertThat(paidSession.getCategory()).isEqualTo(Category.PAID);
        assertThat(paidSession.getDateRange()).isEqualTo(dateRange);
        assertThat(paidSession.getCoverImage()).isEqualTo(coverImage);
        assertThat(paidSession.getStatus()).isEqualTo(status);
        assertThat(paidSession.getInstructorId()).isEqualTo(IN1.getId());
        assertThat(paidSession.getProcessStatus()).isEqualTo(READY);
        assertThat(paidSession.getRecruitmentStatus()).isEqualTo(OPEN);
        assertThat(paidSession.getCreatorId()).isEqualTo(1L);
        assertThat(paidSession.getCreatedAt()).isEqualTo(createdAt);
        assertThat(paidSession.getUpdatedAt()).isEqualTo(updatedAt);
    }
}