package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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

            Movie movie = new Movie();
            movie.setDirector("aaaa");
            movie.setActor("bbbb");
            movie.setName("바람과 함께 사라지다");
            movie.setPrice(10000);

            em.persist(movie);

            em.flush();
            em.clear();

            Movie findMovie = em.find(Movie.class, movie.getId());
            System.out.println("findMovie = " + findMovie.getName());


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