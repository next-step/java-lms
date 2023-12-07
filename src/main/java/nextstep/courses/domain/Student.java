package nextstep.courses.domain;

public class Student {
    private Long id;
    private String name;

    public Student(String name) {
        this(0L, name);
    }

    public Student(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
