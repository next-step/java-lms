package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class StudentsTest {

    private Students students;

    @BeforeEach
    void setUp(){
        students = new Students(List.of(NsUserTest.JAVAJIGI, NsUserTest.JAVAJIGI));
    }

    @Test
    void 수강인원_추가_가능() {
        Assertions.assertThat(students.isPossibleAdd(3)).isTrue();
    }

    @Test
    void 수강인원_추가_불가능() {
        Assertions.assertThat(students.isPossibleAdd(2)).isFalse();
    }

}
