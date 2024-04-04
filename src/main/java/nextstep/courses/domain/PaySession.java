package nextstep.courses.domain;

import nextstep.courses.domain.enums.SessionStatus;
import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

public class PaySession extends Session {
    private static final String SESSION_NOT_RECRUITING = "해당 강의는 현재 모집 중이 아닙니다.";
    private static final String PAYMENT_AMOUNT_WRONG = "수강료가 일치하지 않습니다.";
    private static final String NUMBER_OF_STUDENTS_IS_FULL = "해당 강의는 정원 마감입니다.";

    private final Long price;
    private final int maxNumberOfStudents;

    public PaySession of(SessionDate sessionDate, Long price, int maxNumberOfStudents, CoverImageInfo coverImageInfo) {
        return new PaySession(sessionDate, price, maxNumberOfStudents, coverImageInfo);
    }

    public static PaySession of(SessionDate sessionDate, Long price, int maxNumberOfStudents) {
        return new PaySession(sessionDate, price, maxNumberOfStudents, null);
    }

    private PaySession(SessionDate sessionDate, Long price, int maxNumberOfStudents, CoverImageInfo coverImageInfo) {
        super(sessionDate, coverImageInfo);
        this.price = price;
        this.maxNumberOfStudents = maxNumberOfStudents;
    }

    @Override
    public void enroll(Payment payment) {
        if (sessionStatus != SessionStatus.READY) {
            throw new IllegalStateException(SESSION_NOT_RECRUITING);
        }
        if (payment.isDifferentAmount(price)) {
            throw new IllegalArgumentException(PAYMENT_AMOUNT_WRONG);
        }
        if (numberOfStudents >= maxNumberOfStudents) {
            throw new IllegalArgumentException(NUMBER_OF_STUDENTS_IS_FULL);
        }
        numberOfStudents++;
    }
}
