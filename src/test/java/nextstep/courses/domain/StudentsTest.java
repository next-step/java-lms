package nextstep.courses.domain;

import nextstep.courses.CannotRegisterSessionException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StudentsTest {

    @Test
    public void create() throws Exception {
        assertThat(new Students())
                .isNotNull()
                .isInstanceOf(Students.class);
    }

    @Test
    public void 단일_학생_등록() throws Exception {
        Students students = new Students();
        students.register(new NsUser());
        assertThat(students.size())
                .isEqualTo(1);
    }

    @Test
    public void 다수_학생_등록() throws Exception {
        final List<NsUser> nsUsers = Arrays.asList(new NsUser(), new NsUser(), new NsUser());
        Students students = new Students();
        students.register(nsUsers);
        assertThat(students.size())
                .isEqualTo(3);
    }

    @Test
    public void 정원초과() throws Exception {
        final List<NsUser> nsUsers = Arrays.asList(new NsUser(), new NsUser(), new NsUser());
        Students students = new Students(1);

        assertThatThrownBy(() -> students.register(nsUsers))
                .isInstanceOf(CannotRegisterSessionException.class);
    }

}
