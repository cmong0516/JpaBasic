package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Parent {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "parent",cascade = CascadeType.ALL,orphanRemoval = true)
    // cascade = CascadeType
    // 라이프 사이클이 유사 , 단일 엔티티 종속일때.
    // orphanRemoval = true
    // 부모객체와 연결관계가 끊어진 엔티티 삭제.
    // 참조하는 곳이 하나일때 사용.
    // 부모를 제거하면 자식도 함께 제거됨.
    private List<Child> childList = new ArrayList<>();

    public void addChild(Child child) {
        childList.add(child);
        child.setParent(this);
    }

    public Long getId() {
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

    public List<Child> getChildList() {
        return childList;
    }

    public void setChildList(List<Child> childList) {
        this.childList = childList;
    }
}
