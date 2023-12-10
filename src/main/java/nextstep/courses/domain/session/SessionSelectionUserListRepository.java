package nextstep.courses.domain.session;

import java.util.List;

public interface SessionSelectionUserListRepository {
    int save(SelectionUser user, Long sessionId);

    List<SelectionUser> findAllBySessionId(Long sessionId);
}
