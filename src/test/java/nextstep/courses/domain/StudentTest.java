package nextstep.courses.domain;

import nextstep.courses.domain.session.Session;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StudentTest {

    @Test
    void 신청한_사람중_승인을_해줄_수_있다() {
        Students students = new Students(NsUser.GUEST_USER);
        students.acceptStudent(new Student(NsUser.GUEST_USER));
        assertThat(students).isEqualTo(new Students(NsUser.GUEST_USER));
    }

    @Test
    void 선발되지_않은_사람은_수강_취소가_된다() {
        Students students = new Students(NsUser.GUEST_USER);
        students.cancelStudent(new Student(NsUser.GUEST_USER));
        assertThat(students).isEqualTo(new Students());
    }
}
