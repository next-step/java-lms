package nextstep.image.domain;

public interface ImageRepository {
	int save(final Image image);
	Image findById(Long id);
}
