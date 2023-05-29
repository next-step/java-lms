package nextstep.courses.domain;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Type {
    FREE("FREE"), PAID("PAID");

    private final String name;

    private static final Map<String, Type> TYPE_MAP =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(Type::toString, Function.identity())));

    Type(String name) {
        this.name = name;
    }

    public static Type find(String status) {
        if (TYPE_MAP.containsKey(status)) {
            return TYPE_MAP.get(status);
        }
        return null;
    }
}
