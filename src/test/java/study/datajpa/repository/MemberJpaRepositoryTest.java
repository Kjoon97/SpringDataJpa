package study.datajpa.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional  //JPA의 모든 변경은 트랜잭션 안에서 이루어져야하므로 @Transactional이 없으면 오류 뜸. (기본적으로 테스트가 끝나면 롤백시킴-> 쿼리문 안보이고, 디비에도 데이터 지움)
@Rollback(false)  //롤백 false로 하면 롤백안하고 커밋함. 디비에 지워지지도 않고 쿼리 볼 수 있음. -> 공부용으로 사용.
class MemberJpaRepositoryTest {

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    public void testMember(){
        Member member = new Member("memberA");
        Member savedMember = memberJpaRepository.save(member);

        Member findMember = memberJpaRepository.find(savedMember.getId());

        //JPA는 같은 트랜잭션 안에서는 영속성 컨텍스트의 동일성을 보장한다. savedMember와 findMember는 같은 인스턴스이다.

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
    }
}