package nextstep.courses.domain;

public class ImageShape {
	private static final Long MIN_WIDTH = 300L;
	private static final Long MIN_HEIGHT = 200L;
	private static final Long WIDTH_RATIO = 3L;
	private static final Long HEIGHT_RATIO = 2L;

	private long width;
	private long height;

	public ImageShape(long width, long height) {
		validate(width, height);
		this.width = width;
		this.height = height;
	}

	private void validate(long width, long height) {
		if (width < MIN_WIDTH) {
			throw new IllegalArgumentException("이미지의 width는 300픽셀 이상이어야 합니다.");
		}
		if (height < MIN_HEIGHT) {
			throw new IllegalArgumentException("이미지의 height는 200픽셀 이상이어야 합니다.");
		}
		checkRatio(width, height);
	}

	private void checkRatio(long width, long height) {
		if (!isCorrectRatio(width, height)) {
			throw new IllegalArgumentException("이미지 width와 height의 비율은 3:2여야 합니다.");
		}
	}

	private boolean isCorrectRatio(long width, long height) {
		return (width * HEIGHT_RATIO) == (height * WIDTH_RATIO);
	}

	public long width() {
		return width;
	}

	public long height() {
		return height;
	}

	@Override
	public String toString() {
		return "ImageShape{" +
				"width=" + width +
				", height=" + height +
				'}';
	}
}
