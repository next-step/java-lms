package nextstep.payments.domain;

public class PaidPayment extends Payment{

    @Override
    public boolean isPaid() {
        return true;
    }
}
