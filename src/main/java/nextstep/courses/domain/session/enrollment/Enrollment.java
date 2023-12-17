package nextstep.courses.domain.session.enrollment;

import nextstep.courses.domain.session.StudentManager;

import java.util.UUID;

public class Enrollment {
    public StudentManager studentManager;
    private UUID uuid = UUID.randomUUID();

    public Enrollment(int maxEnrollmentCount) {
        studentManager = new StudentManager(maxEnrollmentCount);
    }

    public EnrollmentResponse enrollFreeSession(EnrollmentRequest request) {
        studentManager.add(request.getUser());
        return new EnrollmentResponse(uuid.toString(), false);
    }

    public EnrollmentResponse enrollPaidSession(EnrollmentRequest request) {
        checkPaidSessionAvailability(request.getFee(), request.getAmount());
        studentManager.add(request.getUser());
        return new EnrollmentResponse(uuid.toString(), studentManager.isMaxStudent());
    }

    private void checkPaidSessionAvailability(long fee, long amount) {
        if (amount < fee) {
            throw new IllegalArgumentException("결제한 금액이 수강료보다 작습니다.");
        }
    }

    public int currentStudentCount() {
        return studentManager.getStudentCount();
    }
}
