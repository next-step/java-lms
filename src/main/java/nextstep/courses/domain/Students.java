package nextstep.courses.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Students {

    private final Set<Student> students;
    private int maxCount = 10;

    public Students() {
        this(new HashSet<>());
    }

    public Students(Set<Student> students) {
        this.students = students;
    }

    public Students(int maxCount) {
        this.students = new HashSet<>();
        this.maxCount = maxCount;
    }

    public Students(Set<Student> students, int maxCount) {
        this.students = students;
        this.maxCount = maxCount;
    }


    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    public void add(Student student) {
        if(isFull()){
            throw new IllegalArgumentException("수강 신청 인원을 넘었습니다");
        }
        if(!student.isApproved()){
            throw new IllegalArgumentException("수강 가능한 인원이 아닙니다");
        }

        this.students.add(student);
    }

    public int size(){
        return students.size();
    }

    public int maxCount() { return maxCount; }

    public boolean isFull(){
        return maxCount <= students.size();
    }

}
