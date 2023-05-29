package nextstep.courses.domain;

import java.util.HashSet;
import java.util.Set;
import nextstep.users.domain.NsUser;

public class Registrations {

  private Set<Registration> registrations = new HashSet<>();

  public Registrations() {
  }

  public void register(Registration registration) {
    registrations.add(registration);
  }

  public boolean isRegistrationFulled(int maxRecruitment) {
    return getRegisterCount() >= maxRecruitment;
  }

  private int getRegisterCount() {
    return (int) registrations.stream()
        .filter(registration -> !registration.isCanceled())
        .count();
  }

  public boolean hasNsUser(NsUser nsUser) {
    return registrations.stream()
        .filter(registration -> !registration.isCanceled())
        .anyMatch(registration -> registration.hasNsUser(nsUser));
  }
}
