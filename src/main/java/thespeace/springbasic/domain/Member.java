package thespeace.springbasic.domain;

import jakarta.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // 아이덴티티 전략: DB가 값을 자동으로 생성(@Id로 Pk 지정).
    private Long id;     // 데이터를 구분하기 위한 임의의 값, 시스템이 정한 ID.

//    @Column(name = "username") // 어노테이션으로 DB의 컬럼명 username과 맵핑.
    private String name;

    public Long getId() {   // getter and setter 단축키 : Alt + insert
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
