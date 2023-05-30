package nextstep.courses.application;

import static org.assertj.core.api.Assertions.*;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class SessionSignUpServiceTest {

  @Autowired
  private SessionSignUpService sessionSignUpService;

  @Autowired
  private SessionRepository sessionRepository;


  @Test
  void sanjigi_학생이_모집중이고_수강인원_다찬_Session_100L곳에_수강신청시_실패() {
    assertThatThrownBy(() -> sessionSignUpService.signUp(100L, "sanjigi"))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("최대 인원수를 넘어서 수강 신청할 수 없습니다.");
  }


  @Test
  void sanjigi_학생이_수강인원_1자리_남은곳에_수강신청_성공() {
    sessionSignUpService.signUp(200L, "sanjigi");

    int studentSize = sessionRepository.findById(200L).getStudents().size();

    assertThat(studentSize).isEqualTo(3);
  }

  @Test
  void sanjigi_학생이_아직_준비중인_Session_300L곳에_수강신청시_실패() {
    assertThatThrownBy(() -> sessionSignUpService.signUp(300L, "sanjigi"))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("준비중인 세션은 수강 신청할 수 없습니다.");
  }

  @Test
  void sanjigi_학생이_종료된_Session에_수강신청시_실패() {
    assertThatThrownBy(() -> sessionSignUpService.signUp(400L, "sanjigi"))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("종료된 세션은 수강 신청할 수 없습니다.");
  }

  @Test
  void sanjigi_학생이_존재하지_않는_Session_500L곳에_수강신청시_실패() {
    assertThatThrownBy(() -> sessionSignUpService.signUp(500L, "sanjigi"))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("존재하지 않는 세션입니다.");
  }

  /**
   *   public void approve(Long sessionId, String userId) {
   *     Session session = sessionRepository.findById(sessionId);
   *
   *     NsUser user = userRepository.findByUserId(userId);
   *
   *     session.approve(user);
   *
   *     sessionRepository.saveApproved(sessionId, user.getId());
   *   }
   *
   *   public void reject(Long sessionId, String userId) {
   *     Session session = sessionRepository.findById(sessionId);
   *
   *     NsUser user = userRepository.findByUserId(userId);
   *
   *     session.reject(user);
   *
   *     sessionRepository.saveRejected(sessionId, user.getId());
   *   }
   */
  @Test
  void soochan_학생이_모집중이고_수강자리_2자리_남은_Session_200L곳에_수강신청후_승인시_성공() {
    sessionSignUpService.signUp(200L, "soochan");

    sessionSignUpService.approve(200L, "soochan");

    Session session = sessionRepository.findById(200L);

    int approvedCount = (int) session.getStudents().listOfStudents()
        .stream()
        .filter(Student::isApproved).count();

    assertThat(session.getStudents().size()).isEqualTo(3);
    assertThat(approvedCount).isEqualTo(2);
  }

  @Test
  void soochan_학생이_모집중이고_수강자리_2자리_남은_Session_200L곳에_수강신청후_거절시_성공() {
    sessionSignUpService.signUp(200L, "soochan");

    sessionSignUpService.reject(200L, "soochan");

    Session session = sessionRepository.findById(200L);

    int rejectedCount = (int) session.getStudents().listOfStudents()
        .stream()
        .filter(Student::isRejected).count();

    assertThat(session.getStudents().size()).isEqualTo(3);
    assertThat(rejectedCount).isEqualTo(1);
  }





}