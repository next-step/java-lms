package nextstep.courses.domain;

import nextstep.courses.CannotEnrollSessionException;
import nextstep.courses.domain.vo.Slot;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Attendees {

    private Slot slot;
    private List<NsUser> attendees;

    public Attendees(int max) {
        this.slot = new Slot(max);
        this.attendees = new ArrayList<>();
    }

    public void add(NsUser user) {
        if (!slot.available()) {
            throw new CannotEnrollSessionException("수강신청 인원 제한 도달");
        }
        slot.plus();
        attendees.add(user);
    }
}
