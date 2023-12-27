package nextstep.session.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Admissions {

    private final List<Admission> admissions = new ArrayList<>();

    public Admissions() {
    }

    public Admissions(List<Admission> admissions) {
        if (admissions != null) {
            this.admissions.addAll(admissions);
        }
    }

    public boolean isAdmiss(NsUser student, Session session) {
        return admissions.stream()
                .anyMatch(admission ->
                        admission.getSessionId().equals(session.getId()) && admission.getStudentId().equals(student.getId()));
    }
}
