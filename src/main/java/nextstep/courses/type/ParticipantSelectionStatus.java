package nextstep.courses.type;

public enum ParticipantSelectionStatus {
    WAITING, ACCEPT, REJECT;

    public boolean isAccept() {
        return this.equals(ACCEPT);
    }

    public boolean isReject() {
        return this.equals(REJECT);
    }
}
