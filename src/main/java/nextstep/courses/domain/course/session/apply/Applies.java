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

    public Apply addApply(Long sessionId, Long nsUserId, LocalDateTime date) {
        checkApplicantAlreadyExisted(nsUserId);

        Apply addedApply = new Apply(sessionId, nsUserId, false, date);
        this.applies.add(addedApply);

        return addedApply;
    }

    private void checkApplicantAlreadyExisted(Long nsUserId) {
        if (this.containsUserId(nsUserId)) {
            throw new IllegalArgumentException("이미 수강 신청 이력이 있습니다.");
        }
    }

    public boolean containsUserId(Long nsUserId) {
        return this.applies.stream()
                .anyMatch(apply -> apply.isSameWithUserId(nsUserId));
    }

    public Apply approve(Apply apply, LocalDateTime date) {
        return this.applies.stream()
                .filter(savedApply -> approved(apply, savedApply))
                .findAny()
                .map(savedApply -> savedApply.approve(date))
                .orElseThrow(() -> new IllegalArgumentException("지원자가 미승인 상태인지 확인하세요."));
    }

    private static boolean approved(Apply apply, Apply savedApply) {
        return savedApply.isSame(apply) && savedApply.isCanceled();
    }

    public Apply cancel(Apply apply, LocalDateTime date) {
        return this.applies.stream()
                .filter(savedApply -> canceled(apply, savedApply))
                .findAny()
                .map(savedApply -> savedApply.cancel(date))
                .orElseThrow(() -> new IllegalArgumentException("지원자가 승인 상태인지 확인하세요."));
    }

    private static boolean canceled(Apply apply, Apply savedApply) {
        return savedApply.isSame(apply) && savedApply.isApproved();
    }

    @Override
    public String toString() {
        return "Applicants{" +
                "applicants=" + applies +
                '}';
    }

    @Override
    public Iterator<Apply> iterator() {
        return this.applies.iterator();
    }
}
