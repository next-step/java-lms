package nextstep.courses.domain;

import static nextstep.courses.domain.SelectionStatus.ACCEPTED;
import static nextstep.courses.domain.SelectionStatus.REJECTED;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import nextstep.users.domain.NsUser;

public class SelectedStudents {

  private Map<SelectionStatus, List<NsUser>> selectedStudents;

  public SelectedStudents(Map<SelectionStatus, List<NsUser>> selectedStudents) {
    this.selectedStudents = selectedStudents;
  }

  public List<NsUser> getAcceptedStudents() {
    return selectedStudents.getOrDefault(ACCEPTED, Collections.emptyList());
  }

  public List<NsUser> getRejectedStudents() {
    return selectedStudents.getOrDefault(REJECTED, Collections.emptyList());
  }

}
