package nextstep.courses.domain;

import java.util.Optional;

import nextstep.courses.domain.enums.ImageType;

public class CoverImageInfo {
	private static final String IMAGE_SIZE_OVER_MESSAGE = "이미지 크기는 1MB 이하여야 합니다.";
	private static final String IMAGE_TYPE_INVALID_MESSAGE = "이미지 타입은 gif, jpg(jpeg 포함), png, svg만 가능합니다.";
	private static final String IMAGE_WIDTH_UNDER_MESSAGE = "이미지의 너비는 300 픽셀 이상이어야 합니다.";
	private static final String IMAGE_HEIGHT_UNDER_MESSAGE = "이미지의 높이는 200 픽셀 이상이어야 합니다.";
	private static final String IMAGE_WRONG_RATE_MESSAGE = "이미지의 너비와 높이는 3:2 비율이어야 합니다.";

	private static final Long MAX_IMAGE_SIZE = 1024L;         // KB
	private static final Long MIN_IMAGE_WIDTH = 300L;         // 픽셀
	private static final Long MIN_IMAGE_HEIGHT = 200L;        // 픽셀
	private static final Long WIDTH_RATE = 3L;
	private static final Long HEIGHT_RATE = 2L;

	private Long id;
	private final Long size;
	private final ImageType imageType;
	private final Long width;
	private final Long height;

	public CoverImageInfo(Long size, String imageType, Long width, Long height) {
		validationImageSize(size);
		validationImageRate(width, height);
		this.imageType = validationImageType(imageType);
		this.size = size;
		this.width = width;
		this.height = height;
	}

	public CoverImageInfo(Long id, Long size, String imageTypeStr, Long width, Long height) {
		this(size, imageTypeStr, width, height);
		this.id = id;
	}

	private ImageType validationImageType(String imageTypeStr) {
		Optional<ImageType> imageType = ImageType.findByType(imageTypeStr);
		return imageType.orElseThrow(() -> new IllegalArgumentException(IMAGE_TYPE_INVALID_MESSAGE));
	}

	private void validationImageRate(Long width, Long height) {
		if (width < MIN_IMAGE_WIDTH) {
			throw new IllegalArgumentException(IMAGE_WIDTH_UNDER_MESSAGE);
		}
		if (height < MIN_IMAGE_HEIGHT) {
			throw new IllegalArgumentException(IMAGE_HEIGHT_UNDER_MESSAGE);
		}
		if (isCorrectImageRate(width, height)) {
			throw new IllegalArgumentException(IMAGE_WRONG_RATE_MESSAGE);
		}
	}

	private static boolean isCorrectImageRate(Long width, Long height) {
		return WIDTH_RATE * height != HEIGHT_RATE * width;
	}

	private void validationImageSize(Long size) {
		if (size > MAX_IMAGE_SIZE) {
			throw new IllegalArgumentException(IMAGE_SIZE_OVER_MESSAGE);
		}
	}

	public void setId() {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public Long getSize() {
		return size;
	}

	public ImageType getImageType() {
		return imageType;
	}

	public Long getWidth() {
		return width;
	}

	public Long getHeight() {
		return height;
	}

	public static CoverImageInfoBuilder builder() {
		return new CoverImageInfoBuilder();
	}

	public static class CoverImageInfoBuilder {
		private Long id;
		private Long size;
		private String imageType;
		private Long width;
		private Long height;

		public CoverImageInfoBuilder id(Long id) {
			this.id = id;
			return this;
		}

		public CoverImageInfoBuilder size(Long size) {
			this.size = size;
			return this;
		}

		public CoverImageInfoBuilder imageType(String imageTypeStr) {
			this.imageType = imageTypeStr;
			return this;
		}

		public CoverImageInfoBuilder width(Long width) {
			this.width = width;
			return this;
		}

		public CoverImageInfoBuilder height(Long height) {
			this.height = height;
			return this;
		}

		public CoverImageInfo build() {
			return new CoverImageInfo(size, imageType, width, height);
		}

	}
}
