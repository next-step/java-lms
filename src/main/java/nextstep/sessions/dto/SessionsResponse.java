package nextstep.sessions.dto;

import java.util.List;

public class SessionsResponse {

    private List<SessionResponse> sessionResponses;

    public SessionsResponse(List<SessionResponse> sessionResponses) {
        this.sessionResponses = sessionResponses;
    }
}
