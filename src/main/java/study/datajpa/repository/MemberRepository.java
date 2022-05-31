package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.datajpa.entity.Member;

//Spring Data JPA 레포지토리.
public interface MemberRepository extends JpaRepository<Member,Long> {  // <Member,Long> -> <엔티티 타입,엔티티 타입의 PK 타입>
}
