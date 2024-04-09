package nextstep.courses.domain;

import nextstep.courses.domain.enums.SessionStatus;
import nextstep.courses.domain.enums.SessionType;
import nextstep.payments.domain.Payment;

public class PaySession extends Session {
    private static final String SESSION_NOT_RECRUITING = "해당 강의는 현재 모집 중이 아닙니다.";
    private static final String PAYMENT_AMOUNT_WRONG = "수강료가 일치하지 않습니다.";
    private static final String NUMBER_OF_STUDENTS_IS_FULL = "해당 강의는 정원 마감입니다.";

    private final Long price;
    private final int maxNumberOfStudents;

    public static PaySession of(Long id, SessionDate sessionDate, Long price, int maxNumberOfStudents, CoverImageInfo coverImageInfo) {
        return new PaySession(id, sessionDate, price, maxNumberOfStudents, coverImageInfo);
    }

    public static PaySession of(Long id, SessionDate sessionDate, Long price, int maxNumberOfStudents) {
        return new PaySession(id, sessionDate, price, maxNumberOfStudents, null);
    }

    private PaySession(Long id, SessionDate sessionDate, Long price, int maxNumberOfStudents, CoverImageInfo coverImageInfo) {
        super(id, sessionDate, coverImageInfo, SessionType.PAY);
        this.price = price;
        this.maxNumberOfStudents = maxNumberOfStudents;
    }

    private PaySession(Long id, SessionDate sessionDate, SessionStatus sessionStatus, int numberOfStudents, int maxNumberOfStudents, CoverImageInfo coverImageInfo, SessionType type, Long price) {
        super(id, sessionDate, sessionStatus, numberOfStudents, coverImageInfo, type);
        this.price = price;
        this.maxNumberOfStudents = maxNumberOfStudents;
    }

    @Override
    public void enroll(Payment payment) {
        validateEnrollable(payment);
        numberOfStudents++;
    }

    private void validateEnrollable(Payment payment) {
        if (sessionStatus.isStatusNotRecruiting()) {
            throw new IllegalStateException(SESSION_NOT_RECRUITING);
        }
        if (payment.isDifferentAmount(price)) {
            throw new IllegalArgumentException(PAYMENT_AMOUNT_WRONG);
        }
        if (numberOfStudents >= maxNumberOfStudents) {
            throw new IllegalArgumentException(NUMBER_OF_STUDENTS_IS_FULL);
        }
    }

    public int getMaxNumberOfStudents() {
        return maxNumberOfStudents;
    }

    public Long getPrice() {
        return price;
    }

    public static PaySession.PaySessionBuilder builder() {
        return new PaySession.PaySessionBuilder();
    }

    public static class PaySessionBuilder {
        private Long id;
        private SessionDate sessionDate;
        private SessionStatus sessionStatus;
        private int numberOfStudents;
        private int maxNumberOfStudents;
        private CoverImageInfo coverImageInfo;
        private SessionType type;
        private Long price;

        public PaySession.PaySessionBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PaySession.PaySessionBuilder sessionDate(SessionDate sessionDate) {
            this.sessionDate = sessionDate;
            return this;
        }

        public PaySession.PaySessionBuilder sessionStatus(SessionStatus sessionStatus) {
            this.sessionStatus = sessionStatus;
            return this;
        }

        public PaySession.PaySessionBuilder numberOfStudents(int numberOfStudents) {
            this.numberOfStudents = numberOfStudents;
            return this;
        }

        public PaySession.PaySessionBuilder maxNumberOfStudents(int maxNumberOfStudents) {
            this.maxNumberOfStudents = maxNumberOfStudents;
            return this;
        }

        public PaySession.PaySessionBuilder coverImageInfo(CoverImageInfo coverImageInfo) {
            this.coverImageInfo = coverImageInfo;
            return this;
        }

        public PaySession.PaySessionBuilder type(SessionType type) {
            this.type = type;
            return this;
        }

        public PaySession.PaySessionBuilder price(Long price) {
            this.price = price;
            return this;
        }

        public PaySession build() {
            return new PaySession(id, sessionDate, sessionStatus, numberOfStudents, maxNumberOfStudents, coverImageInfo, type, price);
        }
    }
}
