package nextstep.courses.domain;

import java.util.Arrays;

public enum SessionStatus {
    PREPARING("준비중", false),
    OPEN("모집중", true),
    CLOSED("종료", false);
    
    private String statusName;
    private boolean canRegistering;

    SessionStatus(String statusName, boolean canRegistering) {
        this.statusName = statusName;
        this.canRegistering = canRegistering;
    }

    public String getStatusName(){
        return statusName;
    }

    public boolean getCanRegistering(){
        return canRegistering;
    }

    public static SessionStatus getByName(String name){
        return Arrays.stream(SessionStatus.values())
                .filter(status -> status.name().equals(name))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
