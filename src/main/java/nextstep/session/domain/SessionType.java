package nextstep.session.domain;

public enum SessionType {

    PAID, FREE;

    public boolean isPaid() {
        return this.equals(PAID);
    }
}
