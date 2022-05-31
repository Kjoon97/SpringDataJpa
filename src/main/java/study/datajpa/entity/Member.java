package study.datajpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //protected 타입 기본 생성자.
@ToString(of = {"id", "username", "age"})  //연관관계인 team은 안 넣어야함. -> 무한 루프.. team참조하면 team에서 member도 참조되고 member에서 team 참조... 반복..
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="team_id")   //외래키 명
    private Team team;

    public Member(String username) {
        this.username = username;
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if (team!=null){
            changeTeam(team);
        }
    }

    //연관관계 세팅. - 팀을 바꾸는 동시에 반대쪽 team에서도 member가 추가되게끔.
    public void changeTeam(Team team){
        this.team = team;
        team.getMembers().add(this);
    }

}
