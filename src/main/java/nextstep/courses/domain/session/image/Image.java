package nextstep.courses.domain.session.image;

import nextstep.courses.domain.enums.ImageType;

public class Image {
	private final ImageCapacity imageCapacity;
	private final ImageType type;
	private final ImageSize size;

	public Image(
		double capacity, String type, int width, int height
	) {
		this.imageCapacity = new ImageCapacity(capacity);
		this.type = ImageType.of(type);
		this.size = new ImageSize(width, height);
	}

	public Image(ImageCapacity imageCapacity, ImageType type, ImageSize size) {
		this.imageCapacity = imageCapacity;
		this.type = type;
		this.size = size;
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
}
