package nextstep.courses.domain;

import java.time.LocalDate;

public class Session {
    private Course course;
    private LocalDate startDate;
    private LocalDate endDate;
    private Image coverImage;
    private SessionPayType sessionPayType;
    private Integer maxStudent; // TODO : free : 제한 없음, paid : 제한 있음
}
