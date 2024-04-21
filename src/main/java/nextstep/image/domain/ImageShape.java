package nextstep.image.domain;

public class ImageShape {
	private ImageWidth width;
	private ImageHeight height;

	public ImageShape(ImageWidth width, ImageHeight height) {
		validateShape(width, height);
		this.width = width;
		this.height = height;
	}

	private static void validateShape(final ImageWidth width, final ImageHeight height) {
		if (width.getWidth() * 2 != height.getHeight() * 3) {
			throw new IllegalArgumentException("가로 세로 비율은 3:2이어야 합니다.");
		}
	}
}
