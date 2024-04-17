package nextstep.courses.domain.Session;

import nextstep.courses.domain.image.Image;

import java.time.LocalDate;
import java.util.List;

public class PaySession {
    LocalDate startDate;
    LocalDate endDate;
    Image coverImage;
    Students students = new Students();
    Status status = Status.READY;
    int fee;
    int maxStudents;


    public PaySession(LocalDate startDate, LocalDate endDate, Image coverImage, int fee, int maxStudents) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.coverImage = coverImage;
        this.fee = fee;
        this.maxStudents = maxStudents;
    }

    public void apply(List<Student> studentList) {
        if (status != Status.APPLYING) {
            throw new RuntimeException("수강 신청은 강의 상태가 모집중이 아니면 불가능합니다. 강의 상태 : " + status);
        }

        if (students.getCounts() + studentList.size() > maxStudents) {
            throw new RuntimeException("유료강의는 강의 최대 수강인원을 초과할 수 없습니다. 최대수강인원 : " + maxStudents);
        }

        Students students = new Students(studentList);
        students.isPaidSessionFee(fee);

        this.students.addAll(students);
    }

    public void changeStatus(Status status) {
        this.status = status;
    }
}
