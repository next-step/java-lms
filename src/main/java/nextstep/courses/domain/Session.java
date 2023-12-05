package nextstep.courses.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Session {
    private Long id;

    private Image image;

    private LocalDate startDate;

    private LocalDate endDate;

    private Type type;

    private int quota;

    private int apply = 0;

    private Status status = Status.READY;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public enum Type {
        FREE("무료"),
        CHARGE("유료");

        Type(String description) {
            this.description = description;
        }

        private final String description;
    }

    public Session() {
    }

    public enum Status {
        READY("준비중"),
        RECRUIT("모집중"),
        END("종료");

        Status(String description) {
            this.description = description;
        }

        private final String description;
    }

    public Session(Image image, LocalDate startDate, LocalDate endDate, Type type, int quota) {
        this(0L, image, startDate, endDate, type, quota, LocalDateTime.now(), null);
    }

    public Session(Long id, Image image, LocalDate startDate,
                   LocalDate endDate, Type type, int quota,
                   LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.image = image;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.quota = quota;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
