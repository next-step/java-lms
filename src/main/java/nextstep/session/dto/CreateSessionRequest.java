package nextstep.session.dto;

import java.time.LocalDate;
import nextstep.image.domain.Image;
import nextstep.image.domain.ImageCapacity;
import nextstep.image.domain.ImageCapacityType;
import nextstep.image.domain.ImageSize;
import nextstep.image.domain.ImageType;
import nextstep.session.domain.Enrollment;
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
}
