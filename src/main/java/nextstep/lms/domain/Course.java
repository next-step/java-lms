package nextstep.lms.domain;

import nextstep.lms.UnAuthorizedException;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

public class Course {
    private static final AtomicLong idGenerator = new AtomicLong(1);

    private Long id;

    private String title;

    private LmsUser creator;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Course() {
    }

    private Course(Long id, String title, LmsUser creator, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.creator = creator;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private static void valiateCreatorAuthorization(LmsUser creator) {
        if (creator.getRole() != LmsUserRole.ADMIN) {
            throw new UnAuthorizedException("과정 생성 권한이 없는 유저입니다.");
        }
    }

    public static Course create(String title, LmsUser creator) {
        valiateCreatorAuthorization(creator);
        return new Course(idGenerator.getAndIncrement(), title, creator, LocalDateTime.now(), null);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
