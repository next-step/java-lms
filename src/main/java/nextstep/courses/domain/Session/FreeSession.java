package nextstep.courses.domain.Session;

import nextstep.courses.domain.image.Image;

import java.time.LocalDate;
import java.util.List;

public class FreeSession {
    LocalDate startDate;
    LocalDate endDate;
    Image coverImage;
    Students students;
    Status status = Status.READY;

    FreeSession(LocalDate startDate, LocalDate endDate, Image coverImage) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.coverImage = coverImage;
    }

    public void apply(List<Student> studentList) {
        if (status != Status.APPLYING) {
            throw new RuntimeException("수강 신청은 강의 상태가 모집중이 아니면 불가능합니다. 강의 상태 : " + status);
        }

        this.students.addAll(students);
    }

    public void changeStatus(Status status) {
        this.status = status;
    }
}
