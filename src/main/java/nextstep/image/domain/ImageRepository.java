package nextstep.image.domain;

public interface ImageRepository {
	int save(final Image image);
	Image findById(final Long id);
}
