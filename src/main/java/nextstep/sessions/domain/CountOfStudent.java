package nextstep.sessions.domain;

public class CountOfStudent {

    private int currentCountOfStudents;

    private final int maxOfStudents;

    private final SessionType sessionType;

    //todo List<Student> students 추가하고 그에 따른 등록 메서드 추가 && 하나하나씩 점진적으로 리팩터링하기

    public CountOfStudent(int currentCountOfStudents, int maxOfStudents, SessionType sessionType) {
        if (sessionType.isCapacityExceeded(currentCountOfStudents, maxOfStudents)) {
            throw new IllegalArgumentException(String.format("이 강의의 현재 수강 신청 인원: (%s)명, 최대 수강 인원: (%s)명이므로 현재 마감이 된 상태입니다.", currentCountOfStudents, maxOfStudents));
        }
        this.currentCountOfStudents = currentCountOfStudents;
        this.maxOfStudents = maxOfStudents;
        this.sessionType = sessionType;
    }

    public void increaseCountOfStudents() {
        if (sessionType.isCapacityExceeded(currentCountOfStudents + 1, maxOfStudents)) {
            throw new IllegalArgumentException(String.format("이 강의의 현재 수강 신청 인원: (%s)명, 최대 수강 인원: (%s)명이므로 현재 마감이 된 상태입니다.", currentCountOfStudents + 1, maxOfStudents));
        }
        this.currentCountOfStudents++;
    }

    public int getMaxOfStudents() {
        return maxOfStudents;
    }

    public String getSessionType() {
        return sessionType.name();
    }

    public int getCurrentCountOfStudents() {
        return currentCountOfStudents;
    }
}
