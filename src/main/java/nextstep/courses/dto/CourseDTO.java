package nextstep.courses.dto;

import nextstep.courses.domain.Sessions;

public class CourseDTO {
    private Long id;

    private String title;

    private Long creatorId;

    private Sessions sessions;

    public CourseDTO(Long id, String title, Long creatorId, Sessions sessions) {
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.sessions = sessions;
    }


    public Long getId() {
        return id;
    }
}
