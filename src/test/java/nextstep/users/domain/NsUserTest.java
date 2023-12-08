package nextstep.users.domain;

public class NsUserTest {
    public static final NsUser JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net", SelectionStatus.SELECTED);
    public static final NsUser SANJIGI = new NsUser(2L, "sanjigi", "password", "name", "sanjigi@slipp.net", SelectionStatus.SELECTED);
    public static final NsUser HONUX = new NsUser(3L, "honux", "password", "name", "honux@slipp.net", SelectionStatus.NOT_SELECTED);
    public static final NsUser CRONG = new NsUser(4L, "crong", "password", "name", "crong@slipp.net", SelectionStatus.NOT_SELECTED);
}
