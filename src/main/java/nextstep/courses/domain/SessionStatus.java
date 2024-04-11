package nextstep.courses.domain;

public enum SessionStatus {
    PREPARE,RECRUIT,END;


    public boolean isRecruit() {
        if (this.equals(RECRUIT)) {
            return true;
        }

        return false;
    }
}
