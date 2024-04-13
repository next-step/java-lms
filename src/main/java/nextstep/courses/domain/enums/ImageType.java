package nextstep.courses.domain.enums;

import java.awt.*;
import java.util.Arrays;
import java.util.Optional;

public enum ImageType {
	GIF, JPG, JPEG, PNG, SVG;

	public static Optional<ImageType> findByType(String imageTypStr){
		String upperImageTypeStr = imageTypStr.toUpperCase();
		return Arrays.stream(ImageType.values()).filter(imageType -> upperImageTypeStr.equals(imageType.name()))
			.findFirst();
	}
}
