package nextstep.courses.cohort.domain;

public class CohortStudentCount {

    private int maxStudentCount;
    private int presentStudentCount;

    public CohortStudentCount(int maxStudentCount, int presentStudentCount) {
        if (maxStudentCount < 1) {
            throw new IllegalArgumentException("수강가능 총인원은 1이상이어야 합니다");
        }

        if (presentStudentCount < 0) {
            throw new IllegalArgumentException("현재 신청인원은 음수일수 없습니다");
        }

        this.maxStudentCount = maxStudentCount;
        this.presentStudentCount = presentStudentCount;
    }

    public boolean isNotOverMax() {
        return this.maxStudentCount > this.presentStudentCount;
    }

    public void plusOneCountAtPresent() {
        if (!isNotOverMax()) {
            return;
        }

        this.presentStudentCount++;
    }
}
