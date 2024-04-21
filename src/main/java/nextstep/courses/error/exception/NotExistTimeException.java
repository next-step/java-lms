package nextstep.courses.error.exception;

import java.text.MessageFormat;
import java.time.LocalDateTime;

public class NotExistTimeException extends RuntimeException {

    public NotExistTimeException(LocalDateTime startDate, LocalDateTime endDate) {
        super(MessageFormat.format("{0} 입력값: {1}, {2}", "시작날짜 또는 종료날짜가 입력되지 않았습니다",
            startDate, endDate));
    }
}
