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
}
