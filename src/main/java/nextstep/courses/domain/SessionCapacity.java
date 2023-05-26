package nextstep.courses.domain;

import exception.LmsException;
import java.util.ArrayList;
import java.util.List;
import nextstep.courses.exception.SessionExceptionCode;
import nextstep.users.domain.NsUser;

public class SessionCapacity {
  private int maxCapacity;
  private List<NsUser> personnel;

  public SessionCapacity(int maxCapacity) {
    this.maxCapacity = maxCapacity;
    this.personnel = new ArrayList<>(maxCapacity);
  }

  public void addPersonnel(NsUser nsUser) {
    if (isPersonnelSizeIsFull()) {
      throw new LmsException(SessionExceptionCode.EXCEED_MAX_PERSONNEL_COUNT);
    }

    personnel.add(nsUser);
  }

  private boolean isPersonnelSizeIsFull() {
    return this.personnel.size() >= maxCapacity;
  }

  public int getCurrentSize() {
    return personnel.size();
  }
}
