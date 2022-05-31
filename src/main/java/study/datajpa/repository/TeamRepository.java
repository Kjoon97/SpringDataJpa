package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.datajpa.entity.Team;

//어노테이션 사용안하고 인터페이스만 있어도 스프링이 인지하고 알아서 프록시로 구현 클래스를 만들어서 사용함.
public interface TeamRepository extends JpaRepository<Team, Long> {
}
