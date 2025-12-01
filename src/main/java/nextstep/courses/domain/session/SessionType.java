package nextstep.courses.domain.session;

public interface SessionType {

    public  boolean isFree();

    public  boolean isOverCapacity(int currentEnrollmentCount);

    public  boolean isValidPayment(Long paymentAmount);
}
