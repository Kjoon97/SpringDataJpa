package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.datajpa.entity.Member;

import java.util.List;

//Spring Data JPA 레포지토리.
public interface MemberRepository extends JpaRepository<Member,Long> {  // <Member,Long> -> <엔티티 타입,엔티티 타입의 PK 타입>

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    //By뒤에 조건이 없으면 전부 조회. , Top3는 3개 조회.
    List<Member> findTop3HelloBy();
}
