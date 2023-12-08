package nextstep.courses.domain.session.image;

import java.util.List;

public interface ImageRepository {
	int save(Image image);
	List<Image> findAllBySessionId(Long id);
}
