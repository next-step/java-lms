package nextstep.courses.domain;

public class SessionBuilder {

    private Long id;
    private String title;
    private int generation;
    private DateTime dateTime;
    private SessionType type;
    private Register register;

    public SessionBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public SessionBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public SessionBuilder withGeneration(int generation) {
        this.generation = generation;
        return this;
    }

    public SessionBuilder withDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public SessionBuilder withType(SessionType type) {
        this.type = type;
        return this;
    }

    public SessionBuilder withRegister(Register register) {
        this.register = register;
        return this;
    }

    public Session build() {
        return new Session(id, title, generation, dateTime, type, register);
    }
}
