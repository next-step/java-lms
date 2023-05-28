package nextstep.courses.domain;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

public class Session {
    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    private File coverImage;

    private boolean isFree;

    private Status status;

    private int currentStudents;

    private int maxStudents;

    public Session(LocalDateTime startedAt, LocalDateTime endedAt, boolean isFree, Status status, int currentStudents, int maxStudents) {
        if (startedAt.isAfter(endedAt)) {
            throw new IllegalArgumentException("시작 날짜가 종료 날짜보다 늦을 수 없습니다.");
        }

        if (currentStudents > maxStudents) {
            throw new IllegalArgumentException("현재 수강 인원은 최대 수강 인원을 초과할 수 없습니다.");
        }

        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.isFree = isFree;
        this.status = status;
        this.currentStudents = currentStudents;
        this.maxStudents = maxStudents;
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

    public void registerCoverImage(File file) {
        this.coverImage = file;
    }

    public File coverImage() {
        return this.coverImage;
    }

    public void patchStatus(Status status) {
        this.status = status;
    }

    public boolean isOpening() {
        return status == Status.OPENING;
    }

    public void registerMaxStudents(int count) {
        this.maxStudents = count;
    }

    public void enrolement() {
        if (isOpening()) {
            throw new RuntimeException("해당 강의는 모집중이 아닙니다.");
        }

        if (isFullStudents()) {
            throw new RuntimeException("정원이 가득찼습니다.");
        }

        currentStudents++;
    }

    private boolean isFullStudents() {
        return currentStudents >= maxStudents;
    }

    public int currentStudents() {
        return currentStudents;
    }
}
