package nextstep.courses.domain.session;

public enum SessionGatheringStatus {
    GATHERING, NON_GATHERING;

    public boolean isEnrollPossibleStatus() {
        return this == GATHERING;
    }
}
