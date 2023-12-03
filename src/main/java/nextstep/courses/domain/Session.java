package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Session {

    private Long id;
    private Long courseId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Session(Long id, Long courseId, LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("강의 시작일은 종료일 이후일 수 없습니다.");
        }
        this.id = id;
        this.courseId = courseId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String period() {
        String startDate = String.valueOf(this.startDate);
        String endDate = String.valueOf(this.endDate);
        return startDate + "~" + endDate;
    }
}
