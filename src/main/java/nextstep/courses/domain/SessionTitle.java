package nextstep.courses.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SessionTitle {
    private static final Map<String, SessionTitle> sessionTitleCache = new HashMap<>();

    private final String title;

    private SessionTitle(String title) {
        this.title = title;
    }

    public static SessionTitle of(String title) {
        validateTitle(title);
        sessionTitleCache.putIfAbsent(title, new SessionTitle(title));
        return sessionTitleCache.get(title);
    }

    public String title() {
        return title;
    }

    private static void validateTitle(String title) {
        if(title == null || title.isBlank()) {
            throw new IllegalArgumentException("세션 타이틀은 null이나 공백일 수 없습니다: " + title);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SessionTitle)) return false;
        SessionTitle that = (SessionTitle) o;
        return Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
