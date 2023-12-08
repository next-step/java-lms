package nextstep.courses.repository;

import nextstep.courses.domain.SessionImage;
import nextstep.courses.enumeration.ExtensionType;

import java.util.Optional;

public interface SessionImageRepository {

    int save(SessionImage sessionImage);

    int update(String imageUrl, ExtensionType extensionType, Long size, Long id);

    int delete(Long id);

    Optional<SessionImage> findById(Long id);
}
