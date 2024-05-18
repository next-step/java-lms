package nextstep.file.domain;

public class ImageFile {
	private Long id;

	private int size;

	private int width;

	private int height;

	private ImageFileType type;

	public ImageFile(Long id, int size, int width, int height, String type) {
		this.id = id;
		this.size = size;
		this.width = width;
		this.height = height;
		this.type = ImageFileType.from(type);
	}

	public Long getId() {
		return id;
	}

	public int getSize() {
		return size;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String getTypeString() {
		return type.name();
	}
}
