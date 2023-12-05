package nextstep.courses.domain.session.image;

public class ImageCapacity {
	private static final double MAX_CAPACITY = 1.0;
	private final double capacity;

	public ImageCapacity(Double capacity) {
		this.capacity = capacity;
		validate();
	}

	private void validate() {
		validateGreaterThanZero();
		validateOverCapacity();
	}
	private void validateOverCapacity() {
		if (capacity > MAX_CAPACITY) {
			throw new IllegalArgumentException("이미지 용량 제한을 초과하였습니다.");
		}
	}

	private void validateGreaterThanZero() {
		if (capacity < 0) {
			throw new IllegalArgumentException("이미지 용량은 0보다 작을 수 없습니다.");
		}
	}

	public double getCapacity() {
		return capacity;
	}
}
