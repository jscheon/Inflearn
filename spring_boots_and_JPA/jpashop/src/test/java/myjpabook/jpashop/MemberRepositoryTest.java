package myjpabook.jpashop;

import myjpabook.jpashop.domain.Member;
import myjpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void testMember(){
        Member member = new Member();
        member.setName("천지석");

        Long saveId = memberRepository.save(member);

        Member findMember = memberRepository.findOne(saveId);

        assertThat(findMember.getId()).isEqualTo(saveId);

        assertThat(findMember).isEqualTo(member);
    }

}