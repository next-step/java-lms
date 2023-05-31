package nextstep.courses.service;

import config.RollackableIntegrationTest;
import nextstep.courses.domain.SessionTest;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.teacher.SessionTeachers;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SessionTeacherServiceTest extends RollackableIntegrationTest {

  @Autowired
  SessionTeacherService sut;

  @Test
  @DisplayName("강의에 강사가 없으면 빈 강사정보 객체를 리턴한다.")
  void 강의에_강사가_없으면_빈_강사정보_리턴() {
    // given
    Session 강사가_없는_강의 = SessionTest.S7;

    // when
    SessionTeachers teachers = sut.getTeachersOfSession(강사가_없는_강의);

    // then
    Assertions.assertThat(teachers.getNumberOfTeacher())
        .isEqualTo(0);
  }
}
