package nextstep.courses.domain.students;

import nextstep.courses.CanNotEnrollSameStudentsException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class StudentsTest {

    @DisplayName("동일한 학생은 추가할 수 없습니다.")
    @Test
    void addSameStudent() {
        // given
        List<NsUser> users = new ArrayList<>();
        users.add(NsUserTest.JAVAJIGI);
        Students students = new Students(users);
        // when
        // then
        Assertions.assertThatThrownBy(() -> students.add(NsUserTest.JAVAJIGI))
                .isInstanceOf(CanNotEnrollSameStudentsException.class)
                .hasMessage("동일한 학생이 2번 수강신청할 수 없습니다.");
    }

}