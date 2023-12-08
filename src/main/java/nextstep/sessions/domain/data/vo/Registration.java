package nextstep.sessions.domain.data.vo;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.Session;
import nextstep.sessions.domain.data.type.ApprovalType;
import nextstep.sessions.domain.data.type.SelectionType;
import nextstep.sessions.domain.exception.SessionsException;
import nextstep.users.domain.NsUser;

public class Registration {

    private final RegistrationInfo registrationInfo;
    private final RegistrationProcedure registrationProcedure;

    public Registration(Session session, NsUser user, Payment payment) {
        this(
            new RegistrationInfo(session, new UserPaymentInfo(user, payment)),
            new RegistrationProcedure(SelectionType.BEFORE_SELECTION, ApprovalType.BEFORE_APPROVAL)
        );
    }

    public Registration(Session session, NsUser user, Payment payment, SelectionType selectionType, ApprovalType approvalType) {
        this(
            new RegistrationInfo(session, new UserPaymentInfo(user, payment)),
            new RegistrationProcedure(selectionType, approvalType)
        );
    }

    public Registration(RegistrationInfo registrationInfo, RegistrationProcedure registrationProcedure) {
        this.registrationInfo = registrationInfo;
        this.registrationProcedure = registrationProcedure;
    }

    public boolean hasUser(NsUser user) {
        return registrationInfo.hasEqualUser(user);
    }

    public UserPaymentInfo userPaymentInfo() {
        return registrationInfo.userPaymentInfo();
    }

    public long userId() {
        return registrationInfo.userId();
    }

    public long paymentId() {
        return registrationInfo.paymentId();
    }

    public void validateSelection() {
        if (registrationProcedure.isSelected()) {
            throw new SessionsException("이미 선발된 인원입니다.");
        }
    }

    public void validateApproval() {
        if (!registrationProcedure.isSelected()) {
            throw new SessionsException("선발된 인원만 승인할 수 있습니다.");
        }
        if (registrationProcedure.isApproved()) {
            throw new SessionsException("이미 승인된 인원입니다.");
        }
    }

    public void validateCancel() {
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
}
