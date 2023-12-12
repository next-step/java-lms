package nextstep.courses.domain.session;

import nextstep.courses.NotEnrollmentPeriodException;
import nextstep.courses.domain.coverimage.CoverImage;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Session {
    private static final String ERR_NOT_PERIOD = "해당 강의는 수강신청 기간이 아닙니다.";

    final Long id;
    CoverImage coverImage;
    final List<Long> studentsId = new ArrayList<>();
    final LocalDate startDate;

    LocalDate endDate;


    public Session (LocalDate startDate, LocalDate endDate) {
        this(0L, null, startDate, endDate);
    }

    public Session(CoverImage image, LocalDate startDate, LocalDate endDate) {
        this(0L, image, startDate, endDate);
    }

    public Session(Long id, CoverImage image, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.coverImage = image;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public void enroll(Payment payment) throws Exception {
        validateEnrollmentStatus();
        this.studentsId.add(payment.userId());
    }

    private void validateEnrollmentStatus() throws NotEnrollmentPeriodException {
        if (!SessionStatus.isEnrollmentPeriod(this)) {
            throw new NotEnrollmentPeriodException(ERR_NOT_PERIOD);
        }
    }
}
