package nextstep.users.fixtures;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.Type;

public class NsUserFixtures {
    public static final NsUser TEACHER_JAVAJIGI_1L = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net" , Type.TEACHER);
    public static final NsUser TEACHER_SANJIGI_2L = new NsUser(2L, "sanjigi", "password", "name", "sanjigi@slipp.net",Type.TEACHER);
    public static final NsUser TEACHER_APPLE_3L = new NsUser(3L, "apple", "password", "name", "apple@slipp.net",Type.TEACHER);
    public static final NsUser STUDENT_ERIC_4L = new NsUser(4L, "apple", "password", "name", "apple@slipp.net",Type.STUDENT);
}
