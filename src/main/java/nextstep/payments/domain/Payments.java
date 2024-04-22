package nextstep.payments.domain;

import java.util.ArrayList;
import java.util.List;

public class Payments {
	private final List<Payment> payments;

	public Payments() {
		this.payments = new ArrayList<>();
	}

	public void add(Payment payment) {
		payments.add(payment);
	}

	public int size() {
		return payments.size();
	}
}
