package nextstep.payments.domain;

import java.util.ArrayList;
import java.util.List;

public class Payments {

    private final List<Payment> payments = new ArrayList<>();

    public Payments() {
    }

    public void add(Payment payment){
        payments.add(payment);
    }
}
