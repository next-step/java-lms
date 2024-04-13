package nextstep.courses.domain;

import nextstep.courses.domain.enrollment.*;
import nextstep.courses.domain.image.SessionCoverImage;
import nextstep.courses.exception.SessionTypeInvalidException;

public class SessionFactory {

    public static Session get(String type, Long courseId, SessionCoverImage coverImage, SessionStatus status, int capacity, Long fee) {
        if (SessionType.FREE.contains(type)) {
            return new Session(courseId, SessionType.FREE, coverImage, new FreeSessionEnrollment(status));
        }

        if (SessionType.PAID.contains(type)) {
            return new Session(courseId, SessionType.PAID, coverImage, new PaidSessionEnrollment(status, capacity, fee));
        }

        throw new SessionTypeInvalidException(type);
    }


}
