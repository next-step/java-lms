package nextstep.users.domain;

import java.math.BigDecimal;

public class NsUserTest {
    public static final NsUser JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    public static final NsUser SANJIGI = new NsUser(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

    public static NsUser createNsUser(Long id, Long balance) {
        return new NsUser(id, "아이디", "비밀번호", "이름", new BigDecimal(balance));
    }
}
