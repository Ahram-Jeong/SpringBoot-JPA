package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // -> 읽기 전용 트랜잭션, JPA가 조회하는 곳에서는(readOnly = true) 성능 최적화. 읽기에는 가급적 넣어주는게 좋음
@RequiredArgsConstructor // 롬복 어노테이션, final이 붙은 필드를 가지고 생성자를 만들어 준다.
public class MemberService {
    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    @Transactional // JPA의 데이터 변경을 위함, 쓰기 전용 트랜잭션 (readOnly = false)
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 회원 단건 조회
    public  Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
