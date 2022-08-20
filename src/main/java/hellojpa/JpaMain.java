package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

//        Member member = new Member();
//        member.setId(2L);
//        member.setName("HelloB");
//
//        em.persist(member);
//
//        tx.commit();
//
//
//        em.close();
//        emf.close();
        // 위의 코드는 중간에 에러가 발생하면 아래 코드가 실행되지 않는다.
        // 해결법 : Try catch 로 감싼다.


        try {

            // 회원 저장
            // *객체 생성    (비영속)
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
            // *em.persist 로 영속상태로 만듬
//            em.persist(member);
            // 회원 찾기
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());
            // 결과
//            findMember = 1
//            findMember.getName() = HelloA

            // 회원 수정
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("HelloJPA");
//            HelloA  - >    HelloJPA

            // 전체 조회 (JPQL)
            // JPQL 은 엔티티 객체를 대상으로 쿼리, SQL 은 테이블 대상으로 쿼리
//            List<Member> result = em.createQuery("select m from Member m", Member.class)
//                    // 페이징 처리도 가능.
////                    .setFirstResult(0)
////                    .setMaxResults(2)
//                    .getResultList();
//
//            for (Member member : result) {
//                System.out.println("member.getName() = " + member.getName());
//            }
//            // 회원 삭제
//            Member findMember = em.find(Member.class, 2L);
//            em.remove(findMember);

//            Member member = new Member();
////            member.setId(3L);
//            // @GeneratedValue
//            member.setUsername("C");
//            member.setRoleType(RoleType.GUEST);



//            em.persist(member);
            // GeneratedValue 를 사용하여 id 값을 MySQL 에서 AI 로 두었고 값 설정을 따로 하지 않았는데
            // IDENTITY 를 사용하여 DB에 값이 들어갈때 id 값이 생성되므로 INSERT 쿼리를 persist 할때 보낸다.

//            Movie movie = new Movie();
//            movie.setDirector("aaaa");
//            movie.setActor("bbbb");
//            movie.setName("바람과 함께 사라지다");
//            movie.setPrice(10000);
//
//            em.persist(movie);
//
//            em.flush();
//            em.clear();
//
//            Movie findMovie = em.find(Movie.class, movie.getId());
//            System.out.println("findMovie = " + findMovie.getName());
//            Member member = new Member();
//
//            member.setUsername("user1");
//            member.setCreateBy("kim");
//            member.setCreatedDate(LocalDateTime.now());
//
//            em.persist(member);
//
//            em.flush();
//            em.clear();
//            Child child1 = new Child();
//            Child child2 = new Child();
//
//            Parent parent = new Parent();
//            parent.addChild(child1);
//            parent.addChild(child2);
//
////            em.persist(parent);
////            em.persist(child1);
////            em.persist(child2);
//
////            @OneToMany(mappedBy = "parent",cascade = CascadeType.ALL)
//            em.persist(parent);
//            // child 가 같이 insert 쿼리가 나간다.
//
//            em.flush();
//            em.clear();
//
//            Parent findParent = em.find(Parent.class, parent.getId());
//            findParent.getChildList().remove(0);
//            Hibernate:
//            /* delete hellojpa.Child */ delete
//                    from
//            Child
//                    where
//            id=?

//            Address address = new Address("city", "street", "10000");
//
//            Member member1 = new Member();
//            member1.setUsername("member1");
//            member1.setHomeAddress(address);
//            em.persist(member1);
//
//            Member member2 = new Member();
//            member2.setUsername("member2");
//            member2.setHomeAddress(address);
//            em.persist(member2);
//
////            member1.getHomeaddress().setCity("newCity");
////            // 이거 자체가 불가능해짐.
//
//            // member1 의 address 를 가져와서 city 값을 변경했는데 member2 의 값도 변경되었다.
//            // 해결법
//            Address address1 = new Address(address.getCity(), address.getStreet(), address.getZipcode());
            // 새로운 address 를 만들어서 객체별로 다른 address 를 넣어주어야한다.
        // 기본 타입은 값을 복사하지만 객체는 참조를 전달하기 때문에 이런 문제가 발생.
            // 이를 해결하려면 객체를 생성자로만 값을 설정하고 수정자 (Setter) 를 만들지 않는 불변객체로 만들어야한다.
            // 값타입 비교는 == , 동등성 비교는 equals


            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("city","street","10000"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("고기");

            member.getAddressHistory().add(new AddressEntity("city1", "street1", "10001"));
            member.getAddressHistory().add(new AddressEntity("city2", "street2", "10002"));

            em.persist(member);

            em.flush();
            em.clear();
            System.out.println("============================================");
            Member findMember = em.find(Member.class, member.getId());
            // member 에 대한 select 문만 보낸다.
            // 콜렉션은 지연로딩임 .

            List<AddressEntity> addressHistory = findMember.getAddressHistory();

            for (AddressEntity address : addressHistory) {
                System.out.println("address = " + address.getAddress().getCity());
            }

            // 지연로딩임을 확인하기 위한 출력.
            // 출력문이 실행되면서 address 를 조회한다.

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }



        em.close();
        emf.close();
    }
}

// 1. emf = Persistence.createEntityManagerFactory(persistence.xml 에서 만든 이름);
// 2. em = emf.createEntityManager();
// 이상태로 암만 올려봐야 안올라감 왜 와이
// 트랜잭션 설정을 하지 않아서 .
// 1. 트랜잭션 tx = em.getTransaction();
// 2. 시작 tx.begin();
// 3. 끝  tx.commit();
// JPA 영속성의 장점으로는 지연을 통해 최적화 여지를 둘수있다. 1차 캐시(영속성 컨텍스트)에 저장해놓고 이를 commit (flush) 할때 DB에 쿼리를 만들어 보내기 때문.

// flush 하는방법
// 1. em.flush
// 2. tx.commit
// 3. JPQL 쿼리실

// 준영속 상태로 만드는법
// 1. em.detach
// 2. em.clear()  -> 영속성컨텍스트를 비움.
// 3. em.close    -> 영속성컨텍스트를 종료.

// 컬렉션 Set List Map 을 저장하는법
// @ElementCollection , @CollectionTable 을 사용
// DB 에는 컬렉션을 같은 테이블에 저장할수 없다.
// 다대일 관계로 따로 테이블을 저장.