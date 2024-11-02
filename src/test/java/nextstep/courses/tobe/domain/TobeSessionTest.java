package nextstep.courses.tobe.domain;

import nextstep.courses.domain.CourseTest;
import nextstep.courses.domain.session.DateRange;
import nextstep.courses.domain.session.image.ImageFileSize;
import nextstep.courses.domain.session.image.ImageSize;
import nextstep.courses.domain.session.image.ImageType;
import nextstep.courses.tobe.ProcessEndedException;
import nextstep.courses.tobe.domain.session.TobeCoverImage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static nextstep.courses.domain.PaidSessionTest.MAX_REGISTER_COUNT;
import static nextstep.courses.domain.PaidSessionTest.SESSION_AMOUNT;
import static nextstep.courses.domain.session.DateRangeTest.END;
import static nextstep.courses.domain.session.DateRangeTest.START;
import static nextstep.courses.tobe.domain.InstructorTest.IN1;
import static nextstep.courses.tobe.domain.ProcessStatus.ENDED;
import static nextstep.courses.tobe.domain.ProcessStatus.READY;
import static nextstep.courses.tobe.domain.RecruitmentStatus.CLOSED;
import static nextstep.courses.tobe.domain.RecruitmentStatus.OPEN;
import static nextstep.courses.tobe.domain.TobeSession.NOT_ALLOWED_PROCESS_ENDED_RECRUITMENT_OPEN_MESSAGE;
import static nextstep.courses.tobe.domain.session.TobeCoverImageTest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TobeSessionTest {
    private DateRange dateRange;
    private List<TobeCoverImage> coverImages;
    private ImageFileSize imageFileSize1;
    private ImageType imageType1;
    private ImageSize imageSize1;
    private ImageFileSize imageFileSize2;
    private ImageType imageType2;
    private ImageSize imageSize2;
    private List<TobeCoverImage> wrappedCoverImages;
    private ProcessStatus processStatus;
    private RecruitmentStatus recruitmentStatus;
    private TobeCoverImage tobeCoverImage1;
    private TobeCoverImage tobeCoverImage2;
    private TobeCoverImage wrappedTobeCoverImage1;
    private TobeCoverImage wrappedTobeCoverImage2;
    private long courseId;

    @BeforeEach
    void setUp() {
        courseId = CourseTest.C1.getId();
        dateRange = new DateRange(START, END);

        tobeCoverImage1 = new TobeCoverImage(SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200, 1L);
        tobeCoverImage2 = new TobeCoverImage(SIZE_512, IMAGE_TYPE_TEXT_JPG, WIDTH_450, HEIGHT_300, 1L);
        coverImages = List.of(tobeCoverImage1, tobeCoverImage2);

        imageFileSize1 = new ImageFileSize(SIZE_1024);
        imageType1 = IMAGE_TYPE_GIF;
        imageSize1 = new ImageSize(WIDTH_300, HEIGHT_200);

        imageFileSize2 = new ImageFileSize(SIZE_512);
        imageType2 = IMAGE_TYPE_JPG;
        imageSize2 = new ImageSize(WIDTH_450, HEIGHT_300);

        wrappedTobeCoverImage1 = new TobeCoverImage(imageFileSize1, imageType1, imageSize1, 1L);
        wrappedTobeCoverImage2 = new TobeCoverImage(imageFileSize2, imageType2, imageSize2, 1L);
        wrappedCoverImages = List.of(wrappedTobeCoverImage1, wrappedTobeCoverImage2);

        processStatus = READY;
        recruitmentStatus = CLOSED;
    }

    @Test
    void createFreeSession_성공() {
        TobeFreeSession actual = new TobeFreeSession(1L, courseId, dateRange, coverImages, IN1,
                processStatus, recruitmentStatus,
                1L, LocalDateTime.now(), LocalDateTime.now());
        TobeFreeSession expected = new TobeFreeSession(1L, courseId, dateRange, wrappedCoverImages, IN1,
                processStatus, recruitmentStatus,
                1L, LocalDateTime.now(), LocalDateTime.now());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void createFreeSession_ENDED_OPEN_실패() {
        assertThatThrownBy(() -> {
            new TobeFreeSession(1L,
                    courseId, dateRange, coverImages, IN1,
                    ENDED, OPEN,
                    1L, LocalDateTime.now(), LocalDateTime.now());
        }).isInstanceOf(ProcessEndedException.class)
                .hasMessage(NOT_ALLOWED_PROCESS_ENDED_RECRUITMENT_OPEN_MESSAGE);
    }

    @Test
    void createPaidSession_성공() {
        TobePaidSession actual = new TobePaidSession(1L,
                courseId, dateRange, coverImages, IN1,
                processStatus, recruitmentStatus,
                MAX_REGISTER_COUNT, SESSION_AMOUNT,
                1L, LocalDateTime.now(), LocalDateTime.now());
        TobePaidSession expected = new TobePaidSession(1L,
                courseId, dateRange, wrappedCoverImages, IN1,
                processStatus, recruitmentStatus,
                MAX_REGISTER_COUNT, SESSION_AMOUNT,
                1L, LocalDateTime.now(), LocalDateTime.now());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void createPaidSession_ENDED_OPEN_실패() {
        assertThatThrownBy(() -> {
                    new TobePaidSession(1L,
                            courseId, dateRange, coverImages, IN1,
                            ENDED, OPEN,
                            MAX_REGISTER_COUNT, SESSION_AMOUNT,
                            1L, LocalDateTime.now(), LocalDateTime.now());
                }).isInstanceOf(ProcessEndedException.class)
                .hasMessage(NOT_ALLOWED_PROCESS_ENDED_RECRUITMENT_OPEN_MESSAGE);
    }
}