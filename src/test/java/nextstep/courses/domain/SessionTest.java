package nextstep.courses.domain;

import nextstep.courses.domain.exception.NotRecruitException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {

    @Test
    @DisplayName("수강등록 테스트")
    void testEnrollmentUser() {
        NsUser student = NsUserTest.JAVAJIGI;
        SessionImage sessionImage = SessionImageTest.S1;

        Session session = new Session(sessionImage, SessionStatus.RECRUIT);
        session.enrollmentUser(student);

        assertThat(session.getStudents())
                .hasSize(1)
                .containsExactly(student);
    }

    @ParameterizedTest
    @EnumSource(mode = EnumSource.Mode.EXCLUDE, names = {"RECRUIT"})
    @DisplayName("모집 중이 아닌 강의에 수강신청 하는 경우 에러 발생")
    void testInvalidEnrollmentUser(SessionStatus sessionStatus) {
        NsUser student = NsUserTest.JAVAJIGI;
        SessionImage sessionImage = SessionImageTest.S1;

        Session session = new Session(sessionImage, sessionStatus);

        assertThatThrownBy(() -> session.enrollmentUser(student)).isInstanceOf(NotRecruitException.class);
    }


}
