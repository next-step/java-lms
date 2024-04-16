package nextstep.courses.domain;

import nextstep.courses.domain.enums.SessionType;
import nextstep.payments.domain.Payment;

public class PaySession extends Session {
    private static final String SESSION_NOT_RECRUITING = "해당 강의는 현재 모집 중이 아닙니다.";
    private static final String PAYMENT_AMOUNT_WRONG = "수강료가 일치하지 않습니다.";
    private static final String NUMBER_OF_STUDENTS_IS_FULL = "해당 강의는 정원 마감입니다.";

    private final Long price;
    private final int maxNumberOfStudents;

    public static PaySession createNewInstance(Course course, SessionInfos sessionInfos, int maxNumberOfStudents, Long price) {
        return new PaySession(0L, course, sessionInfos, 0, maxNumberOfStudents, price);
    }

    public static PaySession createFromData(Long id, Course course, SessionInfos sessionInfos, int numberOfStudents, int maxNumberOfStudents, Long price) {
        return new PaySession(id, course, sessionInfos, numberOfStudents, maxNumberOfStudents, price);
    }

    private PaySession(Long id, Course course, SessionInfos sessionInfos, int numberOfStudents, int maxNumberOfStudents, Long price) {
        super(id, course, sessionInfos, SessionType.PAY, numberOfStudents);
        this.price = price;
        this.maxNumberOfStudents = maxNumberOfStudents;
    }

    @Override
    public void enroll(Payment payment) {
        validateEnrollable(payment);
        numberOfStudents++;
    }

    private void validateEnrollable(Payment payment) {
        if (sessionInfos.isStatusNotRecruiting()) {
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

}
