package nextstep.courses.domain;

import nextstep.courses.domain.session.DateRange;
import nextstep.courses.domain.session.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static nextstep.courses.domain.CoverImageTest.*;
import static nextstep.courses.domain.session.DateRangeTest.END;
import static nextstep.courses.domain.session.DateRangeTest.START;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static org.assertj.core.api.Assertions.assertThat;

public class FreeSessionTest {
    public static final FreeSession FS1 = new FreeSession(1L,
            CourseTest.C1.getId(),
            new DateRange(START, END),
            new CoverImage(SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200),
            Status.PREPARE,
            1L,
            START,
            START);

    private FreeSession freeSession;

    @BeforeEach
    void setUp() {
        freeSession = new FreeSession(1L,
                CourseTest.C1.getId(),
                new DateRange(START, END),
                new CoverImage(SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200),
                Status.PREPARE,
                1L,
                LocalDateTime.now(),
                LocalDateTime.now());
    }

    @Test
    void register_성공() {
        FreeSession actual = new FreeSession(1L,
                CourseTest.C1.getId(),
                new DateRange(START, END),
                new CoverImage(SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200),
                Status.PREPARE,
                1L,
                LocalDateTime.now(),
                LocalDateTime.now());
        freeSession.register(new Student(freeSession,JAVAJIGI, START));
        assertThat(actual).isNotEqualTo(freeSession);

        actual.register(new Student(freeSession,JAVAJIGI, START));
        assertThat(actual).isEqualTo(freeSession);
    }
}