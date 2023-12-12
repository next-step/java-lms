package nextstep.courses.domain.session;

import nextstep.courses.MaxStudentsNumberExceededException;
import nextstep.courses.PriceMismatchException;
import nextstep.courses.domain.coverimage.CoverImage;
import nextstep.payments.domain.Payment;

import java.time.LocalDate;

public class PaidSession extends Session {
    private static final String ERR_MAX_STUDENTS_EXCEEDED = String.format("해당 강의의 수강인원이 모두 차서 더 이상 등록할 수 없습니다.");
    private static final String ERR_PRICE_MISMATCH = "결제한 금액이 수강료(%d원)와 일치하지 않습니다.";

    private final int maxStudentsNumber;
    private final int price;


    public PaidSession(int maxStudentsNumber) {
        this(maxStudentsNumber, 0);
    }

    public PaidSession(int maxStudentsNumber, int price) {
        this(null, LocalDate.now(), LocalDate.now(), maxStudentsNumber, price);
    }

    public PaidSession(CoverImage image, LocalDate startDate, LocalDate endDate, int maxStudentsNumber, int price) {
        super(image, startDate, endDate);
        this.maxStudentsNumber = maxStudentsNumber;
        this.price = price;
    }


    @Override
    public void enroll(Payment payment) throws Exception {
        validateMaxStudentsNumber();
        validatePayment(payment);
        super.enroll(payment);
    }

    private void validateMaxStudentsNumber() throws MaxStudentsNumberExceededException {
        if (this.studentsId.size() >= this.maxStudentsNumber) {
            throw new MaxStudentsNumberExceededException(ERR_MAX_STUDENTS_EXCEEDED);
        }
    }

    private void validatePayment(Payment payment) throws PriceMismatchException {
        if (!payment.match(this.price)) {
            throw new PriceMismatchException(String.format(ERR_PRICE_MISMATCH, this.price));
        }
    }
}
