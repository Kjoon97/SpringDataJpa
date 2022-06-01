package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.entity.Member;

import java.util.List;

//Spring Data JPA 레포지토리.
public interface MemberRepository extends JpaRepository<Member,Long> {  // <Member,Long> -> <엔티티 타입,엔티티 타입의 PK 타입>

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    //By뒤에 조건이 없으면 전부 조회. , Top3는 3개 조회.
    List<Member> findTop3HelloBy();

    //스프링 데이터 jpa-  네임드쿼리 사용하기.(실무에서 잘 사용 안함.)
    @Query(name = "Member.findByUsername")
    List<Member> findByUsername(@Param("username") String username);

    //메서드에 JPQL 쿼리 작성(이 방법을 실무에서 사용 많이 함.)
    @Query("select m from Member m where m.username= :username and m.age = :age")  // -> 오타나면 컴파일 에러 뜸.
    List<Member> findUser(@Param("username") String username, @Param("age") int age);
}
