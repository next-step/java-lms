package nextstep.courses.domain;

import nextstep.courses.CannotRegisterException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.SessionStatus.PREPARING;
import static nextstep.courses.domain.SessionStatus.RECRUITING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RegisterTest {

    private Register r1, r2, r3;

    @BeforeEach
    void setup() {
        r1 = new RegisterBuilder()
                .withStatus(PREPARING)
                .withMaxRegisterCount(2)
                .build();

        r2 = new RegisterBuilder()
                .withStatus(RECRUITING)
                .withMaxRegisterCount(2)
                .withStudent(new NsUser())
                .build();

        r3 = new RegisterBuilder()
                .withStatus(RECRUITING)
                .withMaxRegisterCount(2)
                .withStudent(new NsUser())
                .withStudent(new NsUser())
                .build();
    }

    @Test
    void create() {
        assertThat(r1).isNotNull();
    }

    @Test
    void 강의_모집기간이_아닙니다_예외처리() {
        assertThatThrownBy(() -> r1.add(new NsUser()))
                .isInstanceOf(CannotRegisterException.class)
                .hasMessage("강의 모집기간이 아닙니다.");
    }

    @Test
    void 등록_인원_정원_초과_예외처리() {
        assertThatThrownBy(() -> r3.add(new NsUser()))
                .isInstanceOf(CannotRegisterException.class)
                .hasMessage("등록 인원이 정원 초과 되었습니다.");
    }

    @Test
    void 수강_신청시_학생_추가() {
        int beforeRegisterCount = r2.students().size();
        r2.add(new NsUser());
        int currRegisterCount = r2.students().size();

        assertThat(beforeRegisterCount + 1).isEqualTo(currRegisterCount);
    }
}
