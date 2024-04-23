package nextstep.courses.entity;

import java.time.LocalDateTime;
import nextstep.courses.domain.cover.Image;
import nextstep.courses.domain.session.Session;

public class SessionEntity extends BaseEntity {

    private Long id;

    private String sessionName;

    private int registrationCount;

    private int maxRegistrationCount;

    private int tuitionFee;

    private Long imageId;

    private String sessionStatus;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    public static SessionEntity from(Session session){
        return new SessionEntity(session.getId(),
            session.getSessionName(),
            session.getRegistrationCount(),
            session.getMaxRegistrationCount(),
            session.getTuitionFee(),
            null,
            session.getSessionStatus(),
            session.getValidityPeriod().getStartDate(),
            session.getValidityPeriod().getEndDate(),
            session.getCreatedAt(),
            session.getUpdatedAt());
    }

    public static SessionEntity from(Session session, Image image){
        return new SessionEntity(session.getId(),
            session.getSessionName(),
            session.getRegistrationCount(),
            session.getMaxRegistrationCount(),
            session.getTuitionFee(),
            image.getId(),
            session.getSessionStatus(),
            session.getValidityPeriod().getStartDate(),
            session.getValidityPeriod().getEndDate(),
            session.getCreatedAt(),
            session.getUpdatedAt());
    }

    public SessionEntity(Long id, String sessionName,
        int registrationCount,
        int maxRegistrationCount,
        int tuitionFee, Long imageId, String sessionStatus, LocalDateTime startDate,
        LocalDateTime endDate, LocalDateTime createdAt, LocalDateTime updateAt) {
        this.id = id;
        this.sessionName = sessionName;
        this.registrationCount = registrationCount;
        this.maxRegistrationCount = maxRegistrationCount;
        this.tuitionFee = tuitionFee;
        this.imageId = imageId;
        this.sessionStatus = sessionStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = createdAt;
        this.updatedAt = updateAt;
    }

    public boolean isPaidType(){
        return tuitionFee != 0;
    }

    public boolean isFreeType(){
        return tuitionFee == 0;
    }

    public Long getId() {
        return id;
    }

    public String getSessionName() {
        return sessionName;
    }

    public int getRegistrationCount() {
        return registrationCount;
    }

    public int getMaxRegistrationCount() {
        return maxRegistrationCount;
    }

    public int getTuitionFee() {
        return tuitionFee;
    }

    public Long getImageId() {
        return imageId;
    }

    public String getSessionStatus() {
        return sessionStatus;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
}
