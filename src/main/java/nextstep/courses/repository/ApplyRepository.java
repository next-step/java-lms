package nextstep.courses.repository;

import nextstep.courses.domain.participant.Participant;
import nextstep.courses.domain.participant.ParticipantState;
import nextstep.users.domain.NsUser;

import java.util.List;

public interface ApplyRepository {
    int save(Long sessionId, NsUser nsUser, ParticipantState state);

    List<Participant> findBySessionId(Long sessionId);

    int updateState(Long id, ParticipantState participantState);


}
