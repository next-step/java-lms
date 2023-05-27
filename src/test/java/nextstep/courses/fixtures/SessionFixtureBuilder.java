package nextstep.courses.fixtures;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionPayType;
import nextstep.courses.domain.SessionStatus;

import java.time.LocalDate;

public class SessionFixtureBuilder {
    public String title = "객체지향 프로그래밍";
    public Long creatorId = 1L;
    public Course course = new CourseFixtureBuilder().build();
    public LocalDate startDate = LocalDate.of(2021, 1, 1);
    public LocalDate endDate = LocalDate.of(2021, 2, 1);
    public String coverImageUrl = "http://localhost:8080/images/testCover.png";
    public SessionPayType payType = SessionPayType.FREE;
    public SessionStatus status = SessionStatus.OPENED;
    public int maxNumberOfUsers = 30;
    public int numberOfUsers = 0;

    public SessionFixtureBuilder withStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public SessionFixtureBuilder withEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public Session build() {
        return new Session(title, creatorId, course, startDate, endDate, coverImageUrl, payType, status, maxNumberOfUsers, numberOfUsers);
    }

    public SessionFixtureBuilder withCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
        return this;
    }
}
