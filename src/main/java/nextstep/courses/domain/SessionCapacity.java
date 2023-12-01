package nextstep.courses.domain;

public class SessionCapacity {
	private final int maximumCapacity;

	public SessionCapacity(int maximumCapacity) {
		this.maximumCapacity = maximumCapacity;
	}

	public void isOver(int numberOfStudents) {
		if (numberOfStudents > maximumCapacity) {
			throw new IllegalArgumentException("최대 수강 인원을 초과했습니다.");
		}
	}
}
