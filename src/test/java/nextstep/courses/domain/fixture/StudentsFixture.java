package nextstep.courses.domain.fixture;

import nextstep.courses.domain.student.Students;

import java.util.ArrayList;
import java.util.Arrays;

import static nextstep.users.domain.NsUserFixture.HYUNGKI;
import static nextstep.users.domain.NsUserFixture.JAVAJIGI;
import static nextstep.users.domain.NsUserFixture.SANJIGI;

public class StudentsFixture {

    public static Students 수강신청_학생들;

    static {
        수강신청_학생들 = new Students(3, new ArrayList<>(Arrays.asList(
                JAVAJIGI,
                SANJIGI,
                HYUNGKI
        )));
    }

}
