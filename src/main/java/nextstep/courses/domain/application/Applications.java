package nextstep.courses.domain.application;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import nextstep.courses.DuplicatedException;

public class Applications {

  private Set<Application> applications = new HashSet<>();

  public Applications() {
  }

  public Applications(List<Application> applications) {
    this(new HashSet<>(applications));
  }

  public Applications(Set<Application> applications) {
    this.applications = applications;
  }

  public void apply(Application application) {
    validateApply(application);
    applications.add(application);
  }

  private void validateApply(Application application) {
    if (hasNsUser(application.getNsUserId())) {
      throw new DuplicatedException("회원은 코스를 중복으로 지원할 수 없습니다.");
    }
  }

  private boolean hasNsUser(Long nsUserId) {
    return applications.stream()
        .anyMatch(application -> application.hasNsUser(nsUserId));
  }
}
