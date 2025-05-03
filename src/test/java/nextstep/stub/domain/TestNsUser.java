package nextstep.stub.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class TestNsUser extends NsUser {
    private int canApproveCalled = 0;
    private int canCancelledCalled = 0;

    public TestNsUser(String userId, String password, String name, String email) {
        super(userId, password, name, email);
    }

    public TestNsUser(String userId, String password, String name, String email, String type) {
        super(userId, password, name, email, type);
    }

    public TestNsUser(String id, String userId, String password, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt, String type) {
        super(id, userId, password, name, email, createdAt, updatedAt, type);
    }

    public TestNsUser(String id, String userId, String password, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, userId, password, name, email, createdAt, updatedAt);
    }

    @Override
    public boolean canApprove(NsUser nsUser) {
        canApproveCalled++;
        return true;
    }

    @Override
    public boolean canCancel(NsUser nsUser) {
        canCancelledCalled++;
        return true;
    }

    public int getCanCancelledCalled() {
        return canCancelledCalled;
    }

    public int getCanApproveCalled() {
        return canApproveCalled;
    }
}
