package nextstep.session.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDate;

public class PaidSession extends Session {
    public PaidSession(int generation, Long creatorId, LocalDate startDate, LocalDate endDate, SessionImage sessionImage, SessionType sessionType, Integer limitNumberOfStudents, Long price) {
        super(generation, creatorId, startDate, endDate, sessionImage, sessionType, limitNumberOfStudents, price);
    }

    public static PaidSession create(int generation, Long creatorId, LocalDate startDate, LocalDate endDate, SessionImage sessionImage, SessionType sessionType, Integer limitNumberOfStudents, Long price) {
        return new PaidSession(generation, creatorId, startDate, endDate, sessionImage, sessionType, limitNumberOfStudents, price);
    }

    @Override
    public void enroll(NsUser user) {
        validateStatus();
        validateLimitNumberOfStudents();
        validatePayment(user);
        students.add(user);
    }

    private void validateStatus() {
        if (sessionStatus != SessionStatus.RECRUITING) {
            throw new IllegalStateException("모집중인 강의만 신청 가능합니다.");
        }
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
