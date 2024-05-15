package nextstep.session.domain;

import nextstep.session.InvalidEnrollmentPolicyException;

public class EnrollmentPolicy {

    private final static int MIN_ENROLLMENT = 0;
    private final static int MIN_FEE = 0;
    private final PriceType priceType;
    private final int maxEnrollment;
    private final int fee;


    public EnrollmentPolicy(PriceType priceType, int maxEnrollment, int fee) {
        if (!validate(maxEnrollment, fee)) {
            throw new InvalidEnrollmentPolicyException(
                String.format("최소수강인원은 %d명 이상, 최소 수강료는 %d원 이상이여야 합니다.", MIN_ENROLLMENT, MIN_FEE));
        }
        this.priceType = priceType;
        this.maxEnrollment = maxEnrollment;
        this.fee = fee;
    }

    public EnrollmentPolicy(String priceType, int maxEnrollment, int fee) {
        this(PriceType.convert(priceType), maxEnrollment, fee);
    }


    private boolean validate(int maxEnrollment, int fee) {
        if (maxEnrollment < MIN_ENROLLMENT || fee < MIN_FEE) {
            return false;
        }
        return true;
    }


    public boolean isCapacityFull(int enrolledStudentCount) {
        if (isPriceTypeFree()) {
            return false;
        }
        return maxEnrollment <= enrolledStudentCount;
    }

    public boolean isPaymentCorrect(int payment) {
        if (isPriceTypeFree()) {
            return true;
        }
        return payment == fee;
    }

    private boolean isPriceTypeFree() {
        return priceType.isFree();
    }

    public String getPriceType() {
        return priceType.toString();
    }

    public int getMaxEnrollment() {
        return maxEnrollment;
    }

    public int getFee() {
        return fee;
    }
}
