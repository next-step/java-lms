package nextstep.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KeyMakerSequentialLongTest {

    @DisplayName("")
    @Test
    public void gg() {
        //given
        //when
        //then
        KeyMakerSequentialLong keyMakerSequentialLong = new KeyMakerSequentialLong();

        for(int i=0 ; i<100 ; i++) {
            System.out.println(keyMakerSequentialLong.generate());
        }
    }
}