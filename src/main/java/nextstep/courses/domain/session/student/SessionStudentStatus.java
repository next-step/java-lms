package nextstep.courses.domain.session.student;

import org.springframework.util.StringUtils;

public enum SessionStudentStatus {
  REQUEST("승인요청 대기"),
  APPROVE("승인요청 대기"),
  REFUSAL("거절");

  private final String statusName;

  SessionStudentStatus(String statusName) {
    this.statusName = statusName;
  }

  public static SessionStudentStatus findByName(String name) {
    if (!StringUtils.hasLength(name)) {
      return null;
    }

    return SessionStudentStatus.valueOf(name);
  }
}
