package nextstep.file;

public class ImageFile {
	private Long id;

	private int size;

	private int width;

	private int height;

	private ImageFileType type;

	public ImageFile(int size, int width, int height, String type) {
		this.size = size;
		this.width = width;
		this.height = height;
		this.type = ImageFileType.from(type);
	}
}
