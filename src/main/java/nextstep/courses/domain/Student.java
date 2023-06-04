package nextstep.courses.domain;

public class Student {
    private Long id;
    private String name;
    private Email eMail;

    public Student(Long id, String name, Email eMail) {
        this.id = id;
        this.name = name;
        this.eMail = eMail;
    }

    public Long getId() {
        return id;
    }
}
