package nextstep.courses.domain;

import java.util.Locale;

public class ImageInfo {
	private final static long KB_UNIT = 1024;
	private long imageSize;
	private int width;
	private int height;
	private String type;

	public ImageInfo(long imageSize) {
		if (imageSize > KB_UNIT)
			throw new IllegalArgumentException("이미지 크기는 1MB(1024KB)이하여야 한다.");
		this.imageSize = imageSize;
	}

	public ImageInfo(String type) {
		if (ImageType.isImageType(type))
			throw new IllegalArgumentException("이미지 타입은 gif, jpg(jpeg 포함), png, svg만 허용한다.");
		this.type = type;
	}

	public ImageInfo(int width, int height) {
		if (width <= 0 || height <= 0) {
			throw new IllegalArgumentException("가로와 세로 길이는 양의 정수여야 합니다.");
		}

		if (width < 300 || height < 200) {
			throw new IllegalArgumentException("가로는 300이상 세로는 200이상이여야 합니다.");
		}

		if (width / 3 * 2 != height) {
			throw new IllegalArgumentException("가로세로비율이 3:2가 아닙니다.");
		}

		this.width = width;
		this.height = height;
	}

}
