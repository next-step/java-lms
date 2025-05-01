package nextstep.users.domain;

import lombok.Getter;

@Getter
public enum NsUserType {
    WOOAH_TECH_COURSE("우아한테크코스"),
    WOOAH_TECH_CAMP_PRO("우아한테크캠프 Pro"),
    INSTRUCTOR("강사"),
    UNSELECTED("비 선발 인원"),
    UNKNOWN("모름");
    /**/
    private final String type;

    NsUserType(String type) {
        this.type = type;
    }

    public static NsUserType fromString(String type) {
        for (NsUserType s : values()) {
            if (s.type.equalsIgnoreCase(type)) {
                return s;
            }
        }
        return UNKNOWN;
    }

    public boolean canApprove(NsUserType nsUserType) {
        return this.equals(INSTRUCTOR) && (nsUserType.equals(WOOAH_TECH_COURSE) || nsUserType.equals(WOOAH_TECH_CAMP_PRO));
    }

    public boolean canCancel(NsUserType nsUserType) {
        return this.equals(INSTRUCTOR) && nsUserType.equals(UNSELECTED);
    }
}
