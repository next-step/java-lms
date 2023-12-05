package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Session {
    private Long id;

    private Long paymentId;

    private Image image;

    private LocalDate startDate;

    private LocalDate endDate;

    private Type type;

    private Long amount;

    private int quota;

    private int applyCount = 0;

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

    public Session(Image image, LocalDate startDate, LocalDate endDate,
                   Type type, Long amount, int quota) {
        this(0L, image, startDate, endDate, type, amount, quota, LocalDateTime.now(), null);
    }

    public Session(Long id, Image image, LocalDate startDate,
                   LocalDate endDate, Type type, Long amount, int quota,
                   LocalDateTime createdAt, LocalDateTime updatedAt) {
        if(image == null) {
            throw new IllegalArgumentException("이미지를 추가해야 합니다");
        }

        this.id = id;
        this.image = image;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.amount = amount;
        this.quota = quota;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    void applySession(Payment payment) {
        if (this.status != Status.RECRUIT) {
            throw new IllegalArgumentException("강의 신청은 모집 중일 때만 가능합니다.");
        }

        if (this.type == Type.CHARGE) {
            if (this.applyCount + 1 == quota) {
                throw new IllegalArgumentException("수강 인원은 정원을 초과할 수 없습니다.");
            }

            if(!payment.isPaid(paymentId, amount)) {
                throw new IllegalArgumentException("결제를 진행해 주세요.");
            }
        }

        this.applyCount += 1;
    }
}
