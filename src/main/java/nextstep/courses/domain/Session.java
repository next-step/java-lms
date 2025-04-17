package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Session {

    private final CoverImage coverImage;
    private final LocalDate startDt;
    private final LocalDate endDt;

    private int capacity;
    private Conditional conditional;
    private BigDecimal cost;
    private SessionStatus status;
    private List<NsUser> students;

    public Session() {
        this(new CoverImage(), LocalDate.now(), LocalDate.now());
    }

    public Session(CoverImage coverImage, LocalDate startDt, LocalDate endDt) {
        this.coverImage = coverImage;
        this.startDt = startDt;
        this.endDt = endDt;
        this.status = SessionStatus.READY;
        this.students = new ArrayList<>();
    }

    public void asFree() {
        this.capacity = Integer.MAX_VALUE;
        this.cost = BigDecimal.ZERO;
        conditional = (currentNum) -> true;
    }

    public void asPaid(int capacity, BigDecimal cost) {
        this.capacity = capacity;
        this.cost = cost;
        conditional = (currentNum) -> currentNum < this.capacity;
    }

    public boolean isAvailable() {
        return conditional.test(students.size());
    }

    public boolean isExactCost(BigDecimal cost) {
        return cost.compareTo(this.cost) == 0;
    }

    public CoverImage getCoverImage() {
        return this.coverImage;
    }

    public LocalDate getStartDt() {
        return startDt;
    }

    public LocalDate getEndDt() {
        return endDt;
    }

    public SessionStatus getSessionStatus() {
        return status;
    }

    public void apply(NsUser nsUser) {
        SessionStatus.isReady(status);

        if (this.isAvailable()) {
            students.add(nsUser);
        }
    }

    public void open() {
        status = SessionStatus.open();
    }

    public void close() {
        status = SessionStatus.close();
    }
}
