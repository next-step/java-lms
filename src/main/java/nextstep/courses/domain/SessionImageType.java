package nextstep.courses.domain;

import java.util.Arrays;

public enum SessionImageType {
    
    gif("gif"), 
    jpg("jpg"), 
    jpeg("jpeg"), 
    png("png"), 
    svg("svg");

    private final String name;

    SessionImageType(String name) {
        this.name = name;
    }

    /**
     * 문자열에서 해당 타입 enum으로 변환 (없으면 예외)
    */
    public static SessionImageType of(String name) {
        return Arrays.stream(values())
                .filter(type -> type.name.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
