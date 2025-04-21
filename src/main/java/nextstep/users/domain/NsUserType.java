package nextstep.users.domain;

import lombok.Getter;

@Getter
public enum NsUserType {
    WOOAH_TECH_COURSE("우아한테크코스"),
    WOOAH_TECH_CAMP_PRO("우아한테크캠프 Pro"),
    INSTRUCTOR("강사"),
    UNSELECTED("비 선발 인원");;

    private final String displayName;

   NsUserType(String displayName) {
        this.displayName = displayName;
    }

    public boolean canApprove(NsUserType nsUserType) {
        return this.equals(INSTRUCTOR) && !(nsUserType.equals(UNSELECTED) || nsUserType.equals(INSTRUCTOR)) ;
    }

    public boolean canCancelApproval(NsUserType nsUserType) {
       return this.equals(INSTRUCTOR) && nsUserType.equals(UNSELECTED);
    }
}
