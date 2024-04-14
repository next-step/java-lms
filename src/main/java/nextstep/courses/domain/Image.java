package nextstep.courses.domain;

public class Image {
	private Long id;
	private ImageType type;
	private ImageShape shape;
	private Integer size;

	public Image(final Long id, final ImageType type, final ImageShape shape, final Integer size) {
		this.id = id;
		this.type = type;
		this.shape = shape;
		this.size = size;
	}
}
