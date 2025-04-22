package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberTest {

    @Test
    @DisplayName("Member는 생성 시 id, name, email 값을 가진다")
    void member_creation_and_getters() {
        Member member = new Member(1L, "홍길동", "hong@example.com");

        assertThat(member.getId()).isEqualTo(1L);
        assertThat(member.getName()).isEqualTo("홍길동");
        assertThat(member.getEmail()).isEqualTo("hong@example.com");
    }

    @Test
    @DisplayName("id가 같은 두 Member는 equals로 같다고 판단한다")
    void members_with_same_id_are_equal() {
        Member member1 = new Member(1L, "홍길동", "hong@example.com");
        Member member2 = new Member(1L, "이름무관", "다른이메일@example.com");

        assertThat(member1).isEqualTo(member2);
    }

    @Test
    @DisplayName("id가 다른 두 Member는 equals로 다르다고 판단한다")
    void members_with_different_id_are_not_equal() {
        Member member1 = new Member(1L, "홍길동", "hong@example.com");
        Member member2 = new Member(2L, "홍길동", "hong@example.com");

        assertThat(member1).isNotEqualTo(member2);
    }

    @Test
    @DisplayName("id가 같으면 hashCode도 동일하다")
    void members_with_same_id_have_same_hashcode() {
        Member member1 = new Member(1L, "홍길동", "hong@example.com");
        Member member2 = new Member(1L, "다른이름", "다른이메일@example.com");

        assertThat(member1.hashCode()).isEqualTo(member2.hashCode());
    }
}
