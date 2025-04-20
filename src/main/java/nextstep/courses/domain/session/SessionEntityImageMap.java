package nextstep.courses.domain.session;

import nextstep.courses.entity.SessionEntity;
import nextstep.courses.entity.SessionImageEntity;

import java.util.*;

public class SessionEntityImageMap {
    private final Map<SessionEntity, List<SessionImageEntity>> value;

    public SessionEntityImageMap() {
        this(new HashMap<>());
    }

    public SessionEntityImageMap(Map<SessionEntity, List<SessionImageEntity>> value) {
        this.value = value;
    }

    public Set<Map.Entry<SessionEntity, List<SessionImageEntity>>> entrySet() {
        return Collections.unmodifiableSet(value.entrySet());
    }

    public void add(SessionEntity sessionEntity, List<SessionImageEntity> sessionImageEntities) {
        value.put(sessionEntity, sessionImageEntities);
    }
}


