package nextstep.courses.domain;

import nextstep.common.domain.BaseControlField;
import nextstep.courses.exception.SessionRegistrationException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session extends BaseControlField {

    private int id;
    private String title;
    private SessionPeriod sessionPeriod;
    private ChargeType chargeType;
    private ImageUrl coverImageUrl;
    private SessionStatusType statusType;
    private SessionStudents sessionStudents;

    Session(int id, String title, SessionPeriod sessionPeriod, ChargeType chargeType, ImageUrl coverImageUrl, SessionStudents sessionStudents, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(id, title, sessionPeriod, chargeType, coverImageUrl, SessionStatusType.PREPARING, sessionStudents, creatorId, createdAt, updatedAt);
    }
    public Session(int id, String title, SessionPeriod sessionPeriod, ChargeType chargeType, ImageUrl coverImageUrl
            , SessionStatusType statusType, SessionStudents sessionStudents, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(creatorId, createdAt, updatedAt);
        if (sessionPeriod == null) {
            throw new IllegalArgumentException("강의 기간을 설정해주세요.");
        }
        if (chargeType == null) {
            throw new IllegalArgumentException("과금 유형을 선택해주세요.");
        }
        this.id = id;
        this.title = title;
        this.sessionPeriod = sessionPeriod;
        this.chargeType = chargeType;
        this.coverImageUrl = coverImageUrl;
        this.statusType = statusType;
        this.sessionStudents = sessionStudents;
    }

    public boolean register(NsUser nsUser) {
        if (!statusType.canRegister()) {
            throw new SessionRegistrationException("모집중인 강의가 아닙니다.");
        }
        return sessionStudents.addStudent(nsUser);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public SessionPeriod getSessionPeriod() {
        return sessionPeriod;
    }

    public ChargeType getChargeType() {
        return chargeType;
    }

    public String getCoverImageUrl() {
        String imageUrl = null;

        if (coverImageUrl != null) {
            imageUrl = coverImageUrl.getImageUrl();
        }
        return imageUrl;
    }

    public SessionStatusType getStatusType() {
        return statusType;
    }

    public SessionStudents getSessionStudents() {
        return sessionStudents;
    }
}
