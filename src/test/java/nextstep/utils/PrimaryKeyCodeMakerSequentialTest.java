package nextstep.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PrimaryKeyCodeMakerSequentialTest {

    @DisplayName("")
    @Test
    public void gg() {
        //given
        //when
        //then
        PrimaryKeyCodeMakerSequential keyMakerSequentialLong = new PrimaryKeyCodeMakerSequential();

        for (int i = 0; i < 100; i++) {
            System.out.println(keyMakerSequentialLong.generate());
        }
    }
}