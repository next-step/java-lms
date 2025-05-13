package nextstep.courses.dto;

import lombok.Builder;
import lombok.Getter;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionId;
import nextstep.courses.domain.session.SessionProgressStatus;
import nextstep.courses.domain.session.SessionRecruitmentStatus;
import nextstep.courses.domain.session.SessionType;
import nextstep.courses.domain.session.info.SessionInfo;
import nextstep.courses.domain.session.info.basic.SessionBasicInfo;
import nextstep.courses.domain.session.info.detail.SessionDetailInfo;
import nextstep.courses.domain.session.info.detail.SessionPeriod;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class SessionDto {
    private final Long id;
    private final Long courseId;
    private final String title;
    private final SessionType sessionType;
    private final SessionProgressStatus progressStatus;
    private final SessionRecruitmentStatus recruitmentStatus;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final int maximumEnrollment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static SessionDto of(Session session) {
        SessionId sessionId = session.getId();
        SessionInfo sessionInfo = session.getInfo();

        SessionBasicInfo sessionBasicInfo = sessionInfo.getBasicInfo();
        SessionDetailInfo sessionDetailInfo = sessionInfo.getDetailInfo();
        SessionPeriod sessionPeriod = sessionDetailInfo.getPeriod();

        return SessionDto.builder()
                .id(sessionId.getId())
                .courseId(sessionId.getCourseId())
                .title(sessionBasicInfo.getTitle())
                .sessionType(sessionDetailInfo.getType())
                .progressStatus(sessionInfo.getProgressStatus())
                .recruitmentStatus(sessionInfo.getRecruitmentStatus())
                .startDate(sessionPeriod.getStartDate())
                .endDate(sessionPeriod.getEndDate())
                .maximumEnrollment(sessionInfo.getMaxEnrollment())
                .build();
    }

    public void setTimeStampForUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
