package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Member{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @GeneratedValue    :    자동생성.
    // 기본값 AUTO
    // IDENTITY 데이터베이스 위임 (MySQL AI)
    // SEQUENCE 데이터베이스 시퀀스 오브젝트 사용
    // SequenceGenerator 로 추가설정
    // TABLE 키 생성 전용 테이블을 생성하여 시퀀시 기능을함. 장점 : 범용성, 단점 : 성능.
    // TableGenerator
    private Long id;

    @Column(name = "name", nullable = false)
    private String username;

//    private Integer age;
//    // ORDINAL 을 사용하면 ENUM 값의 순서가 변경되었을때 원치 않는 방향으로 저장된다. 주의해야함!!
//    @Enumerated(EnumType.STRING)
//    private RoleType roleType;

//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createDate;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date lastModifiedDate;

//    private LocalDateTime startDate;
//    private LocalDateTime endDate;

    @Embedded
    private Period workPeriod;

    @Embedded
    private Address homeaddress;

//    private String city;
//    private String street;
//    private String zipcode;


//    @Lob
//    private String description;

    public Member() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public Address getHomeaddress() {
        return homeaddress;
    }

    public void setHomeaddress(Address homeaddress) {
        this.homeaddress = homeaddress;
    }
}
