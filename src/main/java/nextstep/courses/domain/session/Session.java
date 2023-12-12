package nextstep.courses.domain.session;

import nextstep.courses.domain.coverimage.CoverImage;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Session {
    final CoverImage coverImage;
    final Boolean free;
    final List<NsUser> students = new ArrayList<>();
    final LocalDate startDate;

    LocalDate endDate;

    public Session() {
        this(null, null, LocalDate.now(), null);
    }

    public Session(boolean free) {
        this(null, free, LocalDate.now(), null);
    }

    public Session(LocalDate startDate, LocalDate endDate) {
        this(null, null, startDate, endDate);
    }

    public Session(CoverImage image, Boolean free, LocalDate startDate, LocalDate endDate) {
        this.coverImage = image;
        this.free = free;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public void register(NsUser user) throws Exception {
        this.students.add(user);
    }
}
