package nextstep.courses.domain;

import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SessionUserMappings {
    List<SessionUserMapping> sessionUserMappingList;

    public SessionUserMappings(List<SessionUserMapping> sessionUserMappingList) {
        if (CollectionUtils.isEmpty(sessionUserMappingList)) {
            throw new IllegalArgumentException("값이 비워있습니다.");
        }
        this.sessionUserMappingList = sessionUserMappingList;
    }

    public List<Long> getNsUserIds() {
        return sessionUserMappingList.stream()
                .map(sessionUserMapping -> sessionUserMapping.getNsUserId())
                .collect(Collectors.toList());

    }

    public List<SessionUserMapping> getSessionUserMappingList() {
        return sessionUserMappingList;
    }

    public List<Long> getNsUserId() {
        return Optional.ofNullable(sessionUserMappingList)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(SessionUserMapping::getNsUserId)
                .collect(Collectors.toList());
    }
}
