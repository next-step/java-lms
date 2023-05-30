package nextstep.courses.domain.session;

import org.springframework.util.StringUtils;

public enum SessionRecruitStatus {

  RECRUIT("모집중"),
  NON_RECRUIT("비모집중")
  ;

  private final String recruitStatusName;

  SessionRecruitStatus(String recruitStatusName) {
    this.recruitStatusName = recruitStatusName;
  }

  public String getRecruitStatusName() {
    return recruitStatusName;
  }

  public static SessionRecruitStatus findByName(String name) {
    if (!StringUtils.hasLength(name)) {
      return null;
    }

    return SessionRecruitStatus.valueOf(name);
  }
}
