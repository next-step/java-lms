package nextstep.sessions.infrastructure;

import nextstep.sessions.domain.SessionRegisterDetails;

public interface SessionRegisterDetailsRepository {
    int save(SessionRegisterDetails sessionRegisterDetails);
}
