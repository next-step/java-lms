package nextstep.courses.domain;

import nextstep.common.Image;

import java.util.Date;
import java.util.List;

public class Session {
    private SessionId id;
    private Date startDate;
    private Date endDate;
    private Image coverImage;
    private Long price;
    private SessionStatus status;
    private Long maxStudentCount;
    private List<Enrolment> enrolments;


}
