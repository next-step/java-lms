package nextstep.sessions.domain;

public class SessionStudent {

    private int limitCount;
    private int count;

    public SessionStudent(long price, int limitCount) {
        if (price <= 0) {
            this.limitCount = 0;
            this.count = 0;
            return;
        }
        if (limitCount <= 0) {
            throw new IllegalArgumentException("유료 강의는 수강 인원에 제한 있습니다.");
        }
        this.limitCount = limitCount;
        this.count = 0;
    }

    public int getLimitCount() {
        return limitCount;
    }

    public int getCount() {
        return count;
    }

    public void addStudent() {
        if (limitCount > 0 && limitCount == count) {
            throw new IllegalStateException("모집 인원이 마감되었습니다.");
        }
        count++;
    }
}
