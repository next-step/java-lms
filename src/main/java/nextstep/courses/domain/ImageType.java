package nextstep.courses.domain;

import java.util.Locale;

public enum ImageType {
	GIF,
	JPG,
	JPEG,
	PNG,
	SVG;

	public static ImageType of(String type) {
		try {
			return valueOf(type.toUpperCase(Locale.ROOT));
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("이미지 타입은 gif, jpg, jpeg, png, svg 만 가능합니다.");
		}
	}
}
