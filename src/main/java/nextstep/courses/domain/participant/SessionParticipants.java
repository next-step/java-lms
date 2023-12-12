package nextstep.courses.domain.participant;

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
        if (validateAlreadyRegisterUser(user)) {
            throw new IllegalArgumentException("이미 등록된 사용자입니다.");
        }
    }

    private boolean validateAlreadyRegisterUser(SessionUserEnrolment user) {
        return participants.stream().filter(participant -> participant.nsUserId().equals(user.nsUserId())).findFirst().isPresent();
    }

    public int count() {
        return participants.size();
    }
}
