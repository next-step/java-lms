package nextstep.courses.domain;

import java.time.LocalDate;

public class Session {
    private Long id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private CoverImage coverImage;
    private MembershipType type;
    private SessionStatus status;

    public Session() {
    }

    public Session(Long id, String title, LocalDate startDate, LocalDate endDate, CoverImage coverImage, MembershipType type, SessionStatus status) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.coverImage = coverImage;
        this.type = type;
        this.status = status;
    }
}
