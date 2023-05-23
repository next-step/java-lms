package nextstep.courses.domain;

import nextstep.common.domain.Image;

import java.util.Date;
import java.util.List;

public class Session {
    private SessionId sessionId;
    private Date startDate;
    private Date endDate;
    private Image coverImage;
    private Long price;
    private SessionStatus status;
    private Long maxStudentCount;
    private List<Enrolment> enrolments;


}
