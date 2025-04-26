package nextstep.users.infrastructure.entity;

import nextstep.courses.infrastructure.entity.BaseEntity;
import nextstep.users.domain.NsUser;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class NsUserEntity extends BaseEntity {

    private String userId;

    private String password;

    private String name;

    private String email;

    private BigDecimal balance;


    public NsUserEntity() {
        super();
    }

    public NsUserEntity(Long id, String userId, String password, String name, String email, BigDecimal balance, Timestamp createdAt, Timestamp updatedAt) {
        super(id, createdAt, updatedAt);
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.balance = balance;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public NsUser toDomain() {
        return new NsUser(getId(), userId, password, name, email, balance, getCreatedAt().toLocalDateTime(), getUpdatedAt().toLocalDateTime());
    }
}
