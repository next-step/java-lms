package nextstep.session.domain;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

class StudentsTest {

    @Test
    public void addTest() {
        NsUser student1 = new NsUser(1L, "aaa", "111", "aaa", "email11");
        NsUser student2 = new NsUser(2L, "bbb", "222", "bbb", "email1s");

        Students students = new Students(new ArrayList<>());

        assertThatNoException().isThrownBy(() -> students.add(student1));
        assertThatIllegalArgumentException().isThrownBy(() -> students.add(student1)).withMessageMatching("이미 존재하는 학생입니다.");
        assertThatNoException().isThrownBy(() -> students.add(student2));
        assertThat(students.size()).isEqualTo(2);
    }

}