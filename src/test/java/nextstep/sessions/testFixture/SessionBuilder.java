package nextstep.sessions.testFixture;

import java.time.LocalDateTime;
import java.util.HashSet;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionBody;
import nextstep.sessions.domain.SessionDate;
import nextstep.sessions.domain.SessionProgressStatus;
import nextstep.sessions.domain.SessionRecruitingStatus;
import nextstep.sessions.domain.students.SessionRegistration;
import nextstep.sessions.domain.students.Students;

public class SessionBuilder {
  private LocalDateTime startDateTime = LocalDateTime.of(2023, 6, 2, 12, 0);
  private LocalDateTime endDateTime = LocalDateTime.of(2023, 6, 3, 0, 0);
  private String title = "제목";
  private String contents = "내용";
  private byte[] coverImage = null;
  private int capacity = 1;
  private SessionRecruitingStatus sessionRecruitingStatus = SessionRecruitingStatus.NOTHING;
  private SessionProgressStatus sessionProgressStatus = SessionProgressStatus.READY;

  private SessionBuilder() {
  }

  public static SessionBuilder aSession() {
    return new SessionBuilder();
  }

  public SessionBuilder withStartDate(LocalDateTime startDateTime) {
    this.startDateTime = startDateTime;

    return this;
  }

  public SessionBuilder withEndDate(LocalDateTime endDateTime) {
    this.endDateTime = endDateTime;

    return this;
  }

  public SessionBuilder withCapacity(int capacity) {
    this.capacity = capacity;

    return this;
  }

  public SessionBuilder withSessionRecruitingStatus(SessionRecruitingStatus sessionRecruitingStatus) {
    this.sessionRecruitingStatus = sessionRecruitingStatus;

    return this;
  }

  public SessionBuilder withSessionProgressStatus(SessionProgressStatus sessionProgressStatus) {
    this.sessionProgressStatus = sessionProgressStatus;

    return this;
  }

  public Session build() {
    return new Session(new SessionDate(this.startDateTime, this.endDateTime),
        new SessionBody(this.title, this.contents, this.coverImage),
        new SessionRegistration(this.capacity, this.sessionRecruitingStatus, this.sessionProgressStatus));
  }
}
