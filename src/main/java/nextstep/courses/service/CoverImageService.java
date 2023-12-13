package nextstep.courses.service;

import nextstep.courses.domain.CoverImage;
import nextstep.courses.repository.CoverImageRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

public class CoverImageService {

    @Resource(name = "coverImageRepository")
    private CoverImageRepository coverImageRepository;

    @Transactional
    public void createCoverImage(CoverImage image, Long sessionId) {
        coverImageRepository.save(image, sessionId);
    }
}
