package nextstep.courses.domain.session;

import nextstep.courses.domain.cover.Image;
import nextstep.courses.domain.session.RegistrationCount;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionName;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.domain.session.Period;
import nextstep.courses.entity.BaseEntity;
import nextstep.payments.domain.Money;
import nextstep.payments.domain.Payment;

public abstract class AbstractSession extends BaseEntity implements Session {

    private final Long id;

    private final SessionName sessionName;

    protected final RegistrationCount registrationCount;

    protected final Money tuitionFee;

    private final Image image;

    protected final SessionStatus sessionStatus;

    private final Period period;

    public AbstractSession(Long id, SessionName sessionName, RegistrationCount registrationCount,
        Money tuitionFee, Image image, SessionStatus sessionStatus, Period period) {
        this.id = id;
        this.sessionName = sessionName;
        this.registrationCount = registrationCount;
        this.tuitionFee = tuitionFee;
        this.image = image;
        this.sessionStatus = sessionStatus;
        this.period = period;
    }

    @Override
    public void addRegistrationCount() {
        registrationCount.addValue();
    }

    @Override
    public boolean isRegistrationAvailable() {
        return isRecruitmentOpen(sessionStatus);
    }

    @Override
    public boolean isPaymentAmountSameTuitionFee(Payment payment) {
        return payment.isSamePaymentAmount(tuitionFee);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getSessionName() {
        return sessionName.getValue();
    }

    @Override
    public int getRegistrationCount() {
        return registrationCount.getValue();
    }

    @Override
    public int getTuitionFee() {
        return tuitionFee.getValue();
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public String getSessionStatus() {
        return sessionStatus.getValue();
    }

    @Override
    public Period getValidityPeriod() {
        return period;
    }
}
