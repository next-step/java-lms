package nextstep.courses.domain.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.courses.DuplicatedException;
import nextstep.courses.domain.course.Course;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ApplicationTest {

  private Course c1;
  private Course c2;
  private NsUser javajigi;
  private NsUser sanjigi;
  private Applications applications;

  @BeforeEach
  public void setUp() {
    c1 = new Course(1L, "ssafy", 3L);
    c2 = new Course(2L, "nextstep", 3L);
    javajigi = new NsUser(1L, "javajigi", "password", "name",
        "javajigi@slipp.net");
    sanjigi = new NsUser(2L, "sanjigi", "password", "name",
        "sanjigi@slipp.net");
    applications = new Applications();
  }

  @Test
  @DisplayName("코스에 처음 지원을 생성하면 Fail 상태이다.")
  public void createApplication(){
    Application application = Application.createApplication(javajigi, c1, applications);

    assertThat(application.isPass()).isFalse();
  }

  @Test
  @DisplayName("사용자는 코스를 중복 지원할 수 없다.")
  public void createApplication_throwException_ifUserAlreadyApply(){
    Application.createApplication(javajigi, c1, applications);

    assertThatThrownBy(() -> Application.createApplication(javajigi, c1, applications))
        .isInstanceOf(DuplicatedException.class);
  }

  @Test
  @DisplayName("관리자는 지원을 승인할 수 있다.")
  public void pass(){
    Application application = Application.createApplication(javajigi, c1, applications);

    application.applicationPass();

    assertThat(application.isPass()).isTrue();
  }
}
