package nextstep.users.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class NsTeacherTest {

    @Test
    public void chargeSession(){
        NsTeacher nsTeacher = new NsTeacher(1L, "javajigi", "password", "name", "javajigi@slipp.net");
        nsTeacher.chargeSession(1L);
        nsTeacher.chargeSession(2L);

        assertThat(nsTeacher.sessionList()).hasSize(2);
        assertThat(nsTeacher.hasSession(1L)).isTrue();
        assertThat(nsTeacher.hasSession(3L)).isFalse();
    }
}
