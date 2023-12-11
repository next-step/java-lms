package nextstep.courses.domain.participant;

import nextstep.courses.exception.ParticipantsException;
import nextstep.users.domain.NsUser;

import java.util.*;
import java.util.stream.Collectors;

public class Participants {
    private final Set<NsUser> values;

    private final Map<NsUser, ParticipantState> valueMap;

    public Participants(Set<NsUser> values) {
        this.values = values;
        this.valueMap = values.stream().collect(Collectors.toMap(nsUser -> nsUser, nsUser -> ParticipantState.COMPLETE));
    }

    public Participants(List<NsUser> values) {
        this.values = Set.copyOf(values);
        this.valueMap = values.stream().collect(Collectors.toMap(nsUser -> nsUser, nsUser -> ParticipantState.COMPLETE));
    }

    public Participants(Map<NsUser, ParticipantState> map) {
        this.values = new HashSet<>(map.keySet());
        this.valueMap = map;
    }

    public int size() {
        return valueMap.size();
    }

    public Participants add(NsUser user) {
        isDuplication(user);
        valueMap.put(user, ParticipantState.CHECKING);
        return new Participants(valueMap);
    }

    private void isDuplication(NsUser user) {
        if (valueMap.containsKey(user)) {
            throw new ParticipantsException("이미 수강 신청한 참여자 입니다.");
        }
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
    public String toString() {
        return "Participants{" +
                "values=" + values +
                '}';
    }
}
