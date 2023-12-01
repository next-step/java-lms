package nextstep.qna.domain.step2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class StudentsTest {

    @Test
    public void addStudent() {
        Students students = new Students(new ArrayList<>());

        students.add(new Student("aaa", "1"));
        Assertions.assertThat(students.size()).isEqualTo(1);

        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> students.add(new Student("aaa", "1")))
                .withMessageMatching("이미 존재하는 학생입니다.");

    }
}
