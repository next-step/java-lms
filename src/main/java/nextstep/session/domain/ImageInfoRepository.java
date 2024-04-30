package nextstep.session.domain;

import java.util.Optional;

public interface ImageInfoRepository {
	int save(ImageInfo imageInfo);
	Optional<ImageInfo> findById(long id);
	int update(ImageInfo imageInfo);
	int deleteById(long id);

}
