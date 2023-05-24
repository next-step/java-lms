package nextstep.courses.domain;

import java.time.LocalDate;

import static nextstep.courses.domain.SessionStatus.RECRUITING;

public class Session {

    private Long id;
    private String title;
    private int generation;
    private LocalDate startDate;
    private LocalDate endDate;
    private SessionType type;
    private SessionStatus status;
    private int maxRegisterNum;
    private int currRegisterNum;

    public Session(Long id, String title, int generation, LocalDate startDate, LocalDate endDate, SessionType type, SessionStatus status, int maxRegisterNum, int currRegisterNum) {
        this.id = id;
        this.title = title;
        this.generation = generation;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.status = status;
        this.maxRegisterNum = maxRegisterNum;
        this.currRegisterNum = currRegisterNum;
    }

    public boolean isRegistrable() {
        if (isNotRecruiting()) {
            throw new RuntimeException("강의 모집기간이 아닙니다.");
        }
        if (isCurrRegisterExceedMaxRegister()) {
            throw new RuntimeException("등록 인원이 정원 초과 되었습니다.");
        }
        this.currRegisterNum++;
        return true;
    }

    private boolean isNotRecruiting() {
        return RECRUITING != status;
    }

    private boolean isCurrRegisterExceedMaxRegister() {
        return currRegisterNum >= maxRegisterNum;
    }

    Long id() {
        return this.id;
    }

    int currRegisterNum() {
        return this.currRegisterNum;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", generation=" + generation +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", type=" + type +
                ", status=" + status +
                ", maxRegisterNum=" + maxRegisterNum +
                ", currRegisterNum=" + currRegisterNum +
                '}';
    }
}
