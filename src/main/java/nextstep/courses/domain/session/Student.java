package nextstep.courses.domain.session;

public class Student {

    private Long id;

    private String loginId;

    private String password;

    private String name;

    private String email;

    public Student(Long id, String loginId, String password, String name, String email) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

}
