package nextstep.courses.domain.registration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Registrations {
  private final List<Registration> registrations;

  public Registrations() {
    this(new ArrayList<>());
  }

  public Registrations(List<Registration> registrations) {
    this.registrations = registrations;
  }

  public Registrations add(Registration registration) {
    List<Registration> newList = new ArrayList<>(registrations);
    newList.add(registration);
    return new Registrations(newList);
  }

  public int count() {
    return registrations.size();
  }

  public boolean contains(Long studentId) {
    return registrations.stream()
        .anyMatch(r -> r.contains(studentId));
  }

}