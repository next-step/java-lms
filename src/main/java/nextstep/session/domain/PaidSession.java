package nextstep.session.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDate;

public class PaidSession extends Session {
    private final Integer limitNumberOfStudents;
    private final Long price;

    public PaidSession(Long creatorId, LocalDate startDate, LocalDate endDate, SessionImage sessionImage, Integer limitNumberOfStudents, Long price) {
        super(creatorId, startDate, endDate, sessionImage);
        this.limitNumberOfStudents = limitNumberOfStudents;
        this.price = price;
    }

    public static PaidSession create(Long creatorId, LocalDate startDate, LocalDate endDate, SessionImage sessionImage, Integer limitNumberOfStudents, Long price) {
        return new PaidSession(creatorId, startDate, endDate, sessionImage, limitNumberOfStudents, price);
    }

    @Override
    protected void validateCommonEnroll(NsUser nsUser) {
        validateLimitNumberOfStudents();
        validatePayment(nsUser);
    }

    private void validateLimitNumberOfStudents() {
        if (this.limitNumberOfStudents <= students.enrolledNumber()) {
            throw new IllegalStateException("수강신청 정원이 가득찼습니다.");
        }
    }

    private void validatePayment(NsUser user) {
        if (!user.getSessionPayment(this).getAmount().equals(price)) {
            throw new IllegalArgumentException("강의의 가격과 결제한 가격이 다릅니다.");
        }
    }
}
