package nextstep.lms.domain;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

public class LmsUser {
    private static final AtomicLong idGenerator = new AtomicLong(1);

    private Long id;

    private String userId;

    private String password;

    private String name;
    private LmsUserRole role;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LmsUser() {
    }

    private LmsUser(String userId, String password, String name, LmsUserRole role) {
        this(idGenerator.getAndIncrement(), userId, password, name, role, LocalDateTime.now(), null);
    }

    private LmsUser(Long id, String userId, String password, String name, LmsUserRole role, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static LmsUser createAdmin(String userId, String password, String name) {
        return new LmsUser(userId, password, name, LmsUserRole.ADMIN);
    }

    public static LmsUser createUser(String userId, String password, String name) {
        return new LmsUser(userId, password, name, LmsUserRole.NORMAL);
    }

    public boolean isAdmin() {
        return role == LmsUserRole.ADMIN;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public LmsUserRole getRole() {
        return role;
    }
}
