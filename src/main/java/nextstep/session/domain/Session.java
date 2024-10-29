package nextstep.session.domain;

import nextstep.DateDomain;
import nextstep.payments.domain.Payment;
import nextstep.session.domain.image.Image;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.List;

public class Session {

    private static final String FREE_PAID_MESSAGE = "해당 강의는 %s강의입니다.";
    private static final String PAID_SUBSCRIBE_MESSAGE = "유료강의는 결제내역이 필수입니다.";
    private static final String FREE_SUBSCRIBE_MESSAGE = "무료강의는 결제내역이 필요없습니다.";
    private static final String SUBSCRIBE_STATUS_NOT_WAIT_MESSAGE = "현재 강의가 모집중이 아닙니다.";
    private static final String SUBSCRIBE_COUNT_MAX_MESSAGE = "강의가 이미 만석입니다.";

    private Long id;
    private final String title;
    private final Image image;
    private final PaymentType paymentType;
    private SubscribeStatus subscribeStatus;
    private int subscribeMax;
    private int price;
    private Subscribers subscribers = new Subscribers();
    private final DateRange dateRange;
    private final DateDomain dateDomain;

    public Session(String title, Image image, PaymentType paymentType, int subscribeMax, int price, LocalDateTime startDate, LocalDateTime endDate) {
        this.title = title;
        this.image = image;
        this.paymentType = paymentType;
        this.subscribeStatus = SubscribeStatus.READY;
        this.subscribeMax = subscribeMax;
        this.price = price;
        this.dateRange = new DateRange(startDate, endDate);
        this.dateDomain = new DateDomain();
    }

    private Session(Long id, String title, Image image, PaymentType paymentType, int subscribeMax, int price, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.paymentType = paymentType;
        this.subscribeStatus = SubscribeStatus.READY;
        this.subscribeMax = subscribeMax;
        this.price = price;
        this.dateRange = new DateRange(startDate, endDate);
        this.dateDomain = new DateDomain();
    }

    private Session(Long id, String title, Image image, PaymentType paymentType, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.paymentType = paymentType;
        this.subscribeStatus = SubscribeStatus.READY;
        this.dateRange = new DateRange(startDate, endDate);
        this.dateDomain = new DateDomain();
    }

    public Session(Long id, String title, String paymentType, String subscribeStatus, int subscribeMax, int price, LocalDateTime startDate, LocalDateTime endDate, String imageName, int imageWidth, int imageHeight, int imageCapacity, List<Subscriber> subscribers, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.paymentType = PaymentType.valueOf(paymentType);
        this.price = price;
        this.subscribeMax = subscribeMax;
        this.subscribeStatus = SubscribeStatus.valueOf(subscribeStatus);
        this.dateRange = new DateRange(startDate, endDate);
        this.image = new Image(imageName, imageWidth, imageHeight, imageCapacity);
        this.subscribers = new Subscribers(subscribers);
        this.dateDomain = new DateDomain(createdAt, updatedAt);
    }

    public Session(Long id, String title, String paymentType, String subscribeStatus, int subscribeMax, int price, LocalDateTime startDate, LocalDateTime endDate, String imageName, int imageWidth, int imageHeight, int imageCapacity, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.paymentType = PaymentType.valueOf(paymentType);
        this.price = price;
        this.subscribeMax = subscribeMax;
        this.subscribeStatus = SubscribeStatus.valueOf(subscribeStatus);
        this.dateRange = new DateRange(startDate, endDate);
        this.image = new Image(imageName, imageWidth, imageHeight, imageCapacity);
        this.dateDomain = new DateDomain(createdAt, updatedAt);
    }

    public static Session createFree(Long id, String title, Image image, LocalDateTime startDate, LocalDateTime endDate) {
        return new Session(id, title, image, PaymentType.FREE, startDate, endDate);
    }

    public static Session createPaid(Long id, String title, Image image, int subscribeMax, int price, LocalDateTime startDate, LocalDateTime endDate) {
        return new Session(id, title, image, PaymentType.PAID, subscribeMax, price, startDate, endDate);
    }

    public Subscriber subscribe(NsUser user) {
        confirmSubscribeStatus();
        if (paymentType == PaymentType.PAID) {
            throw new IllegalArgumentException(PAID_SUBSCRIBE_MESSAGE);
        }
        return subscribeUser(user);
    }

    public Subscriber subscribe(NsUser user, Payment payment) {
        confirmSubscribeStatus();
        if (paymentType == PaymentType.FREE) {
            throw new IllegalArgumentException(FREE_SUBSCRIBE_MESSAGE);
        }
        payment.checkMatchAmount(this.price);
        confirmSubscribeMax();
        return subscribeUser(user);
    }

    public void waitSession() {
        changeSubscribeStatus(SubscribeStatus.WAIT);
    }

    public void closedSession() {
        changeSubscribeStatus(SubscribeStatus.CLOSED);
    }

    public int getSubscribeCount() {
        return this.subscribers.subscribeUsersSize();
    }

    public SubscribeStatus getSubscribeStatus() {
        return subscribeStatus;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Image getImage() {
        return image;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public int getSubscribeMax() {
        return subscribeMax;
    }

    public int getPrice() {
        return price;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public DateDomain getDateDomain() {
        return dateDomain;
    }

    public Subscribers getSubscribers() {
        return subscribers;
    }

    public boolean checkFreePaid() {
        return this.paymentType.equals(PaymentType.FREE);
    }

    private void changeSubscribeStatus(SubscribeStatus subscribeStatus) {
        this.subscribeStatus = subscribeStatus;
    }

    private Subscriber subscribeUser(NsUser nsUser) {
        return this.subscribers.addUser(this, nsUser);
    }


    private void confirmSubscribeStatus() {
        if (this.subscribeStatus != SubscribeStatus.WAIT) {
            throw new IllegalArgumentException(SUBSCRIBE_STATUS_NOT_WAIT_MESSAGE);
        }
    }

    private void confirmSubscribeMax() {
        if (this.subscribeMax < this.subscribers.subscribeUsersSize() + 1) {
            throw new IllegalArgumentException(SUBSCRIBE_COUNT_MAX_MESSAGE);
        }
    }
}
