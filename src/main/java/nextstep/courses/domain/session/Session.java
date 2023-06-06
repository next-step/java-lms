package nextstep.courses.domain.session;

import nextstep.courses.enums.CourseType;
import nextstep.courses.enums.SesstionStatus;

import java.time.LocalDateTime;

public class Session {

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String coverImage;

    private SesstionStatus status;

    private CourseType courseType;

    private int maxStudent;

}
