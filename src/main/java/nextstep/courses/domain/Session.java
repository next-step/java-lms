package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Session {
    private int id;

    private int courseId;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    private String coverImageUrl;

    private boolean isFree;

    private Status status;

    private int currentStudents;

    private int maxStudents;

    public Session(LocalDateTime startedAt, LocalDateTime endedAt, boolean isFree, Status status, int currentStudents, int maxStudents) {
        if (startedAt.isAfter(endedAt)) {
            throw new IllegalArgumentException("시작 날짜가 종료 날짜보다 늦을 수 없습니다.");
        }

        if ((isFullStudents(currentStudents, maxStudents))) {
            throw new IllegalArgumentException("현재 수강 인원은 최대 수강 인원을 초과할 수 없습니다.");
        }

        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.isFree = isFree;
        this.status = status;
        this.currentStudents = currentStudents;
        this.maxStudents = maxStudents;
    }

    public Session(int id, int courseId, String coverImageUrl, boolean isFree, Status status, int currentStudents, int maxStudents, LocalDateTime startedAt, LocalDateTime endedAt) {
        this.id = id;
        this.courseId = courseId;
        this.coverImageUrl = coverImageUrl;
        this.isFree = isFree;
        this.status = status;
        this.currentStudents = currentStudents;
        this.maxStudents = maxStudents;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }

    public void patchTerms(LocalDateTime startedAt, LocalDateTime endedAt) {
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }

    public List<LocalDateTime> terms() {
        return List.of(startedAt, endedAt);
    }

    public void patchIsFree(boolean input) {
        this.isFree = input;
    }

    public boolean isFree() {
        return isFree;
    }

    public void registerCoverImage(String url) {
        this.coverImageUrl = url;
    }

    public String coverImageUrl() {
        return this.coverImageUrl;
    }

    public void patchStatus(Status status) {
        this.status = status;
    }

    public boolean isOpening() {
        return status == Status.OPENING;
    }

    public String statusToString() {
        return status.toString();
    }

    public void registerMaxStudents(int count) {
        this.maxStudents = count;
    }

    public void enrolement() {
        if (!isOpening()) {
            throw new RuntimeException("해당 강의는 모집중이 아닙니다.");
        }

        if (isFullStudents(currentStudents + 1, maxStudents)) {
            throw new RuntimeException("정원이 가득찼습니다.");
        }

        currentStudents++;
    }

    private boolean isFullStudents(int currentStudents, int maxStudents) {
        return currentStudents > maxStudents;
    }

    public int currentStudents() {
        return currentStudents;
    }

    public int maxStudents() {
        return maxStudents;
    }

    @Override
    public String toString() {
        return "Session {" +
                "id=" + id +
                ", courseId=" + courseId +
                ", coverImageUrl=" + coverImageUrl +
                ", isFree=" + isFree +
                ", status=" + status +
                ", currentStudents=" + currentStudents +
                ", maxStudents=" + maxStudents +
                ", startedAt=" + startedAt +
                ", endedAt=" + endedAt +
                '}';
    }
}
