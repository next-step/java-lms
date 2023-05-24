package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Session {

    private int id;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String chargeYN;
    private String coverImageUrl;
    private SessionStatusType statusType;
    private int numberOfStudents;
    private int maximumNumberOfStudents;
    private Long creatorId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    Session() {
    }

    Session(int id, String title, SessionStatusType statusType, int maximumNumberOfStudents) {
        this.id = id;
        this.title = title;
        this.statusType = statusType;
        this.maximumNumberOfStudents = maximumNumberOfStudents;
    }

    public int register() {
        checkRecruiting();
        checkExceedMaximumNumberOfStudents();
        return ++numberOfStudents;
    }

    private void checkExceedMaximumNumberOfStudents() {
        if (numberOfStudents >= maximumNumberOfStudents) {
            throw new RuntimeException("정원이 초과되어 신청하실 수 없습니다.");
        }
    }

    private void checkRecruiting() {
        if (statusType != SessionStatusType.RECRUITING) {
            throw new RuntimeException("모집중인 강의가 아닙니다.");
        }
    }
}
