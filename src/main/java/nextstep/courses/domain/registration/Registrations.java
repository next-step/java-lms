package nextstep.courses.domain.registration;

import java.util.ArrayList;
import java.util.List;

public class Registrations {
    private final List<Registration> registrations;

    public Registrations() {
        this(new ArrayList<>());
    }

    public Registrations(Registration registration) {
      this.registrations = new ArrayList<>(List.of(registration));
    }

    public Registrations(List<Registration> registrations) {
        this.registrations = new ArrayList<>(registrations);
    }

    public Registrations add(Registration registration) {
        validateDuplicateRegistration(registration);
        List<Registration> newList = new ArrayList<>(registrations);
        newList.add(registration);
        return new Registrations(newList);
    }

    private void validateDuplicateRegistration(Registration registration) {
        if (isAlreadyRegistered(registration.getStudentId())) {
            throw new IllegalArgumentException("이미 수강신청한 학생입니다.");
        }
    }

    public boolean isAlreadyRegistered(long studentId) {
        return registrations.stream()
            .anyMatch(registration -> registration.getStudentId().equals(studentId));
    }

    public void validateCapacity(int maxCapacity) {
        if (count() > maxCapacity) {
            throw new IllegalStateException("최대 수강 인원을 초과할 수 없습니다.");
        }
    }

    public int count() {
        return registrations.size();
    }
}