package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.SANJIGI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class SessionStudentTest {

    @DisplayName("수강 제한 인원을 넘어서면 예외를 던진다")
    @Test
    void aboveMaxSessionSize() {
        SessionStudents sessionStudents = new SessionStudents(new ArrayList<>(List.of(SANJIGI, JAVAJIGI)));
        SessionStudent sessionStudent = new SessionStudent(1, sessionStudents);
        assertThatThrownBy(() -> sessionStudent.isUnderMaxStudentCount())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수강 인원이 다 찼습니다");
    }
}