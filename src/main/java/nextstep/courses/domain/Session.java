package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

public class Session {
    private Long id;

    private String title;

    private Period period;

    private String coverImageUrl;

    private Boolean isFree;

    private SessionStatus sessionStatus;

    private Students students;

    private Long capacity;

    public Session(Long id, String title, Period period, String coverImageUrl, Boolean isFree, SessionStatus sessionStatus, Students students, Long capacity) {
        this.id = id;
        this.title = title;
        this.period = period;
        this.coverImageUrl = coverImageUrl;
        this.isFree = isFree;
        this.sessionStatus = sessionStatus;
        this.students = students;
        this.capacity = capacity;
    }

    public void add(NsUser user) {
        if (this.sessionStatus != SessionStatus.OPEN) {
            throw new IllegalStateException("강의 모집 중이 아닙니다.");
        }
        if (this.students.isGreaterEqualThan(this.capacity)) {
            throw new IllegalStateException("강의가 현재 만석입니다.");
        }
        this.students.add(user);
    }
}
