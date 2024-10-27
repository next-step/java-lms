package nextstep.session;

import nextstep.session.image.Image;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DateRangeTest {

    @DisplayName("강의를 생성할 시 시작일보다 종료일이 빠르면 예외가 발생한다.")
    @Test
    void createSessionNotExistStartDateThrowExceptionTest() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = simpleDateFormat.parse("2023-05-05");
        Date endDate = simpleDateFormat.parse("2023-04-05");

        assertThatThrownBy(() -> new DateRange(startDate, endDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("종료일이 시작일보다 빠릅니다.");
    }
}
