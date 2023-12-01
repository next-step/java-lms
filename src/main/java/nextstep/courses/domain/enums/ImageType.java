package nextstep.courses.domain.enums;

import java.util.Arrays;

public enum ImageType {
	GIF,
	JPG,
	JPEG,
	PNG,
	SVG
	;

	ImageType() {}

	public static ImageType of(String imageType) {
		return Arrays.stream(values())
			.filter(e -> e.name().equals(imageType))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("지원하지 않는 이미지 형식입니다."));
	}
}
