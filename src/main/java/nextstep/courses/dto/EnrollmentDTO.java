package nextstep.courses.dto;

public class EnrollmentDTO {
    private final NsUsersDTO users;
    private final NsUserLimitDTO limits;

    public EnrollmentDTO(NsUsersDTO users, NsUserLimitDTO limits) {
        this.users = users;
        this.limits = limits;
    }

    public NsUserLimitDTO getLimits() {
        return limits;
    }
}
