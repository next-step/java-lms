package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;
import nextstep.users.domain.NsUser;

public class Registrations {

  private List<Registration> registrations = new ArrayList<>();

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
