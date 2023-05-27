package nextstep.session.domain;

import java.util.Arrays;

public enum SessionStatus {
    READY("ready"),
    RECRUITING("recruiting"),
    END("end");

    private String status;

    SessionStatus(String status) {
        this.status = status;
    }

    public static SessionStatus of(String status) {
        return Arrays.stream(values())
                .filter(value -> value.status.equals(status))
                .findAny()
                .orElse(null);
    }

    public String getStatus() {
        return status;
    }
}
