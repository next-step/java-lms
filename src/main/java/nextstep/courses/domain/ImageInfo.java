package nextstep.courses.domain;

import java.util.Locale;

public class ImageInfo {
	private final static long KB_UNIT = 1024;
	private long imageSize;
	private int width;
	private int height;
	private String type;

	public ImageInfo(long imageSize, String type, int width, int height) {
		validateImageSize(imageSize);
		validateImageType(type);
		validateImageDimensions(width, height);

		this.imageSize = imageSize;
		this.type = type;
		this.width = width;
		this.height = height;
	}

	public ImageInfo(long imageSize) {
		validateImageSize(imageSize);
		this.imageSize = imageSize;
	}

	public ImageInfo(String type) {
		validateImageType(type);
		this.type = type;
	}

	public ImageInfo(int width, int height) {
		validateImageDimensions(width, height);
		this.width = width;
		this.height = height;
	}

	private void validateImageSize(long imageSize) {
		if (imageSize > KB_UNIT) {
			throw new IllegalArgumentException("이미지 크기는 1MB(1024KB)이하여야 합니다.");
		}
	}

	private void validateImageType(String type) {
		if (ImageType.isImageType(type)) {
			throw new IllegalArgumentException("이미지 타입은 gif, jpg(jpeg 포함), png, svg만 허용합니다.");
		}
	}

	private void validateImageDimensions(int width, int height) {
		if (width <= 0 || height <= 0) {
			throw new IllegalArgumentException("가로와 세로 길이는 양의 정수여야 합니다.");
		}

		if (width < 300 || height < 200) {
			throw new IllegalArgumentException("가로는 300 이상, 세로는 200 이상이어야 합니다.");
		}

		if (isValidRatio(width) != height) {
			throw new IllegalArgumentException("가로세로비율이 3:2가 아닙니다.");
		}
	}

	private int isValidRatio(int width) {
		return width / 3 * 2;
	}

}
