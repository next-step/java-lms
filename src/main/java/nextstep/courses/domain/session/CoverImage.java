package nextstep.courses.domain.session;

import nextstep.courses.domain.session.ImageShape;
import nextstep.courses.domain.session.ImageType;

import java.util.Locale;

public class CoverImage {
	private static final Long MAX_SIZE = 1024L * 1024L;

	private Long id;
	private final long sessionId;
	private final long size;
	private final ImageType imageType;
	private final ImageShape imageShape;

	public CoverImage(long sessionId, long size, String imageType, ImageShape imageShape) {
		this(0L, sessionId, size, imageType, imageShape);
	}

	public CoverImage(long id, long sessionId, long size, String imageType, ImageShape imageShape) {
		validate(size);
		this.id = id;
		this.sessionId = sessionId;
		this.size = size;
		this.imageType = ImageType.of(imageType.toUpperCase(Locale.ROOT));
		this.imageShape = imageShape;
	}

	private void validate(Long size) {
		if (size > MAX_SIZE) {
			throw new IllegalArgumentException("커버 이미지 크기는 1MB 이하여야 합니다.");
		}
	}

	public boolean hasSameSessionId(long sessionId) {
		return this.sessionId == sessionId;
	}

	public long sessionId() {
		return sessionId;
	}

	public long size() {
		return size;
	}

	public ImageType imageType() {
		return imageType;
	}

	public ImageShape imageShape() {
		return imageShape;
	}

	@Override
	public String toString() {
		return "CoverImage{" +
				"id=" + id +
				", sessionId=" + sessionId +
				", size=" + size +
				", imageType=" + imageType +
				", imageShape=" + imageShape +
				'}';
	}
}
