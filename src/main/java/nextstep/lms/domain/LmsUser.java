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

    public static LmsUser adminOf(String userId, String password, String name) {
        validateIfPresent(userId, password, name);
        return new LmsUser(userId, password, name, LmsUserRole.ADMIN);
    }

    public static LmsUser normalOf(String userId, String password, String name) {
        validateIfPresent(userId, password, name);
        return new LmsUser(userId, password, name, LmsUserRole.NORMAL);
    }

    private static void validateIfPresent(String userId, String password, String name) {
        if (Utils.isNullOrBlank(userId, password, name)) {
            throw new IllegalArgumentException("아이디 / 비밀번호 / 이름 은 필수값입니다.");
        }
    }

    public boolean isAdmin() {
        return this.role.isAdmin();
    }

    public boolean isNotAdmin() {
        return this.role.isNotAdmin();
    }

    public boolean isName(String name) {
        return this.name.equals(name);
    }

    public boolean isUserId(String userId) {
        return this.userId.equals(userId);
    }
}
