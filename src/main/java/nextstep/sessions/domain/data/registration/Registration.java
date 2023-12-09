package nextstep.sessions.domain.data.registration;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.session.Session;
import nextstep.sessions.domain.data.session.UserPaymentInfo;
import nextstep.sessions.domain.exception.SessionsException;
import nextstep.users.domain.NsUser;

public class Registration {

    private Long id;
    private final RegistrationInfo registrationInfo;
    private final RegistrationProcedure registrationProcedure;

    public Registration(Session session, NsUser user, Payment payment) {
        this.registrationInfo = new RegistrationInfo(session, new UserPaymentInfo(user, payment));
        this.registrationProcedure = new RegistrationProcedure(SelectionType.BEFORE_SELECTION, ApprovalType.BEFORE_APPROVAL);
    }

    public Registration(Session session, NsUser user, Payment payment, Long id, SelectionType selectionType, ApprovalType approvalType) {
        this(id,
            new RegistrationInfo(session, new UserPaymentInfo(user, payment)),
            new RegistrationProcedure(selectionType, approvalType)
        );
    }

    public Registration(Long id, RegistrationInfo registrationInfo, RegistrationProcedure registrationProcedure) {
        this.id = id;
        this.registrationInfo = registrationInfo;
        this.registrationProcedure = registrationProcedure;
    }

    public boolean hasUser(NsUser user) {
        return registrationInfo.hasEqualUser(user);
    }

    public long userId() {
        return registrationInfo.userId();
    }

    public long paymentId() {
        return registrationInfo.paymentId();
    }

    public void select() {
        validateSelection();
        registrationProcedure.select();
    }

    public void approve() {
        validateApproval();
        registrationProcedure.approve();
    }

    public void cancel() {
        validateCancel();
    }

    private void validateSelection() {
        if (registrationProcedure.isSelected()) {
            throw new SessionsException("이미 선발된 인원입니다.");
        }
    }

    private void validateApproval() {
        if (!registrationProcedure.isSelected()) {
            throw new SessionsException("선발된 인원만 승인할 수 있습니다.");
        }
        if (registrationProcedure.isApproved()) {
            throw new SessionsException("이미 승인된 인원입니다.");
        }
    }

    private void validateCancel() {
        if (!registrationProcedure.isBeforeSelection()) {
            throw new SessionsException("미선발된 인원이 아닙니다.");
        }
    }

    public ApprovalType approvalType() {
        return registrationProcedure.approvalType();
    }

    public SelectionType selectionType() {
        return registrationProcedure.selectionType();
    }

    public Payment payment() {
        return registrationInfo.payment();
    }

    public NsUser user() {
        return registrationInfo.user();
    }

    public Long id() {
        return id;
    }

    public Long sessionId() {
        return registrationInfo.sessionId();
    }
}
