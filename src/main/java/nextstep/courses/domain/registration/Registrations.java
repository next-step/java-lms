package nextstep.courses.domain.registration;

import java.util.ArrayList;
import java.util.List;

public class Registrations {
  private static final int UNLIMITED = -1;

  private final List<Registration> registrations;
  private final int maxCapacity;

  public Registrations() {
    this(new ArrayList<>(), UNLIMITED);
  }

  public Registrations(int maxCapacity) {
    this(new ArrayList<>(), maxCapacity);
  }

  public Registrations(List<Registration> registrations, int maxCapacity) {
    this.registrations = registrations;
    this.maxCapacity = maxCapacity;
  }

  public static Registrations of(List<Registration> registrations, int maxCapacity) {
    return new Registrations(registrations, maxCapacity);
  }

  public Registrations add(Registration registration) {
    validateCapacity();
    List<Registration> newList = new ArrayList<>(registrations);
    newList.add(registration);
    return new Registrations(newList, maxCapacity);
  }

  public void validateCapacity() {
    if (isUnlimited()) {
      return;
    }
    if (registrations.size() >= maxCapacity) {
      throw new IllegalArgumentException("최대 수강 인원을 초과할 수 없습니다.");
    }
  }

  private boolean isUnlimited() {
    return maxCapacity == UNLIMITED;
  }

  public int getMaxCapacity() {
    return maxCapacity;
  }

  public int count() {
    return registrations.size();
  }
}