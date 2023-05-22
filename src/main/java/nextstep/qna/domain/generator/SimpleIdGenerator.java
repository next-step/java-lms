package nextstep.qna.domain.generator;

import java.util.HashMap;
import java.util.Map;

public class SimpleIdGenerator {
    private static final long INITIAL_SEQUENCE = 1;
    private static final long INCREMENTAL_VALUE = 1;
    private static final Map<Class<?>, Long> cache = new HashMap<>();

    public static synchronized long getAndIncrement(Class<?> clazz) {

        long id = cache.getOrDefault(clazz, INITIAL_SEQUENCE);

        if (id == INITIAL_SEQUENCE) {
            return put(clazz, INCREMENTAL_VALUE, false);
        }

        return put(clazz, id, true);

    }

    private static long put(Class<?> clazz, long value, boolean isIncrease) {
        long putVal = getPutVal(value, isIncrease);

        cache.put(clazz, putVal);
        return putVal;
    }

    private static long getPutVal(long value, boolean isIncrease) {

        if (isIncrease) {
            return value + INITIAL_SEQUENCE;
        }

        return value;
    }
}
