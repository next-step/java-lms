package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class StudentsTest {
    @Test
    public void isGreaterEqualThan_True() {
        Students students = new Students();
        students.add(NsUserTest.JAVAJIGI);
        students.add(NsUserTest.SANJIGI);
        assertThat(students.isGreaterEqualThan(2L)).isTrue();
    }

    @Test
    public void isGreaterEqualThan_False() {
        Students students = new Students();
        students.add(NsUserTest.JAVAJIGI);
        students.add(NsUserTest.SANJIGI);
        assertThat(students.isGreaterEqualThan(3L)).isFalse();
    }

    @Test
    public void add() {
        Students students = new Students();
        students.add(NsUserTest.JAVAJIGI);
        students.add(NsUserTest.SANJIGI);

        assertThat(students.isGreaterEqualThan(3L)).isFalse();

        students.add(NsUserTest.YESJIGI);

        assertThat(students.isGreaterEqualThan(3L)).isTrue();
    }

    @Test
    public void add_Duplicate() {
        Students students = new Students();
        students.add(NsUserTest.JAVAJIGI);
        assertThatIllegalStateException().isThrownBy(() -> students.add(NsUserTest.JAVAJIGI));
    }

    @Test
    public void remove() {
        Students students = new Students();
        students.add(NsUserTest.JAVAJIGI);
        students.add(NsUserTest.SANJIGI);

        assertThat(students.isGreaterEqualThan(2L)).isTrue();

        students.remove(NsUserTest.SANJIGI);

        assertThat(students.isGreaterEqualThan(2L)).isFalse();
    }

    @Test
    public void remove_NotFound() {
        Students students = new Students();
        students.add(NsUserTest.JAVAJIGI);
        assertThatIllegalStateException().isThrownBy(() -> students.remove(NsUserTest.SANJIGI));
    }
}
