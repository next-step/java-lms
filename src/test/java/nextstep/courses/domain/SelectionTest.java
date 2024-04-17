package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class SelectionTest {

    @Test
    @DisplayName("createNewInstance로 생성 시 hasPaid는 기본 값 false를 가진다.")
    void createNewInstance() {
        Selection selection = Selection.createNewInstance(0L, 0L);
        assertThat(selection.hasPaid()).isFalse();
    }

    @Test
    @DisplayName("createFromData로 생성 시 hasPaid는 주입하는 매개변수를 가진다.")
    void createFromData() {
        boolean hasPaid = false;
        Selection selection = Selection.createFromData(0L, 0L, 0L, hasPaid);
        assertThat(selection.hasPaid()).isEqualTo(hasPaid);
    }

}