package nextstep.courses.domain;

public class Student {

    private Long id;
    private String studentId;
    private String major;
    private String name;

    public Student() {
    }

    public Student(Long id, String studentId, String major, String name) {
        this.id = id;
        this.studentId = studentId;
        this.major = major;
        this.name = name;
    }
}
