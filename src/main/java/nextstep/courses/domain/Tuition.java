package nextstep.courses.domain;

public class Tuition {
	private final long tuition;

	public Tuition(long tuition) {
		this.tuition = tuition;
		isGreaterThanZero();
	}

	public void isEqual(long amount) {
		if (tuition != amount) {
			throw new IllegalArgumentException("강의 비용과 결제 금액이 일치하지 않습니다.");
		}
	}

	private void isGreaterThanZero() {
		if (tuition < 0) {
			throw new IllegalArgumentException("강의 신청 비용은 0보다 작을 수 없습니다.");
		}
	}
}
