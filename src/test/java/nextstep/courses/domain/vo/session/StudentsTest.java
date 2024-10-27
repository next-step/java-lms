package nextstep.courses.domain.vo.session;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentsTest {

    private NsUser[] nsUsers;
    private List<NsUser> nsUserList;

    @BeforeEach
    void setUp() {
        nsUsers = new NsUser[]{NsUserTest.JAVAJIGI, NsUserTest.SANJIGI};
        nsUserList = new ArrayList<>(Arrays.asList(nsUsers));
    }

    @Test
    void create() {
        Students list = new Students(nsUserList);
        nsUserList.add(NsUserTest.THIRDJIGI); // 불변 테스트
        Students array = new Students(nsUsers);

        Assertions.assertThat(list).isEqualTo(array);
    }

    @Test
    void add() {
        Students students = new Students();
        students.add(NsUserTest.JAVAJIGI);
        students.add(NsUserTest.SANJIGI);
        students.add(NsUserTest.THIRDJIGI);

        Students expected = new Students(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI, NsUserTest.THIRDJIGI);

        Assertions.assertThat(students).isEqualTo(expected);
    }

    @Test
    void size() {
        Students students = new Students(nsUserList);
        students.add(NsUserTest.THIRDJIGI);
        int size = students.size();

        Assertions.assertThat(size).isEqualTo(3);
    }
}
