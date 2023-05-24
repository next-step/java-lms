package nextstep.qna.domain.generator;

import java.util.HashMap;
import java.util.Map;

public class SimpleIdGenerator {
    private static final long INITIAL_SEQUENCE = 0;
    private static final long INCREMENTAL_VALUE = 1;
    private static final Map<Class<?>, Long> cache = new HashMap<>();

    public static synchronized long getAndIncrement(Class<?> clazz) {

        long id = cache.getOrDefault(clazz, INITIAL_SEQUENCE);

        return put(clazz, id);
    }

    private static long put(Class<?> clazz, long value) {
        long putVal = increase(value);

        cache.put(clazz, putVal);
        return putVal;
    }

    private static long increase(long value) {
        return value + INCREMENTAL_VALUE;
    }
}
