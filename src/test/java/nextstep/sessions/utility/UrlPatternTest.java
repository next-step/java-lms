package nextstep.sessions.utility;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class UrlPatternTest {

    @DisplayName("정상적인 이미지 여부를 판단합니다.")
    @ParameterizedTest(name = "{0} = {1}")
    @CsvSource(value = {
            "http://www.test.com/image1,true",
            "http://www.test.com/image1?id=123,true",
            "file://www.test.com/image1,false",
            "dddddd,false",
            "www.test.com/image1,false",
    })
    void test01(String url, boolean expected) {
        assertThat(UrlPattern.isValid(url)).isSameAs(expected);
    }

}
