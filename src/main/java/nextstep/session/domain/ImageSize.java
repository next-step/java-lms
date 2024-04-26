package nextstep.session.domain;

public class ImageSize {
	private final static long KB_UNIT = 1024;

	private int imageSize;

	public ImageSize(int imageSize) {
		validateImageSize(imageSize);
		this.imageSize = imageSize;
	}

	private void validateImageSize(long imageSize) {
		if (imageSize > KB_UNIT) {
			throw new IllegalArgumentException("이미지 크기는 1MB(1024KB)이하여야 합니다.");
		}
	}

}
