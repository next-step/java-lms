package nextstep.users.domain;


import java.util.Objects;

public class NsUserId {
    private Long nsUserId = 0L;


    public NsUserId() {
    }

    public NsUserId(Long nsUserId) {
        this.nsUserId = nsUserId;
    }

    public Long getNsUserId() {
        return nsUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NsUserId other = (NsUserId) o;
        return this.hashCode() == other.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(nsUserId);
    }

    @Override
    public String toString() {
        return "NsUserId{" +
                "nsUserId=" + nsUserId +
                '}';
    }
}
