package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.*;
import java.util.stream.Collectors;

public class Students {

    private Set<Student> userList;

    public Students(NsUser... users) {
        this(getStudents(users));
    }

    private static List<Student> getStudents(NsUser... users) {
        return Arrays.stream(users).map(Student::new).collect(Collectors.toList());
    }

    public Students(Student... student) {
        this.userList = new HashSet<>(List.of(student));
    }

    public Students(Set<NsUser> userList) {
        this(userList.stream().map(Student::new).collect(Collectors.toList()));
    }

    public Students(List<Student> students) {
        this.userList = new HashSet<>(students);
    }

    public Students() {
        this.userList = new HashSet<>();
    }

    public Students registerSessionStudent(NsUser nsUser, SessionType sessionType) {
        this.userList.add(new Student(nsUser));
        if (sessionType.isMaxCapacity(this)) {
            throw new MaxStudentsExceedException("수강인원을 초과하여 신청할 수 없습니다.");
        }
        return this;
    }

    private boolean containsNsUser(NsUser nsUser) {
        return containsNsUser(new Student(nsUser));
    }

    private boolean containsNsUser(Student student) {
        return this.userList.contains(student);
    }


    public List<Student> excludeNsUser(Students students) {
        return this.userList.stream()
                .filter(it -> !students.containsNsUser(it))
                .collect(Collectors.toList());
    }

    public long size(){
        return this.userList.stream()
                .filter(Student::isWaitingStudent)
                .count();
    }

    public Students acceptStudent(Student student) {
        this.userList.stream()
                .filter(student::equals)
                .findFirst()
                .ifPresent(Student::acceptSession);
        return this;
    }

    public Students cancelStudent(Student student) {
        this.userList.removeIf(student::equals);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Students students = (Students) o;
        return Objects.equals(userList, students.userList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userList);
    }
}
