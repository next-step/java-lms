package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Session {

    private final Long id;
    private final String title;
    private final int generation;
    private final DateTime dateTime;
    private final SessionType type;
    private final Register register;

    public Session(Long id, String title, int generation, DateTime dateTime, SessionType type, Register register) {
        this.id = id;
        this.title = title;
        this.generation = generation;
        this.dateTime = dateTime;
        this.type = type;
        this.register = register;
    }

    public Session(Long id, String title, int generation, DateTime dateTime, SessionType type, SessionStatus status, int maxRegisterCount) {
        this(id, title, generation, dateTime, type, new Register(status, maxRegisterCount));
    }

    public Session(Long id, String title, int generation, LocalDateTime startAt, LocalDateTime endAt, SessionType type, SessionStatus status, int maxRegisterCount) {
        this(id, title, generation, new DateTime(startAt, endAt), type, status, maxRegisterCount);
    }

    public Register register() {
        return this.register;
    }

    int currRegisterCount() {
        return this.register.students().size();
    }
}
