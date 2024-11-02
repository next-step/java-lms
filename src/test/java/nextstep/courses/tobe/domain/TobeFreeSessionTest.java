package nextstep.courses.tobe.domain;

import nextstep.courses.domain.CourseTest;
import nextstep.courses.domain.session.DateRange;
import nextstep.courses.tobe.NotMatchedInstructorException;
import nextstep.courses.tobe.RecruitmentClosedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static nextstep.courses.domain.session.CoverImageTest.*;
import static nextstep.courses.domain.session.DateRangeTest.END;
import static nextstep.courses.domain.session.DateRangeTest.START;
import static nextstep.courses.tobe.domain.ApprovedStatus.APPROVED;
import static nextstep.courses.tobe.domain.ApprovedStatus.DENIED;
import static nextstep.courses.tobe.domain.InstructorTest.IN1;
import static nextstep.courses.tobe.domain.InstructorTest.IN2;
import static nextstep.courses.tobe.domain.ProcessStatus.READY;
import static nextstep.courses.tobe.domain.RecruitmentStatus.CLOSED;
import static nextstep.courses.tobe.domain.RecruitmentStatus.OPEN;
import static nextstep.courses.tobe.domain.SelectedStatus.REJECTED;
import static nextstep.courses.tobe.domain.SelectedStatus.SELECTED;
import static nextstep.courses.tobe.domain.TobeSession.*;
import static nextstep.users.domain.NsUserTest.*;
import static nextstep.users.domain.NsUserTest.THIRDJIGI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TobeFreeSessionTest {
    public static final TobeFreeSession TFS1 = new TobeFreeSession(1L,
            CourseTest.C1.getId(),
            new DateRange(START, END),
            List.of(new TobeCoverImage(SIZE, IMAGE_TYPE_TEXT, WIDTH, HEIGHT, 1L)),
            IN1,
            READY, CLOSED,
            1L, START, START);

    private TobeFreeSession freeSession;
    private long courseId;
    private DateRange dateRange;
    private List<TobeCoverImage> coverImages;
    private TobeStudent studentSelectedDenied1;
    private TobeStudent studentSelectedApproved1;
    private TobeStudent studentRejectApproved1;
    private TobeStudent studentRejectDenied1;
    private TobeStudent studentSelectedDenied2;
    private TobeStudent studentSelectedApproved2;
    private TobeStudent studentRejectApproved2;
    private TobeStudent studentRejectDenied2;
    private TobeStudent studentSelectedDenied3;
    private TobeStudent studentSelectedApproved3;
    private TobeStudent studentRejectApproved3;
    private TobeStudent studentRejectDenied3;

    @BeforeEach
    void setUp() {
        courseId = CourseTest.C1.getId();
        dateRange = new DateRange(START, END);
        coverImages = List.of(new TobeCoverImage(SIZE, IMAGE_TYPE_TEXT, WIDTH, HEIGHT, 1L));

        freeSession = new TobeFreeSession(1L,
                courseId, dateRange, coverImages, IN1,
                READY, OPEN,
                1L, LocalDateTime.now(), LocalDateTime.now());

        studentSelectedDenied1 = new TobeStudent(freeSession, JAVAJIGI, SELECTED, DENIED, LocalDateTime.now());
        studentSelectedApproved1 = new TobeStudent(freeSession, JAVAJIGI, SELECTED, APPROVED, LocalDateTime.now());
        studentRejectApproved1 = new TobeStudent(freeSession, JAVAJIGI, REJECTED, APPROVED, LocalDateTime.now());
        studentRejectDenied1 = new TobeStudent(freeSession, JAVAJIGI, REJECTED, DENIED, LocalDateTime.now());

        studentSelectedDenied2 = new TobeStudent(freeSession, SANJIGI, SELECTED, DENIED, LocalDateTime.now());
        studentSelectedApproved2 = new TobeStudent(freeSession, SANJIGI, SELECTED, APPROVED, LocalDateTime.now());
        studentRejectApproved2 = new TobeStudent(freeSession, SANJIGI, REJECTED, APPROVED, LocalDateTime.now());
        studentRejectDenied2 = new TobeStudent(freeSession, SANJIGI, REJECTED, DENIED, LocalDateTime.now());

        studentSelectedDenied3 = new TobeStudent(freeSession, THIRDJIGI, SELECTED, DENIED, LocalDateTime.now());
        studentSelectedApproved3 = new TobeStudent(freeSession, THIRDJIGI, SELECTED, APPROVED, LocalDateTime.now());
        studentRejectApproved3 = new TobeStudent(freeSession, THIRDJIGI, REJECTED, APPROVED, LocalDateTime.now());
        studentRejectDenied3 = new TobeStudent(freeSession, THIRDJIGI, REJECTED, DENIED, LocalDateTime.now());
    }

    @Test
    void register_성공() {
        TobeFreeSession actual = new TobeFreeSession(1L,
                courseId, dateRange, coverImages, IN1,
                READY, OPEN,
                1L, LocalDateTime.now(), LocalDateTime.now());
        freeSession.register(new TobeStudent(
                freeSession,
                JAVAJIGI,
                REJECTED, DENIED,
                START)
        );
        assertThat(actual).isNotEqualTo(freeSession);

        actual.register(new TobeStudent(
                freeSession,
                JAVAJIGI,
                REJECTED, DENIED,
                START));
        assertThat(actual).isEqualTo(freeSession);
    }

    @Test
    void register_CLOSED_실패() {
        TobeFreeSession actual = new TobeFreeSession(1L,
                courseId, dateRange, coverImages, IN1,
                READY, CLOSED,
                1L, LocalDateTime.now(), LocalDateTime.now());

        assertThatThrownBy(() -> {
            actual.register(new TobeStudent(
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

        TobeFreeSession expected = new TobeFreeSession(1L,
                courseId, dateRange, coverImages, IN1,
                READY, OPEN,
                1L, START, START);
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

        TobeFreeSession expected = new TobeFreeSession(1L,
                courseId, dateRange, coverImages, IN1,
                READY, OPEN,
                1L, START, START);
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