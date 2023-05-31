package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class SessionRegistrationStatus {
    private SessionStatus sessionStatus;
    private int maxStudents;
    private List<NsUser> students = new ArrayList<>();

    public SessionRegistrationStatus(SessionStatus sessionStatus, int maxStudents, List<NsUser> students){
        validateMaxStudents(maxStudents, students);
        this.sessionStatus = sessionStatus;
        this.maxStudents = maxStudents;
        this.students = students;
    }

    public void addStudents(List<NsUser> students){
        validateSessionStatus();
        validateMaxStudents(maxStudents, students);
        maxStudents++;
        students.addAll(students);
    }

    private void validateSessionStatus() {
        if(!canRegisteringStatus()){
            throw new IllegalArgumentException("수강신청이 불가한 상태입니다.");
        }
    }

    private void validateMaxStudents(int maxStudents, List<NsUser> students) {
        if(maxStudents < students.size()){
            throw new IllegalArgumentException("최대 수강인원을 넘길 수 없습니다.");
        }
    }

    public SessionRegistrationStatus(SessionStatus sessionStatus){
        this.sessionStatus = sessionStatus;
    }

    public SessionRegistrationStatus(int maxStudents){
        this.maxStudents = maxStudents;
    }

    public boolean canRegisteringStatus() {
        return sessionStatus.getCanRegistering();
    }

    public boolean overMaxStudents(int numberOfStudents) {
        return numberOfStudents >= maxStudents;
    }
}
