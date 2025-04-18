package nextstep.courses.infrastructure;

import nextstep.courses.dto.SessionDto;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("sessionRepository")
public interface SessionRepository {
    Optional<SessionDto> findById(Long id);
} 