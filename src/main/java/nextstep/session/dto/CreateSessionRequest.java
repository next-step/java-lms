package nextstep.session.dto;

import java.time.LocalDate;
import nextstep.image.domain.Image;
import nextstep.image.domain.ImageCapacity;
import nextstep.image.domain.ImageCapacityType;
import nextstep.image.domain.ImageSize;
import nextstep.image.domain.ImageType;
import nextstep.session.domain.Session;
import nextstep.session.domain.SessionPeriod;
import nextstep.session.domain.SessionStatus;
import nextstep.session.domain.SessionType;

public class CreateSessionRequest {

    private Long courseId;

    private LocalDate startDate;

    private LocalDate endDate;

    private long value;

    private ImageCapacityType imageCapacityType;

    private ImageType imageType;

    private long width;
    private long height;

    private SessionType type;

    private SessionStatus status;

    private long price;

    public CreateSessionRequest(Long courseId, LocalDate startDate, LocalDate endDate, long value,
                                ImageCapacityType imageCapacityType, ImageType imageType, long width, long height,
                                SessionType type, SessionStatus status, long price) {
        this.courseId = courseId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.value = value;
        this.imageCapacityType = imageCapacityType;
        this.imageType = imageType;
        this.width = width;
        this.height = height;
        this.type = type;
        this.status = status;
        this.price = price;
    }

    public Session toEntity() {
        SessionPeriod sessionPeriod = new SessionPeriod(startDate, endDate);
        ImageCapacity imageCapacity = new ImageCapacity(value, imageCapacityType);
        ImageSize imageSize = new ImageSize(width, height);
        return new Session(
                sessionPeriod,
                new Image(imageCapacity, imageType, imageSize),
                type,
                status,
                price
        );
    }

    public Long getCourseId() {
        return courseId;
    }
}
