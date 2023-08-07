package nextstep.courses.domain.application;

import java.time.LocalDateTime;
import java.util.Objects;
import nextstep.courses.SessionApprovalFailException;
import nextstep.courses.domain.base.BaseInfo;
import nextstep.courses.domain.course.Course;
import nextstep.users.domain.NsUser;

public class Application {

  private final ApplicationInfo applicationInfo;

  private final Long nsUserId;

  private final Long courseId;

  private final BaseInfo baseInfo;

  public Application(Long nsUserId, Long courseId, Long creatorId) {
    this(null, nsUserId, courseId, creatorId);
  }

  public Application(Long id, Long nsUserId, Long courseId, Long creatorId) {
    this(id, false, nsUserId, courseId
        , creatorId, LocalDateTime.now(), LocalDateTime.now());
  }

  public Application(Long id, boolean pass, Long nsUserId, Long courseId,
      Long creatorId, LocalDateTime createdDate, LocalDateTime updatedDate) {
    this(new ApplicationInfo(id, pass),
        nsUserId,
        courseId,
        new BaseInfo(creatorId, createdDate, updatedDate));
  }

  public Application(ApplicationInfo applicationInfo, Long nsUserId, Long courseId,
      BaseInfo baseInfo) {
    this.applicationInfo = applicationInfo;
    this.nsUserId = nsUserId;
    this.courseId = courseId;
    this.baseInfo = baseInfo;
  }

  public static Application createApplication(NsUser nsUser, Course course,
      Applications applications) {
    return createApplication(nsUser, course, applications, nsUser.getId());
  }

  public static Application createApplication(NsUser nsUser, Course course,
      Applications applications, Long creatorId) {
    Application application = new Application(nsUser.getId(), course.getId(), creatorId);
    applications.apply(application);
    return application;
  }

  public void applicationPass() {
    applicationInfo.applicationPass();
  }

  public void applicationFail() {
    applicationInfo.applicationFail();
  }

  public boolean hasNsUser(Long nsUserId) {
    return this.nsUserId.equals(nsUserId);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Application that = (Application) o;
    return Objects.equals(applicationInfo, that.applicationInfo) && Objects
        .equals(nsUserId, that.nsUserId) && Objects.equals(courseId, that.courseId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(applicationInfo, nsUserId, courseId);
  }

  public Long getId() {
    return applicationInfo.getId();
  }

  public boolean isPass() {
    return applicationInfo.isPass();
  }

  public Long getNsUserId() {
    return nsUserId;
  }

  public Long getCourseId() {
    return courseId;
  }

  public Long getCreatorId(){
    return baseInfo.getCreatorId();
  }
}
