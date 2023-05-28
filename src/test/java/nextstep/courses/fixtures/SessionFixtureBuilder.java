package nextstep.courses.fixtures;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionStatus;
import nextstep.courses.domain.SessionUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SessionFixtureBuilder {
    public Long id = 0L;
    public String title = "객체지향 프로그래밍";
    public Long creatorId = 1L;
    public Course course = new CourseFixtureBuilder().build();
    public LocalDate startDate = LocalDate.of(2021, 1, 1);
    public LocalDate endDate = LocalDate.of(2021, 2, 1);
    public String coverImageUrl = "http://localhost:8080/images/testCover.png";
    public Long price = 0L;
    public SessionStatus status = SessionStatus.OPENED;
    public int maxNumberOfUsers = 30;
    public List<SessionUser> registeredUsers = new ArrayList<>();

    public SessionFixtureBuilder withStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public SessionFixtureBuilder withEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public Session build() {
        return new Session(id, title, creatorId, course, startDate, endDate, coverImageUrl, price, status,
                maxNumberOfUsers,
                registeredUsers, LocalDateTime.now(), null);
    }

    public SessionFixtureBuilder withCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
        return this;
    }

    public SessionFixtureBuilder withPrice(Long price) {
        this.price = price;
        return this;
    }

    public SessionFixtureBuilder withStatus(SessionStatus status) {
        this.status = status;
        return this;
    }

    public SessionFixtureBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public SessionFixtureBuilder withRegisterdUsers(List<Long> registerdUsers) {
        registerdUsers.forEach(userId -> this.registeredUsers.add(new SessionUser(userId)));
        return this;
    }
}
