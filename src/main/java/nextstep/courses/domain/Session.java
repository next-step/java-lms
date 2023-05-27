package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Session extends BaseEntity {

    private LocalDate startDate;

    private LocalDate endDate;

    private CoverImage coverImage;

    private FeeType feeType;

    private SessionStatus sessionStatus = SessionStatus.PREPARING;

    private final int maxNumberOfStudent;

    private final List<NsUser> users = new ArrayList<>();

    public Session(LocalDate startDate, LocalDate endDate, int maxNumberOfStudent) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.feeType = FeeType.FREE;
        this.maxNumberOfStudent = maxNumberOfStudent;
    }

    public Session(LocalDate startDate, LocalDate endDate, CoverImage coverImage, FeeType feeType, int maxNumberOfStudent) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.coverImage = coverImage;
        this.feeType = feeType;
        this.maxNumberOfStudent = maxNumberOfStudent;
    }

    public void register(NsUser nsUser) {
        if (!sessionStatus.equals(SessionStatus.RECRUITING)) {
            throw new IllegalStateException("강의 수강신청은 강의 상태가 모집중일 때만 가능합니다.");
        }
        if (users.size() >= maxNumberOfStudent) {
            throw new IllegalStateException("강의는 강의 최대 수강 인원을 초과할 수 없습니다.");
        }
        users.add(nsUser);
    }

    public Session startRecruit() {
        if (endDate.isBefore(LocalDate.now())) {
            throw new IllegalStateException("종료된 강의는 모집할 수 없습니다.");
        }
        this.sessionStatus = SessionStatus.RECRUITING;
        return this;
    }

    public Session changeFeeType(FeeType toFeetype) {
        this.feeType = toFeetype;
        return this;
    }
}
