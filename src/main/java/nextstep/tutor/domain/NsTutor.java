package nextstep.tutor.domain;

import nextstep.tutor.exception.TutorException;

import java.time.LocalDateTime;
import java.util.Objects;

public class NsTutor {

    private Long id;

    private String tutorId;

    private String password;

    private String name;

    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public NsTutor() {
    }

    public NsTutor(Long id, String tutorId, String password, String name, String email) {
        this(id, tutorId, password, name, email, LocalDateTime.now(), null);
    }

    public NsTutor(Long id, String tutorId, String password, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.tutorId = tutorId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void isSameTutor(Long tutorId){
        if (!Objects.equals(tutorId, this.id)) {
            throw new TutorException("강사가 일치 하지 않습니다.");
        }
    }

}
