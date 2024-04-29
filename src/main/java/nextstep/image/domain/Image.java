package nextstep.image.domain;

public class Image {
	private Long id;
	private Long sessionId;
	private String url;
	private ImageType type;
	private ImageShape shape;
	private Integer size;

	public Image(final long id, final long sessionId, final String url, final ImageType type, final ImageShape shape, final Integer size) {
		this.id = id;
		this.sessionId = sessionId;
		this.url = url;
		this.type = type;
		this.shape = shape;
		this.size = size;
	}
}
