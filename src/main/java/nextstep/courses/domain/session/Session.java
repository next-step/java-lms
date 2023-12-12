package nextstep.courses.domain.session;

import nextstep.courses.domain.coverimage.CoverImage;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Session {
    final Long id;
    CoverImage coverImage;
    final Boolean free;
    final List<Long> studentsId = new ArrayList<>();
    final LocalDate startDate;

    LocalDate endDate;


    public Session(CoverImage image, Boolean free, LocalDate startDate, LocalDate endDate) {
        this(0L, image, free, startDate, endDate);
    }

    public Session(Long id, CoverImage image, Boolean free, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.coverImage = image;
        this.free = free;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public void enroll(Payment payment) throws Exception {
        this.studentsId.add(payment.userId());
    }
}
