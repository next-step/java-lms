package nextstep.courses.domain;

import nextstep.courses.domain.Image.CoverImage;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.HashSet;

public abstract class Session {
    protected CoverImage coverImage;
    protected ProgressPeriod progressPeriod;
    protected SessionState state;
    protected Participants participants;

    protected Session(CoverImage coverImage, LocalDate startDate, LocalDate endDate, SessionState state) {
        this.coverImage = coverImage;
        this.progressPeriod = new ProgressPeriod(startDate, endDate);
        this.state = state;
        this.participants = new Participants(new HashSet<>());
    }


    public abstract void apply(NsUser loginUser);
}
