package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Session {
    private final Long id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private CoverImage coverImage;
    protected SessionStatus status;
    protected List<NsUser> userList = new ArrayList<>();

    public Session(Long id, String title, LocalDate startDate, LocalDate endDate, CoverImage coverImage, SessionStatus status) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.coverImage = coverImage;
        this.status = status;
        this.userList = new ArrayList<>();
    }

    public Session(Long id, String title, LocalDate startDate, LocalDate endDate, CoverImage coverImage, SessionStatus status, List<NsUser> users) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.coverImage = coverImage;
        this.status = status;
        this.userList = users;
    }

    public int userCount() {
        return userList.size();
    }

    public boolean hasUser(NsUser user) {
        return userList.contains(user);
    }

    public abstract void join(NsUser user);

    public boolean isId(Long sessionId) {
        return this.id == sessionId;
    }
}
