package nextstep.session.domain;

import nextstep.users.domain.NsUser;

public class Tutor {

    private final String tutorId;

    public Tutor(NsUser tutor) {
        this(tutor.getUserId());
    }

    public Tutor(String tutorId) {
        this.tutorId = tutorId;
    }

    public String getTutorId() {
        return tutorId;
    }
}
