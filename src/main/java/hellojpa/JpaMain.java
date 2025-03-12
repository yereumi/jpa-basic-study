package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            Member findMember1 = em.find(Member.class, 101L); // DB에서 조회
//            Member findMember2 = em.find(Member.class, 101L); // 1차 캐시에서 조회
//
//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L, "B");
//
//            em.persist(member1);
//            em.persist(member2);

            Member member = em.find(Member.class, 150L);
            member.setName("ZZZZZ"); // 변경 감지(Dirty Checking)
            // 변경 감지 원리
            // 1. flush()
            // 2. 1차 캐시 안의 엔티티와 스냅샷 비교
            //  스냅샷: 값을 조회한 시점의 상태
            // 3. 변경된 값이 있으면 UPDATE SQL 생성
            // 4. flush
            // 5. commit

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
