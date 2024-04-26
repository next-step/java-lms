package nextstep.session.domain;

import java.lang.reflect.Array;
import java.util.Arrays;

public enum ImageType {
	GIF,
	JPG,
	JPEG,
	PNG,
	SVG;

	ImageType() {
	}

	public static ImageType getImageType(String imageType) {
		return Arrays.stream(ImageType.values())
				.filter(type -> type.name().equalsIgnoreCase(imageType))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("이미지 타입은 gif, jpg(jpeg 포함), png, svg만 허용합니다."));
	}

}
