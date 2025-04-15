package nextstep.payments.domain;

import java.util.ArrayList;
import java.util.List;

public class Payments {

    private List<Payment> payments = new ArrayList<>();

    public Payments() {
    }

    public Payments(List<Payment> payments) {
        this.payments = payments;
    }

    public void add(Payment payment) {
        payments.add(payment);
    }

    public boolean paidCorrectly(Long userId, Long amount) {
        Payment payment = get(userId);

        if (payment == null) {
            return false;
        }

        return payment.getAmount().equals(amount);
    }

    public boolean paidIncorrectly(Long userId, Long amount) {
        return !paidCorrectly(userId, amount);
    }

    private Payment get(Long userId) {
        return payments.stream()
                .filter(payment -> payment.getNsUserId().equals(userId))
                .findFirst()
                .orElse(null);
    }
}
