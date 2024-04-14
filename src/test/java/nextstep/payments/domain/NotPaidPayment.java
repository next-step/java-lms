package nextstep.payments.domain;

public class NotPaidPayment extends Payment{

    @Override
    public boolean isPaid() {
        return false;
    }
}
