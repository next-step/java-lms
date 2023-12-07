package nextstep.courses.domain.course.session;

import nextstep.courses.domain.course.image.Image;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class Session {
    private Long id;

    private Image image;

    private Duration duration;

    private Type type;

    private Long amount;

    private int quota;

    private Applicants applicants;

    private Status status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public enum Type {
        FREE("무료"),
        CHARGE("유료");

        private final String description;

        Type(String description) {
            this.description = description;
        }
    }

    public enum Status {
        READY("준비중"),
        RECRUIT("모집중"),
        END("종료");

        private final String description;

        Status(String description) {
            this.description = description;
        }
    }

    public Session() {
    }

    public Session(Image image, Duration duration,
                   Type type, Long amount, int quota) {
        this(0L, image, duration, type, amount, quota, new Applicants(),
                Status.READY, LocalDateTime.now(), null);
    }

    public Session(Long id, Image image, Duration duration,
                   Type type, Long amount, int quota, Applicants applicants,
                   Status status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        if (image == null) {
            throw new IllegalArgumentException("이미지를 추가해야 합니다");
        }

        if (duration == null) {
            throw new IllegalArgumentException("기간을 추가해야 합니다.");
        }

        this.id = id;
        this.image = image;
        this.duration = duration;
        this.type = type;
        this.amount = amount;
        this.quota = quota;
        this.applicants = applicants;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public boolean isSame(Long sessionId) {
        return Objects.equals(this.id, sessionId);
    }

    public Long getId() {
        return this.id;
    }

    public int applyCount() {
        return this.applicants.size();
    }

    public void apply(NsUser loginUser, Payment payment) {
        checkStatusOnRecruit();

        if (typeCharged()) {
            checkApplySizeIsValid();
            checkPaymentIsPaid(loginUser, payment);
        }

        this.applicants.add(loginUser);
    }

    private boolean typeCharged() {
        return this.type == Type.CHARGE;
    }

    private void checkStatusOnRecruit() {
        if (this.status != Status.RECRUIT) {
            throw new IllegalArgumentException("강의 신청은 모집 중일 때만 가능 합니다.");
        }
    }

    private void checkApplySizeIsValid() {
        if (applyCount() + 1 > quota) {
            throw new IllegalArgumentException("수강 인원은 정원을 초과할 수 없습니다.");
        }
    }

    private void checkPaymentIsPaid(NsUser loginUser, Payment payment) {
        if (payment == null || !payment.isPaid(loginUser, this)) {
            throw new IllegalArgumentException("결제를 진행해 주세요.");
        }
    }
}
