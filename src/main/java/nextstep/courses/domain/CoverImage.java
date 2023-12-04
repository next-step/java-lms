package nextstep.courses.domain;

import java.util.List;

public class CoverImage {
	private static final Long MAX_SIZE = 1024L * 1024L;
	private static final List<String> ALLOW_TYPES = List.of("gif", "jpg", "jpeg", "png", "svg");

	private Long sessionId;
	private Long size;
	private String type;
	private ImageShape imageShape;

	public CoverImage() {
	}

	public CoverImage(Long sessionId, Long size, String type, ImageShape imageShape) {
		validate(size, type);
		this.sessionId = sessionId;
		this.size = size;
		this.type = type;
		this.imageShape = imageShape;
	}

	private void validate(Long size, String type) {
		if (size > MAX_SIZE) {
			throw new IllegalArgumentException("커버 이미지 크기는 1MB 이하여야 합니다.");
		}
		if (!isAllowedType(type)) {
			throw new IllegalArgumentException("이미지 타입은 gif, jpg, jpeg, png, svg 만 가능합니다.");
		}
	}

	private boolean isAllowedType(String type) {
		return ALLOW_TYPES.contains(type);
	}
}
