package nextstep.courses.domain;

import static nextstep.courses.domain.CourseBuilder.aCourse;
import static nextstep.courses.domain.CoverImageBuilder.aCoverImage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;

public class SessionBuilder extends AuditInfo {
    private Long id = 0L;
    private Course course = aCourse().build();
    private SessionPaymentType sessionPaymentType = SessionPaymentType.FREE;
    private long amountOfPrice = 0l;
    private int limitOfUsers = 0;
    private List<NsUser> userList = new ArrayList<>();
    private Duration duration = new Duration(LocalDateTime.now(), LocalDateTime.now().plusMonths(1));
    private SessionStatus sessionStatus = SessionStatus.READY;
    private CoverImage coverImage = aCoverImage().build();

    private SessionBuilder(){
        super(LocalDateTime.now(), null);
    }

    private SessionBuilder(final SessionBuilder sessionBuilder){
        this();
        this.id = sessionBuilder.id;
        this.course = sessionBuilder.course;
        this.sessionPaymentType = sessionBuilder.sessionPaymentType;
        this.amountOfPrice = sessionBuilder.amountOfPrice;
        this.limitOfUsers = sessionBuilder.limitOfUsers;
        this.userList = sessionBuilder.userList;
        this.duration = sessionBuilder.duration;
        this.sessionStatus = sessionBuilder.sessionStatus;
        this.coverImage = sessionBuilder.coverImage;
    }

    public static SessionBuilder aSession(){
        return new SessionBuilder();
    }

    public SessionBuilder withId(final Long id){
        this.id = id;
        return new SessionBuilder(this);
    }

    public SessionBuilder withCourse(final Course course){
        this.course = course;
        return new SessionBuilder(this);
    }

    public SessionBuilder withSessionPaymentType(final SessionPaymentType sessionPaymentType){
        this.sessionPaymentType = sessionPaymentType;
        return new SessionBuilder(this);
    }

    public SessionBuilder withAmountOfPrice(final long amountOfPrice){
        this.amountOfPrice = amountOfPrice;
        return new SessionBuilder(this);
    }

    public SessionBuilder withLimitOfUsers(final int limitOfUsers){
        this.limitOfUsers = limitOfUsers;
        return new SessionBuilder(this);
    }

    public SessionBuilder withUserList(final List<NsUser> userList){
        this.userList = userList;
        return new SessionBuilder(this);
    }

    public SessionBuilder withDuration(final Duration duration){
        this.duration = duration;
        return new SessionBuilder(this);
    }

    public SessionBuilder withSessionStatus(final SessionStatus sessionStatus){
        this.sessionStatus = sessionStatus;
        return new SessionBuilder(this);
    }

    public SessionBuilder withCoverImage(final CoverImage coverImage){
        this.coverImage = coverImage;
        return new SessionBuilder(this);
    }

    public SessionBuilder withCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return new SessionBuilder(this);
    }

    public SessionBuilder withUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return new SessionBuilder(this);
    }

    public Session build(){
        return new Session(id, course, amountOfPrice, sessionPaymentType,
                new NsUsers(userList), limitOfUsers, duration, sessionStatus, coverImage, createdAt, updatedAt);
    }
}
