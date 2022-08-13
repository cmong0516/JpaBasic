package hellojpa;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
// 장점
// 1. 테이블 정규화
// 2. 외래키 참조 무결성 제약조건 활용가능
// 3. 저장공간 효율화
// 단점
// 1. 조회시 조인 많이 사용
// 2. 조회 쿼리 복잡
// 3. 데이터 저장시 Insert 2번 호출.
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// 장점
// 1. 조인이 필요 없으므로 조회 성능이 빠르고 조회 쿼리가 단순하다.
// 단점
// 1. 자식 엔티티가 매핑한 컬럼은 모두 null 허용
// 2. 단일 테이블에서 모든것을 저장하므로 테이브이 커질수 있다.
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
// DBA , ORM 양쪽 모두에서 비추천함.
// 장점
// 1. 서브 타입을 명확하게 구분해서 처리할때 효과적
// 단점
// 1. 여러 자식 테이블을 함께 조회할때 성능 다운
// 2. 자식 테이블을 통합해서 쿼리가 어려움.
@DiscriminatorColumn
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int price;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
