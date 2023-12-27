package nextstep.courses.domain.course.session.apply;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Applies implements Iterable<Apply> {
    private final List<Apply> applies;

    public Applies() {
        this(new ArrayList<>());
    }

    public Applies(List<Apply> applicants) {
        this.applies = applicants;
    }

    public int size() {
        return this.applies.size();
    }

    public boolean containsUserId(Long nsUserId) {
        return this.applies.stream()
                .anyMatch(apply -> apply.isSameWithUserId(nsUserId));
    }

    public Apply approve(Apply apply, LocalDateTime date) {
        return this.applies.stream()
                .filter(savedApply -> matchesNotApproved(apply, savedApply))
                .findAny()
                .map(savedApply -> savedApply.approve(date))
                .orElseThrow(() -> new IllegalArgumentException("지원자가 대기, 취소 상태인지 확인하세요."));
    }

    private static boolean matchesNotApproved(Apply apply, Apply savedApply) {
        return savedApply.isSame(apply) && savedApply.notApproved();
    }

    public Apply cancel(Apply apply, LocalDateTime date) {
        return this.applies.stream()
                .filter(savedApply -> matchesNotCanceled(apply, savedApply))
                .findAny()
                .map(savedApply -> savedApply.cancel(date))
                .orElseThrow(() -> new IllegalArgumentException("지원자가 대기, 승인 상태인지 확인하세요."));
    }

    private boolean matchesNotCanceled(Apply apply, Apply savedApply) {
        return savedApply.isSame(apply) && savedApply.notCanceled();
    }

    @Override
    public String toString() {
        return "Applies{" +
                "applies=" + applies +
                '}';
    }

    @Override
    public Iterator<Apply> iterator() {
        return this.applies.iterator();
    }
}
