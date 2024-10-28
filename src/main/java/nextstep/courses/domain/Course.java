package nextstep.courses.domain;

import nextstep.DateDomain;
import nextstep.session.domain.Session;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Course {
    private Long id;

    private String title;

    private Long term;

    private List<Session> sessions = new ArrayList<>();

    private Long creatorId;

    private DateDomain dateDomain;


    public Course(String title, Long term, Long creatorId) {
        this.title = title;
        this.term = term;
        this.creatorId = creatorId;
        this.dateDomain = new DateDomain();
    }

    public Course(Long id, String title, Long term, Long creatorId) {
        this.id = id;
        this.title = title;
        this.term = term;
        this.creatorId = creatorId;
        this.dateDomain = new DateDomain();
    }

    public Course(Long id, String title, Long term, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.term = term;
        this.creatorId = creatorId;
        this.dateDomain = new DateDomain(createdAt, updatedAt);
    }

    public String getTitle() {
        return title;
    }

    public void addSession(Session lecture) {
        this.sessions.add(lecture);
    }

    public List<Session> getLectures() {
        return sessions;
    }

    public Long getId() {
        return id;
    }

    public Long getTerm() {
        return term;
    }

    public DateDomain getDateDomain() {
        return dateDomain;
    }

    public Long getCreatorId() {
        return creatorId;
    }

}
