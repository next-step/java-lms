package nextstep.courses.domain.session;

public class Session {

    private final SessionCapacity capacity;
    private final SessionCoverImage coverImage;
    private final Long fee;

    public Session(SessionCapacity capacity, Long fee) {
        this(capacity, null, fee);
    }

    public Session(SessionCapacity capacity, SessionCoverImage coverImage, Long fee) {
        this.capacity = capacity;
        this.coverImage = coverImage;
        this.fee = fee;
    }

    public boolean hasCapacity() {
        return capacity.hasCapacity();
    }

    public SessionCapacity getCapacity() {
        return capacity;
    }

    public boolean matchFee(Long amount) {
        return fee.equals(amount);
    }

    public Long getFee() {
        return fee;
    }
}
