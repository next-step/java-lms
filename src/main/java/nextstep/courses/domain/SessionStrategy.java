package nextstep.courses.domain;

@FunctionalInterface
public interface SessionStrategy {
    boolean canEnroll();
}
