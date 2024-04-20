package nextstep.courses.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nextstep.courses.domain.session.image.CoverImage;
import nextstep.courses.infrastructure.session.image.CoverImageEntity;
import nextstep.courses.infrastructure.session.image.CoverImageRepository;

@Service("CoverImageService")
public class CoverImageService {

    private final CoverImageRepository coverImageRepository;

    public CoverImageService(final CoverImageRepository coverImageRepository) {
        this.coverImageRepository = coverImageRepository;
    }

    @Transactional
    public Long save(final CoverImage coverImage, final Long sessionId) {
        final CoverImageEntity coverImageEntity = CoverImageEntity.fromDomain(coverImage, sessionId);

        return coverImageRepository.save(coverImageEntity);
    }

    public CoverImage findById(final Long coverImageId) {
        final CoverImageEntity coverImageEntity = coverImageRepository.findById(coverImageId)
                .orElseThrow(NoSuchElementException::new);

        return coverImageEntity.toDomain();
    }

    public CoverImage findBySessionId(final Long sessionId) {
        final CoverImageEntity coverImageEntity = coverImageRepository.findBySessionId(sessionId)
                .orElseThrow(NoSuchElementException::new);

        return coverImageEntity.toDomain();
    }
}
