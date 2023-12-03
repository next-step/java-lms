package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.List;

public class SessionType {

    private PayType type;
    private Integer price;
    private Integer capacity;

    public SessionType() {
        this.type = PayType.FREE;
    }

    public SessionType(PayType type, Integer price, Integer capacity) {
        this.type = type;
        this.price = price;
        this.capacity = capacity;
    }

    public boolean isMaxCapacity(List<NsUser> userList) {
        if (PayType.FREE.equals(type)) {
            return true;
        }
        return capacity < userList.size();
    }
}
