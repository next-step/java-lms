package nextstep.courses.domain.session;

public class SessionPrice {

    private static final int ZERO_PRICE = 0;

    private int price;

    public SessionPrice(int price) {
        validate(price);
        this.price = price;
    }

    private void validate(int price) {
        if(price < ZERO_PRICE) {
            throw new IllegalArgumentException("금액은 음수일 수 없습니다");
        }
    }

    public boolean validatePrice(int userPay) {
        if(this.price != userPay) {
            throw new IllegalArgumentException("정확한 수강료를 납부해주세요");
        }
        return true;
    }
}
