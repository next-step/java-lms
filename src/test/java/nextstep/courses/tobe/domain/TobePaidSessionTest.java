package nextstep.courses.tobe.domain;

import nextstep.courses.MaxStudentCapacityException;
import nextstep.courses.domain.CourseTest;
import nextstep.courses.domain.PaidSession;
import nextstep.courses.domain.session.CoverImage;
import nextstep.courses.domain.session.DateRange;
import nextstep.courses.domain.session.Status;
import nextstep.courses.tobe.domain.session.TobeCoverImage;
import nextstep.payments.PaymentMismatchException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.PaidSession.MAX_STUDENT_CAPACITY_MESSAGE;
import static nextstep.courses.domain.PaidSession.PAYMENT_MISMATCH_MESSAGE;
import static nextstep.courses.domain.session.CoverImageTest.*;
import static nextstep.courses.domain.session.DateRangeTest.END;
import static nextstep.courses.domain.session.DateRangeTest.START;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TobePaidSessionTest {

    public static final int MAX_REGISTER_COUNT = 30;
    public static final long SESSION_AMOUNT = 10000L;
    public static final TobePaidSession TPS1 = new TobePaidSession(1L,
            CourseTest.C1.getId(),
            new DateRange(START, END),
            new TobeCoverImage(SIZE, IMAGE_TYPE_TEXT, WIDTH, HEIGHT, 1L),
            ProcessStatus.READY,
            RecruitmentStatus.CLOSED,
            MAX_REGISTER_COUNT,
            SESSION_AMOUNT,
            1L,
            START,
            START);
}
