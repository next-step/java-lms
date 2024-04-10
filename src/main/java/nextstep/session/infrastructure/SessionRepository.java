package nextstep.session.infrastructure;

import nextstep.session.dto.SessionDto;

public interface SessionRepository {

    long save(SessionDto sessionDto);

    SessionDto findById(long sessionId);
}
