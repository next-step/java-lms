package nextstep.courses.domain;

import nextstep.courses.domain.type.SessionStatus;
import nextstep.courses.domain.type.SessionType;

import java.time.LocalDate;

public class Session {
    private final Students students;
    private final int capacity;
    private final SessionStatus sessionStatus;
    private final SessionType sessionType;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String coverImageUrl;

    public Session(Students students,
                   int capacity,
                   SessionStatus sessionStatus,
                   SessionType sessionType,
                   LocalDate startDate,
                   LocalDate endDate,
                   String coverImageUrl) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("시작 일은 종료 일보다 앞서야 합니다.");
        }

        this.students = students;
        this.capacity = capacity;
        this.sessionStatus = sessionStatus;
        this.sessionType = sessionType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.coverImageUrl = coverImageUrl;
    }

    public void register(Long studentId) {
        validateToRegister();

        students.add(studentId);
    }

    public int getStudentsSize() {
        return students.size();
    }

    private void validateToRegister() {
        if (isNotRecruiting()) {
            throw new IllegalStateException("아직 모집중인 강의가 아닙니다.");
        }

        if (capacity == students.size()) {
            throw new IllegalStateException("더 이상 학생을 등록할 수 없습니다.");
        }
    }

    public boolean isNotRecruiting() {
        return this.sessionStatus != SessionStatus.RECRUIT;
    }
}
