package nextstep.users.domain;

import nextstep.courses.domain.Cost;
import nextstep.courses.domain.SessionTest;
import nextstep.courses.domain.SessionsTest;
import nextstep.courses.domain.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.SessionTest.createSession;
import static org.assertj.core.api.Assertions.*;

public class StudentTest {
    public static Student student1 = new Student(NsUserTest.SANJIGI, createSession(Cost.FREE, State.RECRUIT_START, 30));
    public static Student student2 = new Student(NsUserTest.JAVAJIGI, createSession(Cost.FREE, State.RECRUIT_START, 30));

}
