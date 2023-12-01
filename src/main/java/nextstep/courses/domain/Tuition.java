package nextstep.courses.domain;

public class Tuition {
	private final long tuition;

	public Tuition(long tuition) {
		this.tuition = tuition;
	}

	public void isEqual(long amount) {
		if (tuition != amount) {
			throw new IllegalArgumentException("강의 비용과 결제 금액이 일치하지 않습니다.");
		}
	}
}
