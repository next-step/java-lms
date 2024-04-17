package nextstep.courses.domain;

public enum SessionProgressStatus {
    PREPARE,
    RUN,
    END;

    public static SessionProgressStatus findByName(String name) {
        return SessionProgressStatus.valueOf(name.toUpperCase());
    }
}
