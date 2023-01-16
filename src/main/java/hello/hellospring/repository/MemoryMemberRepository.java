package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.*;


public class MemoryMemberRepository implements  MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        //NULL값이더라도 Optional.ofNullable()로 묶으면 클라이언트에서 뭘 할 수 가 있다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
        //루프로 돌면서 맴버에서 getName와 name와 같은지 확인 하나라도 같은면 그 것을 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    public void clearStore()
    {
        store.clear();
    }
}
