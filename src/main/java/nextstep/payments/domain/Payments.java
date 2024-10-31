package nextstep.payments.domain;

import java.util.ArrayList;
import java.util.List;

public class Payments {
    private List<Payment> values = new ArrayList<>();

    public void add(Payment payment){
        values.add(payment);
    }
}
