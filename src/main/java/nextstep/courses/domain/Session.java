package nextstep.courses.domain;

import java.time.LocalDate;

public class Session {
    private Course course;
    private LocalDate startDate;
    private LocalDate endDate;
    private Image coverImage;
    private SessionPayType sessionPayType;
    private Integer maxStudent; // TODO : free : 제한 없음, paid : 제한 있음

    public Session(Course course, LocalDate startDate, LocalDate endDate, Image coverImage, SessionPayType sessionPayType, Integer maxStudent) {
        validateSessionDate(startDate, endDate);
        this.course = course;
        this.startDate = startDate;
        this.endDate = endDate;
        this.coverImage = coverImage;
        this.sessionPayType = sessionPayType;
        this.maxStudent = maxStudent;
    }

    private void validateSessionDate(LocalDate startDate, LocalDate endDate){
        throw new IllegalArgumentException("시작일보다 종료일이 먼저올 수 없습니다.");
    }
}
