package nextstep.lms.dto;

public class EnrollApplicationDTO {
    private final Long userId;
    private final int tuitionFee;

    public EnrollApplicationDTO(Long userId, int tuitionFee) {
        this.userId = userId;
        this.tuitionFee = tuitionFee;
    }

    public Long getUserId() {
        return userId;
    }

    public int getTuitionFee() {
        return tuitionFee;
    }
}
