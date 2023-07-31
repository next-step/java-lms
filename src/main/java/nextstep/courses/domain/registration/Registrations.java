package nextstep.courses.domain.registration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import nextstep.courses.DuplicatedException;

public class Registrations {

  private Set<Registration> registrations = new HashSet<>();

  public Registrations() {
  }

  public Registrations(List<Registration> registrations) {
    this(new HashSet<>(registrations));
  }

  public Registrations(Set<Registration> registrations) {
    this.registrations = registrations;
  }

  public void register(Registration registration) {
    validateRegistration(registration);
    registrations.add(registration);
  }

  private void validateRegistration(Registration registration) {
    if (hasNsUser(registration.getNsUserId())) {
      throw new DuplicatedException("회원은 강의를 중복으로 신청할 수 없습니다.");
    }
  }

  public boolean hasNsUser(Long nsUserId) {
    return registrations.stream()
        .filter(registration -> !registration.isCanceled())
        .anyMatch(registration -> registration.hasNsUser(nsUserId));
  }

  public boolean isRegistrationFulled(int maxRecruitment) {
    return getRegisterCount() >= maxRecruitment;
  }

  private int getRegisterCount() {
    return (int) registrations.stream()
        .filter(registration -> !registration.isCanceled())
        .count();
  }
}
