package nextstep.session.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StudentTest {

    @DisplayName("두 학생을 비교할 수 있다.")
    @Test
    void compareUser() {
        // given
        Student student1 = new Student(NsUserTest.JAVAJIGI);
        Student student2 = new Student(NsUserTest.JAVAJIGI);
        Student student3 = new Student(NsUserTest.SANJIGI);

        // then
        assertThat(student1.matchUser(student2))
                .isTrue();
        assertThat(student1.matchUser(student3))
                .isFalse();
    }
}
