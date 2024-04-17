package nextstep.courses.domain.Session;

import nextstep.courses.domain.image.Image;

import java.time.LocalDate;

public class Session {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Image coverImage;
    private final Type type;
    private final int fee;
    private final int maxStudents;
    private Students students;

    private Status status = Status.READY;

    Session(LocalDate startDate, LocalDate endDate, Image coverImage, Type type, int fee, int maxStudents) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.coverImage = coverImage;
        this.type = type;
        this.fee = fee;
        this.maxStudents = maxStudents;
    }
}
