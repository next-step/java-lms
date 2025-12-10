package nextstep.courses.domain.session;

import nextstep.courses.SessionUnregistrableException;
import nextstep.courses.domain.*;
import nextstep.courses.domain.image.Image;
import nextstep.courses.domain.session.type.Free;
import nextstep.courses.domain.session.type.Paid;
import nextstep.courses.domain.session.type.SessionType;

import java.time.LocalDate;

public class Session {
    private final BaseEntity baseEntity;
    private final Image image;
    private SessionStatus status;
    private final SessionType type;

    public Session(Long id, String title, Image image, SessionStatus status, SessionType type, LocalDate startDate, LocalDate endDate) {
        this.baseEntity = new BaseEntity(id, title, new Period(startDate, endDate));
        this.image = image;
        this.status = status;
        this.type = type;
    }

    public void openRecruiting() {
        this.status = SessionStatus.RECRUITING;
    }

    public boolean isRecruiting() {
        return this.status == SessionStatus.RECRUITING;
    }

    public boolean canEnroll(EnrollmentCondition request) {
        return this.type.canEnroll(request);
    }

    public String currentStatusToHumanReadable() {
        return this.status.name();
    }

    public String typeToHumanReadable() {
        return this.type.toHumanReadableTypeName();
    }

    public static Session createFreeSession(String title, Image image) {
        return new Session(null, title, image, SessionStatus.PREPARING, new Free(), LocalDate.now(), LocalDate.MAX);
    }

    public static Session createPaidSession(long id, String title, Image image, long price) {
        return new Session(null, title, image, SessionStatus.PREPARING, new Paid(id, price), LocalDate.now(), LocalDate.MAX);
    }
}
