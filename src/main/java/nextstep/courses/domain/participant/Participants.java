package nextstep.courses.domain.participant;

import nextstep.courses.exception.ParticipantsException;
import nextstep.users.domain.NsUser;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Participants {
    private final Set<Participant> values;


    public Participants(List<NsUser> values) {
        this.values = values.stream().map(item -> new Participant(item, ParticipantState.CHECKING)).collect(Collectors.toSet());
    }

    public Participants(Set<Participant> participants) {
        this.values = participants;
    }

    public int size() {
        return values.size();
    }

    public Participants add(NsUser user) {
        isDuplication(user);
        values.add(new Participant(user, ParticipantState.CHECKING));
        return new Participants(values);
    }

    private void isDuplication(NsUser user) {
        if (isEqualUser(user)) {
            throw new ParticipantsException("이미 수강 신청한 참여자 입니다.");
        }
    }

    private boolean isEqualUser(NsUser user) {
        return values.stream().anyMatch(item -> item.equalUser(user));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participants that = (Participants) o;
        return Objects.equals(values, that.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }


    @Override
    public String
    toString() {
        return "Participants{" +
                "values=" + values +
                '}';
    }
}
