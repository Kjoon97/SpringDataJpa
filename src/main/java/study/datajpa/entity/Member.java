package study.datajpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    private Long id;
    private String username;

    protected Member(){  //JPA는 프록시 기술을 쓸 때 기본 생성자가 있어야한다(private으로 하면 안됨). 아무데서나 호출되지 않게 protected로 선언.

    }
    public Member(String username) {
        this.username = username;
    }

    //setter 대신 따로 매서드 만들어서 사용.
    public void changeUsername(String username){
        this.username = username;
    }
}
