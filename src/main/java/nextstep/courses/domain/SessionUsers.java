package nextstep.courses.domain;

import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SessionUsers {
    List<SessionUser> sessionUserMappingList;

    public SessionUsers(List<SessionUser> sessionUserMappingList) {
        this.sessionUserMappingList = sessionUserMappingList;
    }

    public List<Long> getNsUserIds() {
        return sessionUserMappingList.stream()
                .map(sessionUserMapping -> sessionUserMapping.getNsUserId())
                .collect(Collectors.toList());
    }
}
