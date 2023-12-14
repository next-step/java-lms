package nextstep.courses.domain.session;

import nextstep.courses.MaxStudentsNumberExceededException;
import nextstep.courses.NotEnrollmentPeriodException;
import nextstep.courses.PriceMismatchException;
import nextstep.courses.domain.Course;
import nextstep.courses.domain.coverimage.CoverImage;
import nextstep.payments.domain.Payment;

public class Session {
    private static final String ERR_NOT_PERIOD = "해당 강의는 수강신청 기간이 아닙니다.";
    private static final String ERR_MAX_STUDENTS_EXCEEDED = String.format("해당 강의의 수강인원이 모두 차서 더 이상 등록할 수 없습니다.");
    private static final String ERR_PRICE_MISMATCH = "결제한 금액이 수강료(%d원)와 일치하지 않습니다.";


    private final Long id;
    private Course course;
    private CoverImage coverImage;
    private final Boolean isFree;
    private final Long maxStudentsNumber;
    private final Long price;
    private final Students students = new Students();
    private final Schedule schedule;


    public Session(Long maxStudentsNumber, Long price) {
        this(0L, new CoverImage(), false, maxStudentsNumber, price, new Schedule());
    }

    public Session(Schedule schedule) {
        this(0L, new CoverImage(), true, 0L, 0L, schedule);
    }

    public Session(Boolean isFree, Schedule schedule) {
        this(0L, new CoverImage(), isFree, 0L, 0L, schedule);
    }

    public Session(Long id, CoverImage image, Boolean isFree, Long maxStudentsNumber, Long price, Schedule schedule) {
        this.id = id;
        this.coverImage = image;
        this.isFree = isFree;
        this.maxStudentsNumber = maxStudentsNumber;
        this.price = price;
        this.schedule = schedule;
    }


    public void enroll(Payment payment) throws Exception {
        if (!this.isFree) {
            validateMaxStudentsNumber();
            validatePayment(payment);
        }
        validateEnrollmentPeriod(this.schedule);
        this.students.add(payment.userId());
    }

    private void validateEnrollmentPeriod(Schedule schedule) throws NotEnrollmentPeriodException {
        if (!schedule.isEnrollmentPeriod()) {
            throw new NotEnrollmentPeriodException(ERR_NOT_PERIOD);
        }
    }

    private void validateMaxStudentsNumber() throws MaxStudentsNumberExceededException {
        if (this.students.isGreaterThanOrEqualTo(this.maxStudentsNumber)) {
            throw new MaxStudentsNumberExceededException(ERR_MAX_STUDENTS_EXCEEDED);
        }
    }

    private void validatePayment(Payment payment) throws PriceMismatchException {
        if (!payment.match(this.price)) {
            throw new PriceMismatchException(String.format(ERR_PRICE_MISMATCH, this.price));
        }
    }

    public void toCourse(Course course) {
        this.course = course;
    }
}
