package nextstep.sessions.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import nextstep.users.domain.NsUser;

/**
 * 객체를 생성한 후에 validate 메서드를 통해 명시적으로 검증해야 한다
 *
 * @date 2023-06-03
 * @author jerryk026
 */
public class Session {

  private LocalDateTime startDateTime;

  private LocalDateTime endDateTime;

  // clob
  private byte[] coverImage;

  // null을 의식해서 Integer -> db를 의식한 설계?
  private int capacity;

  private SessionStatus status = SessionStatus.READY;

  private Set<NsUser> users = new HashSet<>();

  public Session(LocalDateTime startDateTime, LocalDateTime endDateTime, byte[] coverImage, int capacity) {
    this.startDateTime = startDateTime;
    this.endDateTime = endDateTime;
    this.coverImage = coverImage;
    this.capacity = capacity;
  }

  public void recruitStart() {
    this.status = SessionStatus.RECRUITING;
  }

  public void recruitEnd() {
    this.status = SessionStatus.END;
  }

  public void enrollment(NsUser user, LocalDateTime enrollmentDateTime) {
    if (enrollmentDateTime.isBefore(startDateTime) || enrollmentDateTime.isAfter(endDateTime)) {
      throw new IllegalStateException("수강신청 기간이 아닙니다");
    }

    if (users.size() >= capacity) {
      throw new IllegalStateException("수강인원이 초과되었습니다");
    }

    if (users.contains(user)) {
      throw new IllegalStateException("이미 수강신청한 사용자입니다");
    }

    if (!SessionStatus.isRecruiting(status)) {
      throw new IllegalStateException("모집중인 강의만 신청 가능합니다");
    }

    users.add(user);
  }

  public void validate() {
    if (startDateTime.isAfter(endDateTime)) {
      throw new IllegalArgumentException("수강신청 종료 일자는 수강신청 시작 일자보다 빠를 수 없습니다");
    }

    if (capacity < 1) {
      throw new IllegalArgumentException("수강인원 수는 1명 이상이어야 합니다");
    }
  }

  public Set<NsUser> getUsers() {
    return this.users;
  }
}
