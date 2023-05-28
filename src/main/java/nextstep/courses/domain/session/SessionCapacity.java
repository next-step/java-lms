package nextstep.courses.domain.session;

public class SessionCapacity {
  private final int maxCapacity;

  public SessionCapacity(int maxCapacity) {
    this.maxCapacity = maxCapacity;
  }

  public int getMaxCapacity() {
    return maxCapacity;
  }
}
