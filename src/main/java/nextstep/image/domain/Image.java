package nextstep.image.domain;

public class Image {
	private Long id;
	private ImageType type;
	private ImageShape shape;
	private Integer size;

	public Image(final Long id, final ImageType type, final ImageShape shape, final Integer size) {
		imageSizeValidate(size);
		this.id = id;
		this.type = type;
		this.shape = shape;
		this.size = size;
	}

	private static void imageSizeValidate(final Integer size) {
		if (size >= 1000000) {
			throw new IllegalArgumentException("이미지 크기는 1메가 바이트를 넘을 수 없습니다.");
		}
	}
}
