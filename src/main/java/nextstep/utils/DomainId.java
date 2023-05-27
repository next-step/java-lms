package nextstep.utils;

public interface DomainId {
    Long value();

    boolean equals(Object o);

    int hashCode();

    String toString();
}
