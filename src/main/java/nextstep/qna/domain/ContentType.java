package nextstep.qna.domain;

import java.util.HashMap;
import java.util.Map;

public enum ContentType {
    QUESTION, ANSWER;

    private static final Map<String, ContentType> TYPE_MAP = new HashMap<>() {{
        for(ContentType contentType : ContentType.values()) {
            put(contentType.name(), contentType);
        }
    }};

    public static ContentType of(String contentType) {
        return TYPE_MAP.computeIfAbsent(contentType, (content) -> {
            throw new IllegalArgumentException(content);
        });
    }
}
