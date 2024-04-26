package nextstep.session.domain;

public class ImageInfo {
	private ImageSize imageSize;
	private ImageReSolution imageReSolution;
	private ImageType imageType;

	public ImageInfo(ImageSize imageSize, ImageReSolution imageReSolution, ImageType imageType) {
		this.imageSize = imageSize;
		this.imageReSolution = imageReSolution;
		this.imageType = imageType;
	}

	public ImageInfo(String type) {
		this.imageType = ImageType.getImageType(type);
	}

}
