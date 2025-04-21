package nextstep.courses.domain;

public class Student {
    Long id;
    public Student() {
    }
    public Student(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public boolean contains(Long studentId) {
        return id.equals(studentId);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id != null && id.equals(student.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
