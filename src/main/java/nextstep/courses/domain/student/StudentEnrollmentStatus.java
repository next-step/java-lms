package nextstep.courses.domain.student;

public enum StudentEnrollmentStatus {

    PENDING("대기중"),
    APPROVAL("승인"),
    CANCEL("취소")
    ;

    private final String status;

    StudentEnrollmentStatus(String status) {
        this.status = status;
    }



}
