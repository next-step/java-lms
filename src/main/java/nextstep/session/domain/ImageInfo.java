package nextstep.session.domain;

public class ImageInfo {
	private ImageSize imageSize;
	private ImageReSolution imageReSolution;
	private ImageType imageType;

	public ImageInfo(String type) {
		this(null, null, ImageType.getImageType(type));
	}

	public ImageInfo(ImageSize imageSize, ImageReSolution imageReSolution, ImageType imageType) {
		this.imageSize = imageSize;
		this.imageReSolution = imageReSolution;
		this.imageType = imageType;
	}

}
