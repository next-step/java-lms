package nextstep.courses.domain;

public class ImageCapacity {
	private static final double MAX_CAPACITY = 1.0;
	private final double capacity;

	public ImageCapacity(Double capacity) {
		this.capacity = capacity;
		isOverCapacity();
	}

	private void isOverCapacity() {
		if (capacity > MAX_CAPACITY) {
			throw new IllegalArgumentException("이미지 용량 제한을 초과하였습니다.");
		}
	}
}
