package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.HashSet;
import java.util.Set;

public class Enrollment {
    private final static int MAX_SIZE = 100_000;

    private Set<NsUser> enrollment;

    private int maxSize;

    public Enrollment(final Charge charge, final int maxSize) {
        this(new HashSet<>(), charge.getChargeStatus(), maxSize(charge.getChargeStatus(), maxSize));
    }

    public Enrollment(final ChargeStatus chargeStatus, final int maxSize) {
        this(new HashSet<>(), chargeStatus, maxSize(chargeStatus, maxSize));
    }

    private static int maxSize(final ChargeStatus chargeStatus, final int maxSize) {
        if (chargeStatus.equals(ChargeStatus.FREE)) {
            return MAX_SIZE;
        }

        if (maxSize < 1) {
            throw new IllegalArgumentException("수강 가능 인원이 음수일 수 없습니다.");
        }
        return maxSize;
    }

    private Enrollment(final Set<NsUser> enrollment, final ChargeStatus chargeStatus, final int maxSize) {
        this.enrollment = enrollment;
        this.maxSize = maxSize;
    }

    public boolean isIncluded(NsUser nsUser) {
        return enrollment.contains(nsUser);
    }

    public void add(SessionStatus sessionStatus, NsUser nsUser) {
        if (enrollment.size() == MAX_SIZE) {
            throw new IllegalArgumentException("수강 가능 인원이 꽉 찼습니다.");
        }
        if (!sessionStatus.equals(SessionStatus.RECRUITING)) {
            throw new IllegalArgumentException("강의 상태가 '모집중'일 때만 수강생을 추가할 수 있습니다.");
        }
        enrollment.add(nsUser);
    }
}
