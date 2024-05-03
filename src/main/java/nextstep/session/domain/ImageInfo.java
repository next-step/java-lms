package nextstep.session.domain;

public class ImageInfo {
	private long id;
	private ImageSize imageSize;
	private ImageReSolution imageReSolution;
	private ImageType imageType;
	private long sessionId;

	public ImageInfo(String type) {
		this(0L, null, null, ImageType.getImageType(type));
	}

	public ImageInfo(long id, ImageSize imageSize, ImageReSolution imageReSolution, ImageType imageType) {
		this.id = id;
		this.imageSize = imageSize;
		this.imageReSolution = imageReSolution;
		this.imageType = imageType;
	}

	public ImageInfo(long id, ImageSize imageSize, ImageReSolution imageReSolution, ImageType imageType, long sessionId) {
		this.id = id;
		this.imageSize = imageSize;
		this.imageReSolution = imageReSolution;
		this.imageType = imageType;
		this.sessionId = sessionId;
	}

	public long getId() {
		return id;
	}

	public int getImageSize() {
		return imageSize.getImageSize();
	}

	public int getImageHeight() {
		return imageReSolution.getHeight();
	}

	public int getImageWidth() {
		return imageReSolution.getWidth();
	}

	public String getImageType() {
		return imageType.name();
	}

	public long getSessionId() {
		return sessionId;
	}

	@Override
	public String toString() {
		return "ImageInfo{" +
				"id=" + id +
				", imageSize=" + imageSize +
				", imageReSolution=" + imageReSolution +
				", imageType=" + imageType +
				'}';
	}

}
