package nextstep.courses.domain;

import java.util.Arrays;

public enum ImageType {
	GIF,
	JPG,
	JPEG,
	PNG,
	SVG;

	ImageType() {
	}

	public static boolean isImageType(String imageType) {
		return Arrays.stream(ImageType.values()).noneMatch(type -> type.name().equalsIgnoreCase(imageType));
	}

}
