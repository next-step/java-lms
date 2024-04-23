package nextstep.courses.domain.Session;

import nextstep.courses.domain.image.Image;

import java.time.LocalDate;
import java.util.List;

public class Session {
    private final SessionDuration sessionDuration;
    private final Image coverImage;
    private final int fee;
    private final int maxStudents;
    private final Students students = new Students();
    private SessionStatus sessionStatus = SessionStatus.READY;

    public Session(LocalDate startDate, LocalDate endDate, Image coverImage) {
        this.sessionDuration = new SessionDuration(startDate, endDate);
        this.coverImage = coverImage;
        this.fee = 0;
        this.maxStudents = Integer.MAX_VALUE;
    }

    public Session(LocalDate startDate, LocalDate endDate, Image coverImage, int fee, int maxStudents) {
        this.sessionDuration = new SessionDuration(startDate, endDate);
        this.coverImage = coverImage;
        this.fee = fee;
        this.maxStudents = maxStudents;
    }

    public void apply(List<Student> studentList) {
        if (!sessionStatus.isApplying()) {
            throw new RuntimeException("수강 신청은 강의 상태가 모집중이 아니면 불가능합니다. 강의 상태 : " + sessionStatus);
        }

        Students newStudents = new Students(studentList);

        if (isPaySession()) {
            newStudents.isPaidSessionFee(fee);

            isMaxStudents(students.getCounts(), newStudents.getCounts());
        }

        this.students.addAll(students);
    }

    public void changeStatus(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    private boolean isPaySession() {
        return this.fee > 0;
    }

    private void isMaxStudents(int oldStudentsCounts, int newStudentsCounts) {
        if (oldStudentsCounts + newStudentsCounts > maxStudents) {
            throw new RuntimeException("유료 강의는 최대 수강인원을 초과할 수 없습니다. 최대수강인원 : " + maxStudents);
        }
    }
}
