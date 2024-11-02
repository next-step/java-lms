package nextstep.courses.tobe.domain;

import nextstep.courses.domain.CourseTest;
import nextstep.courses.domain.FreeSession;
import nextstep.courses.domain.Student;
import nextstep.courses.domain.session.CoverImage;
import nextstep.courses.domain.session.DateRange;
import nextstep.courses.domain.session.Status;
import nextstep.courses.tobe.domain.session.TobeCoverImage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static nextstep.courses.domain.session.CoverImageTest.*;
import static nextstep.courses.domain.session.DateRangeTest.END;
import static nextstep.courses.domain.session.DateRangeTest.START;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static org.assertj.core.api.Assertions.assertThat;

public class TobeFreeSessionTest {
    public static final TobeFreeSession TFS1 = new TobeFreeSession(1L,
            CourseTest.C1.getId(),
            new DateRange(START, END),
            new TobeCoverImage(SIZE, IMAGE_TYPE_TEXT, WIDTH, HEIGHT, 1L),
            ProcessStatus.READY,
            RecruitmentStatus.CLOSED,
            1L,
            START,
            START);
}