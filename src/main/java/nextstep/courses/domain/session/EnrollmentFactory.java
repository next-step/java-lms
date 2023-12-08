package nextstep.courses.domain.session;

import nextstep.courses.domain.attendee.Attendee;
import nextstep.courses.domain.attendee.FreeAttendees;
import nextstep.courses.domain.attendee.PaidAttendees;

import java.util.List;

public class EnrollmentFactory {

    private EnrollmentFactory() {}

    public static Enrollment create(SessionType type,
                                    List<Attendee> attendees,
                                    Long amount,
                                    int maxCapacity) {
        if (type.isFree()) {
            return createFree(attendees);
        }
        return createPaid(attendees, amount, maxCapacity);
    }

    private static PaidEnrollment createPaid(List<Attendee> attendees,
                                             Long amount,
                                             int maxCapacity) {
        PaidAttendees paidAttendees = new PaidAttendees(attendees, maxCapacity);
        Price price = new Price(amount);
        return new PaidEnrollment(paidAttendees, price);
    }

    private static FreeEnrollment createFree(List<Attendee> attendees) {
        FreeAttendees freeAttendees = new FreeAttendees(attendees);
        return new FreeEnrollment(freeAttendees);
    }
}
