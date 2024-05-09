package nextstep.file;

import java.util.Arrays;

public enum ImageFileType {
	GIF,
	JPG,
	JPEG,
	PNG,
	SVG;

	public static ImageFileType from(String type) {
		return Arrays.stream(ImageFileType.values())
				.filter(imageType -> imageType.name().equalsIgnoreCase(type))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("이미지 타입은 gif, jpg(jpeg 포함), png, svg만 허용됩니다."));
	}
}
