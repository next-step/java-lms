package nextstep.image.domain;

public class Image {
	private ImageType type;
	private ImageShape shape;
	private Integer size;

	public Image(final ImageType type, final ImageShape shape, final Integer size) {
		this.type = type;
		this.shape = shape;
		this.size = size;
	}
}
