package nextstep.courses.domain;

import nextstep.courses.CannotSignUpException;

public class PaidSession extends Session {
    private int maxStudentCount;

    private int studentCount;

    public PaidSession(String title, Course course, int maxStudentCount) {
        super(title, course);
        this.maxStudentCount = maxStudentCount;
        this.studentCount = 0;
    }

    @Override public void signUp() throws CannotSignUpException {
        validateAvailableSignUp();

        studentCount += 1;
    }

    private void validateAvailableSignUp() throws CannotSignUpException {
        if (maxStudentCount == studentCount) {
            throw new CannotSignUpException("최대 수강 인원을 초과했습니다.");
        }
    }
}
