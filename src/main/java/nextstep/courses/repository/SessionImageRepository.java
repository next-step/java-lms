package nextstep.courses.repository;

import nextstep.courses.domain.SessionImage;

import java.util.Optional;

public interface SessionImageRepository {

    int save(SessionImage sessionImage);

    int updateImageUrl(String title, Long id);

    int delete(Long id);

    Optional<SessionImage> findById(Long id);
}
