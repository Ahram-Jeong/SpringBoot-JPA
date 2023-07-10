package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor // 스프링 JPA에서 EntityManager를 Autowired로도 인젝션 할 수 있게 지원해주기 때문에 해당 어노테이션 사용 가능, 일반 스프링은 아직 불가능
public class MemberRepository {
//    @PersistenceContext
//    private EntityManager em;

    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id); // (타입, pk)
    }
    
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList(); // createQuery(JPQL, 반환타입)
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name).getResultList();
    }
}
