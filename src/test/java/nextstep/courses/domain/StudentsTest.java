package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class StudentsTest {
    @Test
    public void isGreaterEqualThan_True() {
        Students students = new Students();
        students.add(new NsUser(1L, "userId1", "password", "name", "email"));
        students.add(new NsUser(2L, "userId2", "password", "name", "email"));
        assertThat(students.isGreaterEqualThan(2L)).isTrue();
    }

    @Test
    public void isGreaterEqualThan_False() {
        Students students = new Students();
        students.add(new NsUser(1L, "userId1", "password", "name", "email"));
        students.add(new NsUser(2L, "userId2", "password", "name", "email"));
        assertThat(students.isGreaterEqualThan(3L)).isFalse();
    }

    @Test
    public void add() {
        Students students = new Students();
        students.add(new NsUser(1L, "userId1", "password", "name", "email"));
        students.add(new NsUser(2L, "userId2", "password", "name", "email"));

        assertThat(students.isGreaterEqualThan(3L)).isFalse();

        NsUser user = new NsUser(3L, "userId3", "password", "name", "email");
        students.add(user);

        assertThat(students.isGreaterEqualThan(3L)).isTrue();
    }

    @Test
    public void add_AlreadyRegistered() {
        Students students = new Students();
        NsUser user = new NsUser(1L, "userId1", "password", "name", "email");
        students.add(user);
        assertThatIllegalStateException().isThrownBy(() -> students.add(user));
    }
}
