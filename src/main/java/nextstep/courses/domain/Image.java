package nextstep.courses.domain;

import nextstep.courses.domain.enums.ImageType;

public class Image {
	private ImageCapacity imageCapacity;
	private ImageType type;
	private ImageSize size;

	public Image(
		double capacity, String type, int width, int height
	) {
		this.imageCapacity = new ImageCapacity(capacity);
		this.type = ImageType.of(type);
		this.size = new ImageSize(width, height);
	}
}
