package nextstep.qna.consumer;

import java.util.function.Consumer;

@FunctionalInterface
public interface ThrowingConsumer<T, E extends Exception> {
    void accept(T t) throws E;

    static <T, E extends Exception> Consumer<T> handlingConsumerWrapper(ThrowingConsumer<T, E> throwingConsumer, Class<E> exceptionClass) {
        return t -> handleConsumer(throwingConsumer, exceptionClass, t);
    }

    private static <T, E extends Exception> void handleConsumer(ThrowingConsumer<T, E> throwingConsumer, Class<E> exceptionClass, T t) {
        try {
            throwingConsumer.accept(t);
        } catch (Exception ex) {
            handleException(ex, exceptionClass);
        }
    }

    private static <E extends Exception> void handleException(Exception ex, Class<E> exceptionClass) {
        try {
            E exCast = exceptionClass.cast(ex);
            System.out.println(exCast.getMessage());
        } catch (ClassCastException ccEx) {
            throw new RuntimeException(ex);
        }
    }

}
