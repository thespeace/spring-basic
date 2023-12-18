package thespeace.springbasic.domain;

public class Member {

    private Long id;     // 데이터를 구분하기 위한 임의의 값, 시스템이 정한 ID.
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
