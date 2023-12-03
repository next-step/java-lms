package nextstep.courses.domain.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import nextstep.courses.domain.field.CoverImage;
import nextstep.courses.domain.field.SessionStatus;
import nextstep.courses.domain.field.SessionType;

public class NsSessions {

    private List<NsSession> nsSessions = new ArrayList<>() {};

    public static NsSessions autoGenerateFree(NsCourse course) {
        NsSessions sessions = new NsSessions();
        for (long idx = 1; idx <= 10; idx++) {
            sessions.getNsSessions().add(NsSession.freeOf(course.getId(),
                                                          CoverImage.DEFAULT_IMAGE,
                                                          SessionType.FREE.getType(),
                                                          SessionStatus.random().getType(),
                                                          LocalDate.now().plusDays(idx),
                                                          LocalDate.now().plusDays(30 + idx)));
        }

        return sessions;
    }

    public static NsSessions autoGeneratePaid(NsCourse course) {
        NsSessions sessions = new NsSessions();
        for (long idx = 1; idx <= 10; idx++) {
            sessions.getNsSessions().add(NsSession.paidOf(course.getId(),
                                                          CoverImage.DEFAULT_IMAGE,
                                                          SessionType.PAID.getType(),
                                                          SessionStatus.random().getType(),
                                                          LocalDate.now().plusDays(idx),
                                                          LocalDate.now().plusDays(30 + idx)));
        }

        return sessions;
    }

    public List<NsSession> getNsSessions() {
        return nsSessions;
    }
}
