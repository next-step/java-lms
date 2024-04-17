package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Session {
    private Long id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private CoverImage coverImage;
    private Long price;
    private SessionStatus status;
    private Long maxStudentCount;
    private List<NsUser> NsUserList = new ArrayList<>();

    public Session() {
    }

    public Session(Long id, String title, LocalDate startDate, LocalDate endDate, CoverImage coverImage, SessionStatus status) {
        this(id, title, startDate, endDate, coverImage, 0L, Long.MAX_VALUE, status);
    }

    public Session(Long id, String title, LocalDate startDate, LocalDate endDate, CoverImage coverImage, Long price, Long maxStudentCount, SessionStatus status) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.coverImage = coverImage;
        this.price = price;
        this.status = status;
        this.maxStudentCount = maxStudentCount;
    }

    public int userCount() {
        return NsUserList.size();
    }

    public void join(NsUser nsUser) {
        if (userCount() >= maxStudentCount) {
            throw new IllegalArgumentException("가능한 수강인원 초과");
        }

        NsUserList.add(nsUser);
    }
}
