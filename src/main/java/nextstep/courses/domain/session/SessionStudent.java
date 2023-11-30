package nextstep.courses.domain.session;

public class SessionStudent {
    private int maxStudentLimit;
    private int currentStudentCount;

    public SessionStudent(final int maxStudentLimit, final int currentStudentCount) {
        this.maxStudentLimit = maxStudentLimit;
        this.currentStudentCount = currentStudentCount;
    }

    public int getCurrentStudentCount() {
        return this.currentStudentCount;
    }

    public boolean isReachedMaxStudentLimit() {
        return this.currentStudentCount >= this.maxStudentLimit;
    }

    public void increaseStudentCount() {
        this.currentStudentCount++;
    }
}
