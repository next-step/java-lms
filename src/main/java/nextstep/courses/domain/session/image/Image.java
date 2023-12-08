package nextstep.courses.domain.session.image;

import nextstep.courses.domain.enums.ImageType;
import nextstep.courses.domain.session.Session;

public class Image {
	private final Long id;
	private final ImageCapacity imageCapacity;
	private final ImageType type;
	private final ImageSize size;
	private final Session session;

	public Image(
		Long id, double capacity,
		String type, int width, int height,
		Session session
	) {
		this.id = id;
		this.imageCapacity = new ImageCapacity(capacity);
		this.type = ImageType.of(type);
		this.size = new ImageSize(width, height);
		this.session = session;
	}

	public Image(
		Long id, ImageCapacity imageCapacity,
		ImageType type, ImageSize size, Session session
	) {
		this.id = id;
		this.imageCapacity = imageCapacity;
		this.type = type;
		this.size = size;
		this.session = session;
	}

	public Long getId() {
		return id;
	}

	public ImageCapacity getImageCapacity() {
		return imageCapacity;
	}

	public ImageType getType() {
		return type;
	}

	public ImageSize getSize() {
		return size;
	}

	public Session getSession() {
		return session;
	}
}
