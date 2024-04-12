package nextstep.courses.domain;

public class ImageShape {
	private int width;
	private int height;

	public ImageShape(int width, int height) {
		validateShape(width, height);
		this.width = width;
		this.height = height;
	}

	private static void validateShape(final int width, final int height) {
		if(width >= 300) {
			throw new IllegalArgumentException("너비는 300px보다 커야 합니다.");
		}
		if(height >= 200) {
			throw new IllegalArgumentException("높이는 200px보다 커야 합니다.");
		}
		if (width * 2 != height * 3) {
			throw new IllegalArgumentException("가로 세로 비율은 3:2이어야 합니다.");
		}
	}
}
