package nextstep.courses.domain;

public class CapacityFactory {
    public static Capacity forFree() {
        return new UnlimitedCapacity();
    }

    public static Capacity forPaid(int maxParticipants) {
        return new LimitedCapacity(maxParticipants);
    }
}
