package nextstep.payments.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Payments {

    private List<Payment> values = new ArrayList<>();

    public Payments() {
        this.values = new ArrayList<>();
    }

    public Payments(List<Payment> values) {
        this.values = values;
    }
}
