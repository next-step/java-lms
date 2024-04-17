package nextstep.payments.domain;

import java.util.ArrayList;
import java.util.List;

public class Payments {

    private final List<Payment> payments = new ArrayList<>();

    public Payments() {}

    public Payments(List<Payment> payments) {
        this.payments.addAll(payments);
    }

    public void add(Payment payment) {
        this.payments.add(payment);
    }

    public boolean contains(Payment payment) {
        return this.payments.contains(payment);
    }

}
