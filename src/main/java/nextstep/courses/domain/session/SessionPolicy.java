package nextstep.courses.domain.session;

import nextstep.courses.domain.image.SessionCoverImages;
import nextstep.courses.domain.registration.Registrations;
import nextstep.courses.domain.session.policy.approval.ApprovalPolicy;
import nextstep.courses.domain.session.policy.capacity.CapacityPolicy;
import nextstep.courses.domain.session.policy.capacity.LimitedCapacity;
import nextstep.courses.domain.session.policy.capacity.UnlimitedCapacity;
import nextstep.courses.domain.session.policy.tuition.FreeTuition;
import nextstep.courses.domain.session.policy.tuition.PaidTuition;
import nextstep.courses.domain.session.policy.tuition.TuitionPolicy;

public class SessionPolicy {
    private final TuitionPolicy tuitionPolicy;
    private final CapacityPolicy capacityPolicy;
    private final ApprovalPolicy approvalPolicy;

    public SessionPolicy() {
        this(new FreeTuition(), new UnlimitedCapacity(), ApprovalPolicy.AUTO);
    }

    public SessionPolicy(long tuitionFee, int maxCapacity) {
        this(new PaidTuition(tuitionFee), new LimitedCapacity(maxCapacity), ApprovalPolicy.AUTO);
    }

    public SessionPolicy(TuitionPolicy tuitionPolicy, CapacityPolicy capacityPolicy) {
        this(tuitionPolicy, capacityPolicy, ApprovalPolicy.AUTO);
    }

    public SessionPolicy(TuitionPolicy tuitionPolicy, CapacityPolicy capacityPolicy, ApprovalPolicy approvalPolicy) {
        this.tuitionPolicy = tuitionPolicy;
        this.capacityPolicy = capacityPolicy;
        this.approvalPolicy = approvalPolicy;
    }

    public Session createSession(Long courseId, int term, String startDay, String endDay, SessionCoverImages coverImages) {
        return new Session(courseId, term, startDay, endDay, this, coverImages);
    }

    public Session createSession(Long id, Long courseId, Term term, SessionPeriod period, SessionProgressState state, SessionCoverImages coverImages) {
        return new Session(id, courseId, term, period, state, this, coverImages);
    }

    public static SessionPolicy free() {
        return new SessionPolicy();
    }

    public static SessionPolicy paid(long tuitionFee, int maxCapacity) {
        return new SessionPolicy(tuitionFee, maxCapacity);
    }

    public void validate(long payAmount, Registrations registrations) {
        tuitionPolicy.validate(payAmount);
        capacityPolicy.validate(registrations);
    }

    public TuitionPolicy getTuitionPolicy() {
        return tuitionPolicy;
    }

    public CapacityPolicy getCapacityPolicy() {
        return capacityPolicy;
    }

    public ApprovalPolicy getApprovalPolicy() {
        return approvalPolicy;
    }
}
