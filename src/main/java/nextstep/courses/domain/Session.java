package nextstep.courses.domain;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Session {
    private Long id;
    private String image;
    private LectureType lectureType;
    private LectureStatus lectureStatus;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Long> userIds = new ArrayList<>();
    private int maxUser;

    public Session(long id) {
        this.id = id;
    }

    public Session(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Session(String image) {
        this.image = image;
    }

    public Session(LectureType lectureType) {
        this.lectureType = lectureType;
    }

    public Session(LectureStatus lectureStatus) {
        this.lectureStatus = lectureStatus;
        this.maxUser = 2;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Long getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public LectureType getLectureType() {
        return lectureType;
    }

    public LectureStatus getLectureStatus() {
        return lectureStatus;
    }

    public void register(long userId) {
        if (!this.lectureStatus.equals(LectureStatus.RECRUITING)) {
            throw new RuntimeException("수강신청은 모집중일때 가능합니다.");
        }

        if(this.userIds.size() >= maxUser) {
            throw new RuntimeException("강의는 강의 최대 수강 인원을 초과할 수 없습니다.");
        }

        userIds.add(userId);
    }
}