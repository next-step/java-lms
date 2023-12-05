package nextstep.sessions.domain;

public class SessionCharge {

    // 가격
    private long price;

    // 인원 수
    private int studentsLimitCount;
    private int studentsCount;

    public SessionCharge(boolean charge, long price, int limitStudents) {
        if (!charge) {
            this.price = 0;
            this.studentsLimitCount = 0;
            this.studentsCount = 0;
            return;
        }
        if (price <= 0) {
            throw new IllegalArgumentException("유료 강의는 수강료가 0원 이하일 수 없습니다.");
        }
        if (limitStudents <= 0) {
            throw new IllegalArgumentException("유료 강의는 수강 인원에 제한 있습니다.");
        }
        this.price = price;
        this.studentsLimitCount = limitStudents;
        this.studentsCount = 0;
    }

    public double getPrice() {
        return price;
    }

    public void addStudent() {
        if (price == 0) {
            return;
        }
        if (studentsCount == studentsLimitCount) {
            throw new IllegalStateException("모집 인원이 마감되었습니다.");
        }
        studentsCount++;
    }
}
