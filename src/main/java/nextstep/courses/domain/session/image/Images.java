package nextstep.courses.domain.session.image;

import java.util.Collections;
import java.util.List;

public class Images {
	private final List<Image> images;

	public Images(List<Image> images) {
		this.images = images;
	}

	public List<Image> getImages() {
		return Collections.unmodifiableList(images);
	}
}
