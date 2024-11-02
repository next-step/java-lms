package nextstep.courses.tobe.domain;

import nextstep.courses.domain.CourseTest;
import nextstep.courses.domain.session.DateRange;
import nextstep.courses.domain.session.image.ImageFileSize;
import nextstep.courses.domain.session.image.ImageSize;
import nextstep.courses.domain.session.image.ImageType;
import nextstep.courses.tobe.domain.session.TobeCoverImage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static nextstep.courses.domain.PaidSessionTest.MAX_REGISTER_COUNT;
import static nextstep.courses.domain.PaidSessionTest.SESSION_AMOUNT;
import static nextstep.courses.domain.session.CoverImageTest.*;
import static nextstep.courses.domain.session.DateRangeTest.END;
import static nextstep.courses.domain.session.DateRangeTest.START;
import static nextstep.courses.tobe.domain.TobeSession.NOT_ALLOWED_PROCESS_ENDED_RECRUITMENT_OPEN_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TobeSessionTest {
    private DateRange dateRange;
    private TobeCoverImage coverImage;
    private ImageFileSize imageFileSize;
    private ImageType imageType;
    private ImageSize imageSize;
    private TobeCoverImage wrappedCoverImage;
    private ProcessStatus processStatus;
    private RecruitmentStatus recruitmentStatus;

    @BeforeEach
    void setUp() {
        dateRange = new DateRange(START, END);
        coverImage = new TobeCoverImage(SIZE, IMAGE_TYPE_TEXT, WIDTH, HEIGHT, 1L);

        imageFileSize = new ImageFileSize(SIZE);
        imageType = ImageType.toImageType(IMAGE_TYPE_TEXT);
        imageSize = new ImageSize(WIDTH, HEIGHT);

        wrappedCoverImage = new TobeCoverImage(
                imageFileSize,
                imageType,
                imageSize,
                1L
        );

        processStatus = ProcessStatus.READY;
        recruitmentStatus = RecruitmentStatus.CLOSED;
    }

    @Test
    void createFreeSession_성공() {
        TobeFreeSession actual = new TobeFreeSession(1L, CourseTest.C1.getId(), dateRange, coverImage,
                processStatus, recruitmentStatus,
                1L, LocalDateTime.now(), LocalDateTime.now());
        TobeFreeSession expected = new TobeFreeSession(1L, CourseTest.C1.getId(), dateRange, wrappedCoverImage,
                processStatus, recruitmentStatus,
                1L, LocalDateTime.now(), LocalDateTime.now());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void createFreeSession_ENDED_OPEN_실패() {
        assertThatThrownBy(() -> {
            new TobeFreeSession(1L, CourseTest.C1.getId(), dateRange, coverImage,
                    ProcessStatus.ENDED, RecruitmentStatus.OPEN,
                    1L, LocalDateTime.now(), LocalDateTime.now());
        }).isInstanceOf(ProcessEndedException.class)
                .hasMessage(NOT_ALLOWED_PROCESS_ENDED_RECRUITMENT_OPEN_MESSAGE);
    }

    @Test
    void createPaidSession_성공() {
        TobePaidSession actual = new TobePaidSession(1L, CourseTest.C1.getId(), dateRange, coverImage,
                processStatus, recruitmentStatus,
                MAX_REGISTER_COUNT, SESSION_AMOUNT,
                1L, LocalDateTime.now(), LocalDateTime.now());
        TobePaidSession expected = new TobePaidSession(1L, CourseTest.C1.getId(), dateRange, wrappedCoverImage,
                processStatus, recruitmentStatus,
                MAX_REGISTER_COUNT, SESSION_AMOUNT,
                1L, LocalDateTime.now(), LocalDateTime.now());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void createPaidSession_ENDED_OPEN_실패() {
        assertThatThrownBy(() -> {
                    new TobePaidSession(1L, CourseTest.C1.getId(), dateRange, coverImage,
                            ProcessStatus.ENDED, RecruitmentStatus.OPEN,
                            MAX_REGISTER_COUNT, SESSION_AMOUNT,
                            1L, LocalDateTime.now(), LocalDateTime.now());
                }).isInstanceOf(ProcessEndedException.class)
                .hasMessage(NOT_ALLOWED_PROCESS_ENDED_RECRUITMENT_OPEN_MESSAGE);
    }
}