package nextstep.courses.domain;

import java.util.Locale;

public class CoverImage {
	private static final Long MAX_SIZE = 1024L * 1024L;

	private Long sessionId;
	private Long size;
	private ImageType imageType;
	private ImageShape imageShape;

	public CoverImage(Long sessionId, Long size, String type, ImageShape imageShape) {
		validate(size);
		this.sessionId = sessionId;
		this.size = size;
		this.imageType = ImageType.of(type.toUpperCase(Locale.ROOT));
		this.imageShape = imageShape;
	}

	private void validate(Long size) {
		if (size > MAX_SIZE) {
			throw new IllegalArgumentException("커버 이미지 크기는 1MB 이하여야 합니다.");
		}
	}
}
