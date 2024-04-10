package nextstep.session.infrastructure;

import nextstep.session.domain.Cover;
import nextstep.session.dto.SessionDto;
import nextstep.session.dto.SessionUpdateBasicPropertiesDto;

public interface SessionRepository {

    long save(SessionDto sessionDto);

    SessionDto findById(long sessionId);

    int updateSessionBasicProperties(SessionDto sessionDto, SessionUpdateBasicPropertiesDto sessionUpdateBasicPropertiesDto);

    int updateCover(SessionDto sessionDto, Cover newCover);
}
