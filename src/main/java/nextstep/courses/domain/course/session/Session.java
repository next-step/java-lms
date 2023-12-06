package nextstep.courses.domain.course.session;

import nextstep.courses.domain.course.image.Image;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Session {
    private Long id;

    private Image image;

    private LocalDate startDate;

    private LocalDate endDate;

    private Type type;

    private Long amount;

    private int quota;

    private List<NsUser> users = new ArrayList<>();

    private Status status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public boolean isSame(Long sessionId) {
        return Objects.equals(this.id, sessionId);
    }

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
        this(0L, image, startDate, endDate, type, amount, quota, new ArrayList<NsUser>(),
                Status.READY, LocalDateTime.now(), null);
    }

    public Session(Long id, Image image, LocalDate startDate, LocalDate endDate,
                   Type type, Long amount, int quota, List<NsUser> users,
                   Status status, LocalDateTime createdAt, LocalDateTime updatedAt) {
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
        this.users = users;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int applyCount() {
        return this.users.size();
    }

    void applySession(NsUser loginUser, Payment payment) {
        if (this.status != Status.RECRUIT) {
            throw new IllegalArgumentException("강의 신청은 모집 중일 때만 가능 합니다.");
        }

        if (this.type == Type.CHARGE) {
            if (applyCount() + 1 > quota) {
                throw new IllegalArgumentException("수강 인원은 정원을 초과할 수 없습니다.");
            }

            if (payment == null || !payment.isPaid(loginUser, this)) {
                throw new IllegalArgumentException("결제를 진행해 주세요.");
            }
        }

        this.users.add(loginUser);
    }
}
