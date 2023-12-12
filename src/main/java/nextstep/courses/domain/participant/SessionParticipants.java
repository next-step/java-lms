package nextstep.courses.domain.session;

import nextstep.courses.domain.participant.SessionUserEnrolment;

import java.util.List;

public class SessionParticipants {

    private final List<SessionUserEnrolment> participants;

    public SessionParticipants(List<SessionUserEnrolment> participants) {
        this.participants = participants;
    }

    public void add(SessionUserEnrolment user) {
        validate(user);
        participants.add(user);
    }

    private void validate(SessionUserEnrolment user) {
        if (participants.contains(user)) {
            throw new IllegalArgumentException("이미 등록된 사용자입니다.");
        }
    }

    public int count() {
        return participants.size();
    }
}
