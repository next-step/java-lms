package nextstep.users.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class NsUserTest {
    public static final NsUser JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    public static final NsUser SANJIGI = new NsUser(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

    @Test
    void idIsUniqueKey(){
      assertThat(JAVAJIGI).isEqualTo(new NsUser(1L, "", "", "", ""));
    }
}
