package nextstep.courses.tobe.domain;

import nextstep.courses.domain.CourseTest;
import nextstep.courses.domain.session.DateRange;
import nextstep.courses.tobe.RecruitmentClosedException;
import nextstep.courses.tobe.domain.session.TobeCoverImage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static nextstep.courses.domain.session.CoverImageTest.*;
import static nextstep.courses.domain.session.DateRangeTest.END;
import static nextstep.courses.domain.session.DateRangeTest.START;
import static nextstep.courses.tobe.domain.ApprovedStatus.DENIED;
import static nextstep.courses.tobe.domain.InstructorTest.IN1;
import static nextstep.courses.tobe.domain.ProcessStatus.READY;
import static nextstep.courses.tobe.domain.RecruitmentStatus.CLOSED;
import static nextstep.courses.tobe.domain.RecruitmentStatus.OPEN;
import static nextstep.courses.tobe.domain.SelectedStatus.REJECTED;
import static nextstep.courses.tobe.domain.TobeSession.NOT_ALLOWED_REGISTER_TO_CLOSED_SESSION_MESSAGE;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
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

    @BeforeEach
    void setUp() {
        courseId = CourseTest.C1.getId();
        dateRange = new DateRange(START, END);
        coverImages = List.of(new TobeCoverImage(SIZE, IMAGE_TYPE_TEXT, WIDTH, HEIGHT, 1L));
        freeSession = new TobeFreeSession(1L,
                courseId, dateRange, coverImages, IN1,
                READY, OPEN,
                1L, LocalDateTime.now(), LocalDateTime.now());
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
}