package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class SessionAttendees {
    private static final int MINIMUM_NUMBER_OF_ATTENDEES = 1;

    private final int maxNumberOfAttendees;
    private final Set<NsUser> attendees;

    public SessionAttendees(int maxNumberOfAttendees) {
        this(maxNumberOfAttendees, new HashSet<>());
    }

    public SessionAttendees(int maxNumberOfAttendees, Set<NsUser> attendees) {
        if(maxNumberOfAttendees < MINIMUM_NUMBER_OF_ATTENDEES) {
            throw new IllegalArgumentException("최대 수강자 수는 " + MINIMUM_NUMBER_OF_ATTENDEES + "명 이상이여야 합니다: " + maxNumberOfAttendees);
        }
        if(maxNumberOfAttendees < attendees.size()) {
            throw new IllegalArgumentException("수강자 수가 최대 수강자 수(" + maxNumberOfAttendees + ")를 초과했습니다: " + attendees.size());
        }

        this.maxNumberOfAttendees = maxNumberOfAttendees;
        this.attendees = attendees;
    }

    public void add(NsUser user) {
        validateDuplicateApply(user);
        validateMaxNumberOfAttendees();

        attendees.add(user);
    }

    public int maxNumberOfAttendees() {
        return maxNumberOfAttendees;
    }

    private void validateMaxNumberOfAttendees() {
        if(attendees.size() >= maxNumberOfAttendees) {
            throw new IllegalArgumentException("수강 가능 인원이 다 찼습니다: " + maxNumberOfAttendees);
        }
    }

    private void validateDuplicateApply(NsUser user) {
        if(attendees.contains(user)) {
            throw new IllegalArgumentException("이미 등록된 수강생입니다: " + user.getName());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SessionAttendees)) return false;
        SessionAttendees that = (SessionAttendees) o;
        return maxNumberOfAttendees == that.maxNumberOfAttendees && Objects.equals(attendees, that.attendees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxNumberOfAttendees, attendees);
    }
}
