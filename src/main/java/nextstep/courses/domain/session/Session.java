package nextstep.courses.domain.session;

import nextstep.courses.domain.participant.SessionParticipants;
import nextstep.courses.domain.participant.SessionUserEnrolment;
import nextstep.courses.exception.NotRecruitingException;
import nextstep.courses.type.RecruitmentStatus;

import java.time.LocalDateTime;

public class Session {

    private Long id;
    private String title;
    private SessionPeriod sessionPeriod;
    private RecruitmentStatus recruitmentStatus;
    private Enrolment enrolment;
    private Images images;
    private Long courseId;

    public Session(Long id, String title, SessionPeriod sessionPeriod, Enrolment enrolment, RecruitmentStatus recruitmentStatus, Long courseId) {
        this(id, title, sessionPeriod, enrolment, recruitmentStatus, null, courseId);
    }

    public Session(String title, SessionPeriod sessionPeriod, Enrolment enrolment, RecruitmentStatus recruitmentStatus, Images images, Long courseId) {
        this(null, title, sessionPeriod, enrolment, recruitmentStatus, images, courseId);
    }

    public Session(Long id, String title, SessionPeriod sessionPeriod, Enrolment enrolment, RecruitmentStatus recruitmentStatus, Images images, Long courseId) {
        this.id = id;
        this.title = title;
        this.sessionPeriod = sessionPeriod;
        this.enrolment = enrolment;
        this.recruitmentStatus = recruitmentStatus;
        this.images = images;
        this.courseId = courseId;
    }

    public String title() {
        return this.title;
    }

    public boolean isFree() {
        return enrolment.isFree();
    }

    public void addParticipant(int money, SessionUserEnrolment nsUser) {
        validateRecruiting();
        enrolment.addParticipant(money, nsUser);
    }

    public void validateRecruiting() {
        if (recruitmentStatus.isNotRecruiting()) {
            throw new NotRecruitingException();
        }
    }

    public int nowParticipants() {
        return enrolment.nowParticipants();
    }

    public LocalDateTime startDateTime() {
        return sessionPeriod.startDate();
    }

    public LocalDateTime endDateTime() {
        return sessionPeriod.startDate();
    }

    public String status() {
        return enrolment.status();
    }

    public int price() {
        return enrolment.price();
    }

    public Long courseId() {
        return courseId;
    }

    public int maxParticipants() {
        return enrolment.maxParticipants();
    }

    public Images images() {
        return images;
    }

    public void mappadByImage(Images images) {
        this.images = images;
    }

    public String recruitmentStatus() {
        return recruitmentStatus.toString();
    }

    public Long id() {
        return id;
    }

    public void mappaedBySessionParticipants(SessionParticipants sessionParticipants) {
        this.enrolment.mappaedBySessionParticipants(sessionParticipants);
    }
}
