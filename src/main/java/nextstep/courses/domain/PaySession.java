package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

public class PaySession extends Session {

    private final int maxCountOfStudents;

    private final int price;

    public PaySession(Image image, Period period, int maxCountOfStudents, int price) {
        super(image, period, SessionType.PAY);
        this.maxCountOfStudents = maxCountOfStudents;
        this.price = price;
    }

    public void enroll(NsUser student, int amountOfPayment) {
        if (this.maxCountOfStudents <= this.getStudents().size()) {
            throw new IllegalArgumentException("유료 강의는 강의 최대 수강 인원을 초과할 수 없다.");
        }
        if (price != amountOfPayment) {
            throw new IllegalArgumentException("유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능합니다.");
        }
        this.getStudents().add(student);
    }

    public int getMaxCountOfStudents() {
        return this.maxCountOfStudents;
    }
}
