package study.datajpa.repository;

import org.springframework.stereotype.Repository;
import study.datajpa.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberJpaRepository {

    @PersistenceContext
    private EntityManager em;

    //create
    public Member save(Member member){
        em.persist(member);
        return member;
    }

    //delete
    public void delete(Member member){
        em.remove(member);
    }

    //전체 조회
    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    //Optional- null일수도 있고 아닐 수도 있고.
    public Optional<Member> findById(Long id){
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    //회원 수 조회.
    public long count(){
        return em.createQuery("select count(m) from Member m", Long.class).getSingleResult();
    }

    //멤버 조회
    public Member find(Long id){
        return em.find(Member.class, id);
    }

    //몇 살 이상보다 나이가 많은 사람의 이름을 조회.
    public List<Member> findByUsernameAndAgeGreaterThan(String username, int age) {
        return em.createQuery("select m from Member m where m.username = :username and m.age > :age")
                .setParameter("username", username)
                .setParameter("age", age)
                .getResultList();
    }

    //JPA - named 쿼리 사용.
    public List<Member> findByUsername(String username) {

        return em.createNamedQuery("Member.findByUsername", Member.class)
                .setParameter("username", username)
                .getResultList();
    }

}
