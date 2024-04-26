package nextstep.courses.error.exception;

import java.text.MessageFormat;
import java.time.LocalDateTime;

public class EndDateBeforeStartDateException extends RuntimeException {

    public EndDateBeforeStartDateException(LocalDateTime startDate, LocalDateTime endDate) {
        super(MessageFormat.format("등록일이 시작일보다 이전입니다, 입력값: {0}, {1}", startDate, endDate));
    }
}
