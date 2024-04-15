package nextstep.courses.domain;

import nextstep.courses.domain.enums.SessionType;
import nextstep.users.domain.NsUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;


class SelectionTest {

    @Test
    @DisplayName("createNewInstance로 생성 시 hasPaid는 기본 값 false를 가진다.")
    void createNewInstance() {
        Selection selection = Selection.createNewInstance(NsUser.GUEST_USER, getDummySession());
        assertThat(selection.hasPaid()).isFalse();
    }

    @Test
    @DisplayName("createFromData로 생성 시 hasPaid는 주입하는 매개변수를 가진다.")
    void createFromData() {
        boolean hasPaid = false;
        Selection selection = Selection.createFromData(0L, NsUser.GUEST_USER, getDummySession(), hasPaid);
        assertThat(selection.hasPaid()).isEqualTo(hasPaid);
    }

    private Session getDummySession() {
        return PaySession.createNewInstance(
                new Course(),
                SessionInfos.createWithReadyStatus(SessionDate.of(LocalDateTime.now(), LocalDateTime.now())),
                10,
                CoverImageInfo.createNewInstance(1000L, "jpg", 300L, 200L),
                1000L
        );
    }

}