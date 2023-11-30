package nextstep.courses.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import nextstep.courses.domain.field.CoverImage;
import nextstep.courses.domain.field.SessionStatus;
import nextstep.courses.domain.field.SessionType;

public class NsSession {

    private Long id;

    private Long courseId;

    private CoverImage coverImage;

    private SessionType sessionType;

    private SessionStatus sessionStatus;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public NsSession(Long id,
                     Long courseId,
                     CoverImage coverImage,
                     SessionType sessionType,
                     SessionStatus sessionStatus,
                     LocalDate startDate,
                     LocalDate endDate) {
        validateImage(coverImage);
        validateSessionDate(startDate, endDate);

        this.id = id;
        this.courseId = courseId;
        this.coverImage = coverImage;
        this.sessionType = sessionType;
        this.sessionStatus = sessionStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = null;
    }

    public static NsSession of(Long id,
                               Long courseId,
                               CoverImage coverImage,
                               String sessionType,
                               String sessionStatus,
                               LocalDate startDate,
                               LocalDate endDate) {
        return new NsSession(id,
                             courseId,
                             coverImage,
                             SessionType.getType(sessionType),
                             SessionStatus.getType(sessionStatus),
                             startDate,
                             endDate);
    }

    public NsSession(Long id,
                     Long courseId,
                     CoverImage coverImage,
                     SessionType sessionType,
                     SessionStatus sessionStatus,
                     LocalDate startDate,
                     LocalDate endDate,
                     LocalDateTime createdAt,
                     LocalDateTime updatedAt) {
        validateImage(coverImage);
        validateSessionDate(startDate, endDate);

        this.id = id;
        this.courseId = courseId;
        this.coverImage = coverImage;
        this.sessionType = sessionType;
        this.sessionStatus = sessionStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private void validateSessionDate(LocalDate startDate, LocalDate endDate) {
        if (checkNull(startDate) || checkNull(endDate)) {
            throw new IllegalArgumentException("시작일과 종료일을 입력해주세요");
        }

        checkPeriod(startDate, endDate);
    }

    private boolean checkNull(LocalDate date) {
        return date == null;
    }

    private void checkPeriod(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("시작일은 종료일보다 이후가 될 수 없습니다");
        }
    }

    private void validateImage(CoverImage coverImage) {
        if (coverImage == null) {
            throw new IllegalArgumentException("강의 커버 이미지를 넣어주세요");
        }
    }
}
