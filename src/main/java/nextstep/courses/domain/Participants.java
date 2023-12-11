package nextstep.courses.domain;

import nextstep.courses.exception.ParticipantsException;
import nextstep.users.domain.NsUser;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Participants {
    private final Set<NsUser> values;

    public Participants(Set<NsUser> values) {
        this.values = values;
    }

    public Participants(List<NsUser> values) {
        this.values = Set.copyOf(values);
    }

    public int size() {
        return values.size();
    }

    public Participants add(NsUser user) {
        isDuplication(user);
        values.add(user);
        return new Participants(values);
    }

    private void isDuplication(NsUser user) {
        if (values.contains(user)) {
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
