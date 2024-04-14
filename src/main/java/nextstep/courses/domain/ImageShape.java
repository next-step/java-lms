package nextstep.courses.domain;

public class ImageShape {
	private ImageWidth imageWidth;
	private ImageHeight imageHeight;

	public ImageShape(ImageWidth imageWidth, ImageHeight imageHeight) {
		validateShape(imageWidth, imageHeight);
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
	}

	private static void validateShape(final ImageWidth imageWidth, final ImageHeight imageHeight) {
		if (imageWidth.getWidth() * 2 != imageHeight.getHeight() * 3) {
			throw new IllegalArgumentException("가로 세로 비율은 3:2이어야 합니다.");
		}
	}
}
