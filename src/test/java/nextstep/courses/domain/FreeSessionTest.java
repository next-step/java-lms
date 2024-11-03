package nextstep.courses.domain;

import nextstep.courses.NotMatchedInstructorException;
import nextstep.courses.RecruitmentClosedException;
import nextstep.courses.domain.session.DateRange;
import nextstep.courses.domain.session.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static nextstep.courses.domain.ApprovedStatus.APPROVED;
import static nextstep.courses.domain.ApprovedStatus.DENIED;
import static nextstep.courses.domain.CourseTest.*;
import static nextstep.courses.domain.CoverImageTest.*;
import static nextstep.courses.domain.InstructorTest.IN1;
import static nextstep.courses.domain.InstructorTest.IN2;
import static nextstep.courses.domain.ProcessStatus.READY;
import static nextstep.courses.domain.RecruitmentStatus.CLOSED;
import static nextstep.courses.domain.RecruitmentStatus.OPEN;
import static nextstep.courses.domain.SelectedStatus.REJECTED;
import static nextstep.courses.domain.SelectedStatus.SELECTED;
import static nextstep.courses.domain.Session.*;
import static nextstep.courses.domain.session.DateRangeTest.END;
import static nextstep.courses.domain.session.DateRangeTest.START;
import static nextstep.users.domain.NsUserTest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FreeSessionTest {
    public static final FreeSession FS1 = new FreeSession(1L,
            C1.getId(),
            new DateRange(START, END),
            new CoverImage(1L, SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200), Status.PREPARE,
            List.of(new CoverImage(1L, SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200)), IN1, READY, CLOSED,
            1L, LocalDateTime.of(2024, 10, 26, 10, 0), LocalDateTime.of(2024, 11, 26, 10, 0)
    );

    private FreeSession freeSession;
    private long courseId;
    private DateRange dateRange;
    private List<CoverImage> coverImages;
    private Student studentSelectedDenied1;
    private Student studentSelectedApproved1;
    private Student studentRejectApproved1;
    private Student studentRejectDenied1;
    private Student studentSelectedDenied2;
    private Student studentSelectedApproved2;
    private Student studentRejectApproved2;
    private Student studentRejectDenied2;
    private Student studentSelectedDenied3;
    private Student studentSelectedApproved3;
    private Student studentRejectApproved3;
    private Student studentRejectDenied3;
    private CoverImage coverImage;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @BeforeEach
    void setUp() {
        courseId = C1.getId();
        dateRange = new DateRange(START, END);
        coverImages = List.of(new CoverImage(1L, SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200));

        createdAt = LocalDateTime.of(2024, 10, 26, 10, 0);
        updatedAt = LocalDateTime.of(2024, 11, 26, 10, 0);

        freeSession = new FreeSession(1L,
                courseId, dateRange,
                new CoverImage(1L, SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200), Status.PREPARE,
                coverImages, IN1, READY, OPEN,
                1L, LocalDateTime.now(), LocalDateTime.now());

        studentSelectedDenied1 = new Student(freeSession, JAVAJIGI, SELECTED, DENIED, LocalDateTime.now());
        studentSelectedApproved1 = new Student(freeSession, JAVAJIGI, SELECTED, APPROVED, LocalDateTime.now());
        studentRejectApproved1 = new Student(freeSession, JAVAJIGI, REJECTED, APPROVED, LocalDateTime.now());
        studentRejectDenied1 = new Student(freeSession, JAVAJIGI, REJECTED, DENIED, LocalDateTime.now());

        studentSelectedDenied2 = new Student(freeSession, SANJIGI, SELECTED, DENIED, LocalDateTime.now());
        studentSelectedApproved2 = new Student(freeSession, SANJIGI, SELECTED, APPROVED, LocalDateTime.now());
        studentRejectApproved2 = new Student(freeSession, SANJIGI, REJECTED, APPROVED, LocalDateTime.now());
        studentRejectDenied2 = new Student(freeSession, SANJIGI, REJECTED, DENIED, LocalDateTime.now());

        studentSelectedDenied3 = new Student(freeSession, THIRDJIGI, SELECTED, DENIED, LocalDateTime.now());
        studentSelectedApproved3 = new Student(freeSession, THIRDJIGI, SELECTED, APPROVED, LocalDateTime.now());
        studentRejectApproved3 = new Student(freeSession, THIRDJIGI, REJECTED, APPROVED, LocalDateTime.now());
        studentRejectDenied3 = new Student(freeSession, THIRDJIGI, REJECTED, DENIED, LocalDateTime.now());

        coverImage = new CoverImage(1L, SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200);
        status = Status.PREPARE;
    }

    @Test
    void register_성공() {
        FreeSession actual = new FreeSession(1L,
                courseId, dateRange,
                coverImage, status,
                coverImages, IN1, READY, OPEN,
                1L, LocalDateTime.now(), LocalDateTime.now());
        freeSession.register(new Student(
                freeSession,
                JAVAJIGI,
                REJECTED, DENIED,
                START)
        );
        assertThat(actual).isNotEqualTo(freeSession);

        actual.register(new Student(
                freeSession,
                JAVAJIGI,
                REJECTED, DENIED,
                START));
        assertThat(actual).isEqualTo(freeSession);
    }

    @Test
    void register_CLOSED_실패() {
        FreeSession actual = new FreeSession(1L,
                courseId, dateRange,
                coverImage, status,
                coverImages, IN1, READY, CLOSED,
                1L, LocalDateTime.now(), LocalDateTime.now());

        assertThatThrownBy(() -> {
            actual.register(new Student(
                    freeSession,
                    JAVAJIGI,
                    REJECTED, DENIED,
                    START)
            );
        }).isInstanceOf(RecruitmentClosedException.class)
                .hasMessage(NOT_ALLOWED_REGISTER_TO_CLOSED_SESSION_MESSAGE);
    }

    @Test
    void approveAll() {
        freeSession.register(studentSelectedDenied1);
        freeSession.register(studentSelectedDenied2);
        freeSession.register(studentSelectedApproved3);
        freeSession.approveAll(IN1);

        FreeSession expected = new FreeSession(1L,
                courseId, dateRange,
                coverImage, status,
                coverImages, IN1, READY, OPEN,
                1L, createdAt, updatedAt);
        expected.register(studentSelectedApproved1);
        expected.register(studentSelectedApproved2);
        expected.register(studentSelectedApproved3);

        assertThat(freeSession).isEqualTo(expected);
    }

    @Test
    void approveAll_등록된_강사가아니면_실패() {
        freeSession.register(studentSelectedDenied1);
        freeSession.register(studentSelectedDenied2);
        freeSession.register(studentSelectedDenied3);
        assertThatThrownBy(() -> {
            freeSession.approveAll(IN2);
        })
                .isInstanceOf(NotMatchedInstructorException.class)
                .hasMessage(NO_AUTH_INSTRUCTOR_TO_UPDATE_APPROVE_STATUS_MESSAGE);
    }

    @Test
    void deniedAll() {
        freeSession.register(studentRejectApproved1);
        freeSession.register(studentRejectDenied2);
        freeSession.register(studentRejectApproved3);
        freeSession.deniedAll(IN1);

        FreeSession expected = new FreeSession(1L,
                courseId, dateRange,
                coverImage, status,
                coverImages, IN1, READY, OPEN,
                1L, createdAt, updatedAt);
        expected.register(studentRejectDenied1);
        expected.register(studentRejectDenied2);
        expected.register(studentRejectDenied3);

        assertThat(freeSession).isEqualTo(expected);
    }

    @Test
    void deniedAll_등록된_강사가_아니면_실패() {
        freeSession.register(studentRejectApproved1);
        freeSession.register(studentRejectApproved2);
        freeSession.register(studentRejectApproved3);

        assertThatThrownBy(() -> {
            freeSession.deniedAll(IN2);
        })
                .isInstanceOf(NotMatchedInstructorException.class)
                .hasMessage(NO_AUTH_INSTRUCTOR_TO_UPDATE_DENIED_STATUS_MESSAGE);
    }
}