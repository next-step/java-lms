package nextstep.session;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Session {

    private static final String DATE_MESSAGE = "종료일이 시작일보다 빠릅니다.";
    private static final String PAID_SUBSCRIBE_MESSAGE = "유료강의는 결제내역이 필수입니다.";
    private static final String FREE_SUBSCRIBE_MESSAGE = "무료강의는 결제내역이 필요없습니다.";
    private static final String SUBSCRIBE_STATUS_NOT_WAIT_MESSAGE = "현재 강의가 모집중이 아닙니다.";
    private static final String SUBSCRIBE_COUNT_MAX_MESSAGE = "강의가 이미 만석입니다.";

    private final Long id;
    private final String title;
    private final Image image;
    private final PaymentType paymentType;
    private SubscribeStatus subscribeStatus;
    private int subscribeMax;
    private int price;
    private final List<NsUser> subscribeUsers = new ArrayList<>();
    private final Date startDate;
    private final Date endDate;

    private Session(Long id, String title, Image image, PaymentType paymentType, int subscribeMax, int price, Date startDate, Date endDate) {
        confirmDate(startDate, endDate);
        this.id = id;
        this.title = title;
        this.image = image;
        this.paymentType = paymentType;
        this.subscribeStatus = SubscribeStatus.READY;
        this.subscribeMax = subscribeMax;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private Session(Long id, String title, Image image, PaymentType paymentType, Date startDate, Date endDate) {
        confirmDate(startDate, endDate);
        this.id = id;
        this.title = title;
        this.image = image;
        this.paymentType = paymentType;
        this.subscribeStatus = SubscribeStatus.READY;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    //무료강의를 생성한다.
    public static Session createFree(Long id, String title, Image image, Date startDate, Date endDate) {
        return new Session(id, title, image, PaymentType.FREE, startDate, endDate);
    }

    //유료강의를 생성한다.
    public static Session createPaid(Long id, String title, Image image, int subscribeMax, int price, Date startDate, Date endDate) {
        return new Session(id, title, image, PaymentType.PAID, subscribeMax, price, startDate, endDate);
    }

    //수강신청을 한다(무료)
    public void subsribe(NsUser user) {
        confirmSubscribeStatus();
        if (paymentType == PaymentType.PAID) {
            throw new IllegalArgumentException(PAID_SUBSCRIBE_MESSAGE);
        }
        subscribeUser(user);
    }

    //수강신청을 한다(유료)
    public void subsribe(NsUser user, Payment payment) {
        confirmSubscribeStatus();
        if (paymentType == PaymentType.FREE) {
            throw new IllegalArgumentException(FREE_SUBSCRIBE_MESSAGE);
        }
        payment.checkMatchAmount(this.price);
        confirmSubscribeMax();
        subscribeUser(user);
    }

    //모집중으로 상태를 변경한다.
    public void waitSession() {
        changeSubscribeStatus(SubscribeStatus.WAIT);
    }

    //종료로 상태를 변경한다.
    public void closedSession() {
        changeSubscribeStatus(SubscribeStatus.CLOSED);
    }

    public int getSubscribeCount() {
        return this.subscribeUsers.size();
    }

    public SubscribeStatus getSubscribeStatus() {
        return subscribeStatus;
    }

    //수강신청 상태를 변경한다.
    private void changeSubscribeStatus(SubscribeStatus subscribeStatus) {
        this.subscribeStatus = subscribeStatus;
    }

    //수강신청하면 인원을 증가시킨다.
    private void subscribeUser(NsUser user) {
        this.subscribeUsers.add(user);
    }

    //시작일 종료일 날짜를 점검한다.
    private void confirmDate(Date startDate, Date endDate) {
        if (startDate.after(endDate)) {
            throw new IllegalArgumentException(DATE_MESSAGE);
        }
    }

    //강의가 모집중인지 확인한다.
    private void confirmSubscribeStatus() {
        if (this.subscribeStatus != SubscribeStatus.WAIT) {
            throw new IllegalArgumentException(SUBSCRIBE_STATUS_NOT_WAIT_MESSAGE);
        }
    }

    //수강신청인원이 다 찼는지 확인한다.
    private void confirmSubscribeMax() {
        if (this.subscribeMax < this.subscribeUsers.size() + 1) {
            throw new IllegalArgumentException(SUBSCRIBE_COUNT_MAX_MESSAGE);
        }
    }
}
