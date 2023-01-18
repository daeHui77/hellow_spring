package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.*;


public class MemberService {
    private  final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    /**
     * 회원가입
     */
    public Long join(Member member) {
        //같은 이름이 있는 중복 회원X
        /*Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m ->{
            throw new IllegalStateException("이미 존재하는  회원입니다.");
        });*/
        //Null일 가능성이 있으면 Optional로 한번 감싸서 코딩을 해준다.
        validateDuplicateMember(member);//중복 회원 검증
        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m ->{
                throw new IllegalStateException("이미 존재하는  회원입니다.");
        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
