package nextstep.courses.domain;

public class Session {

    private Long id;
    private String title;
    private int generation;
    private DateTime dateTime;
    private SessionType type;
    private Register register;

    public Session() {
    }

    public Session(Long id, String title, int generation, DateTime dateTime, SessionType type, Register register) {
        this.id = id;
        this.title = title;
        this.generation = generation;
        this.dateTime = dateTime;
        this.type = type;
        this.register = register;
    }
}
