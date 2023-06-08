package nextstep.courses.domain;

import nextstep.courses.domain.enrollment.Students;
import nextstep.courses.exception.CannotRegisterException;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.fixture.SessionFixtures.STUDENT_1;
import static nextstep.courses.domain.fixture.SessionFixtures.STUDENT_2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class StudentsTest {

    @Test
    public void 유저_등록이_잘_동작해야_한다() throws Exception {
        //given
        Students students = new Students(2);

        //when
        students.add(STUDENT_1);
        students.add(STUDENT_2);

        //then
        assertThat(students.count()).isEqualTo(2);
    }

    @Test
    public void 이미_등록한_유저는_등록할_수_없다() throws Exception {
        //given
        Students students = new Students(2);

        //when
        students.add(STUDENT_1);

        //then
        assertThatThrownBy(() -> students.add(STUDENT_1))
                .isInstanceOf(CannotRegisterException.class);
    }

    @Test
    public void 최대_수강_인원을_초과할_수_없다() throws Exception {
        //given
        Students users = new Students(1);

        //when
        users.add(STUDENT_1);

        //then
        assertThatThrownBy(() -> users.add(STUDENT_2))
                .isInstanceOf(CannotRegisterException.class);
    }

    @Test
    public void 현재_수강_인원보다_제한_인원을_작게_설정할_수_없다() throws Exception {
        //given
        Students users = new Students(2);

        //when
        users.add(STUDENT_1);
        users.add(STUDENT_2);

        //then
        assertThatThrownBy(() -> users.updateCapacity(1))
                .isInstanceOf(IllegalStateException.class);
    }
}