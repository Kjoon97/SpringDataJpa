package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

import java.util.Collection;
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

    //단순히 값 하나를 조회
    @Query("select m.username from Member m")
    List<String> findUsernameList();

    //Dto 조회.
    @Query("select new study.datajpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    //컬렉션 파라미터 바인딩(많이 사용)
    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") Collection<String> names);
}
