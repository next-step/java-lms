package nextstep.courses.domain;

public class SessionCapacity {
	private final int maximumCapacity;

	public SessionCapacity(int maximumCapacity) {
		this.maximumCapacity = maximumCapacity;
		isGreaterThanZero();
	}

	public void isGreaterThan(int numberOfStudents) {
		if (numberOfStudents >= maximumCapacity) {
			throw new IllegalArgumentException("최대 수강 인원을 초과했습니다.");
		}
	}

	private void isGreaterThanZero() {
		if (maximumCapacity < 0) {
			throw new IllegalArgumentException("최대 신청인원은 0보다 작을 수 없습니다.");
		}
	}
}
