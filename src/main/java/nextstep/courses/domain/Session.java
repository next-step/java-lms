package nextstep.courses.domain;

import nextstep.common.CommunicationTerm;
import nextstep.common.domain.Image;

import java.util.Date;
import java.util.List;

@CommunicationTerm("강의")
public class Session {
    private SessionId sessionId;
    private Date startDate;
    private Date endDate;
    private Image coverImage;
    private Long price;
    private SessionStatus status;
    private Long maxStudentCount;
    private List<Enrolment> enrolments;

    public Session(Long price, Long maxStudentCount) {
        this.price = price;
        this.maxStudentCount = maxStudentCount;
    }
}
