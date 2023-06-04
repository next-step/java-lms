package nextstep.qna.domain.session;

import nextstep.qna.domain.enums.CourseType;
import nextstep.qna.domain.enums.SesstionStatus;

import java.time.LocalDateTime;

public class Session {

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String coverImage;

    private SesstionStatus status;

    private CourseType courseType;

    private int maxStudent;

}
