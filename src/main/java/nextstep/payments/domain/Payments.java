package nextstep.payments.domain;

import java.util.ArrayList;
import java.util.List;

import nextstep.courses.domain.constant.SessionFee;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;

public class Payments {

    private List<Payment> payments = new ArrayList<>();

    public static Payments autoGenerate(NsUsers nsUsers) {
        Payments list = new Payments();
        long idx = 1L;
        for (NsUser user : nsUsers.getNsUsers()) {
            list.getPayments().add(new Payment(String.valueOf(idx),
                                               idx,
                                               user.getId(),
                                               SessionFee.random().getFee()));
            idx++;
        }

        return list;
    }

    public List<Payment> getPayments() {
        return payments;
    }
}
