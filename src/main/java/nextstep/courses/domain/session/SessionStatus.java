package nextstep.courses.domain.session;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum SessionStatus {

    PREPARING("준비중"),
    OPEN("모집중"),
    CLOSED("종료");

    private static final Map<String, SessionStatus> sessionStatuses = Stream.of(values())
            .collect(Collectors.toUnmodifiableMap(sessionStatus -> sessionStatus.statusName, Function.identity()));

    private final String statusName;

    SessionStatus(final String statusName) {
        this.statusName = statusName;
    }

    public static SessionStatus from(final String statusName) {
        return Optional.ofNullable(sessionStatuses.get(statusName))
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강의 상태입니다. 상태: " + statusName));
    }

    public String statusName() {
        return this.statusName;
    }

    @Override
    public String toString() {
        return this.statusName;
    }
}
