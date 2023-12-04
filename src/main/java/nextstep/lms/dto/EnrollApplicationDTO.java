package nextstep.lms.dto;

import nextstep.users.domain.NsUser;

public class EnrollApplicationDTO {
    private final NsUser nsUser;
    private final int tuitionFee;

    public EnrollApplicationDTO(NsUser nsUser, int tuitionFee) {
        this.nsUser = nsUser;
        this.tuitionFee = tuitionFee;
    }
    public NsUser getNsUser() {
        return nsUser;
    }

    public int getTuitionFee() {
        return tuitionFee;
    }
}
