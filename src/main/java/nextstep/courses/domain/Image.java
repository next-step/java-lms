package nextstep.courses.domain;

public class Image {
	private Capacity capacity;
	private ImageType type;
	private ImageSize size;

	public Image(
		double capacity, String type, int width, int height
	) {
		this.capacity = new Capacity(capacity);
		this.type = ImageType.of(type);
		this.size = new ImageSize(width, height);
	}
}
