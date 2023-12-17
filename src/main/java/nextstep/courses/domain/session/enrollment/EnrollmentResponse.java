package nextstep.courses.domain.session.enrollment;

public class EnrollmentResponse {
    private String randomUUID;
    private boolean isMaxStudent;

    public EnrollmentResponse(String randomUUID, boolean isMaxStudent) {
        this.randomUUID = randomUUID;
        this.isMaxStudent = isMaxStudent;
    }

    public String getRandomUUID() {
        return randomUUID;
    }

    public boolean isMaxStudent() {
        return isMaxStudent;
    }
}
