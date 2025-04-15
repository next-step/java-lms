package nextstep.courses.infrastructure;

import nextstep.courses.dto.ImageDto;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository {
    ImageDto findBySessionId(Long sessionId);
}
