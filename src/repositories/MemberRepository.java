package repositories;

import entities.Member;
import java.util.ArrayList;
import java.util.List;

public class MemberRepository {
    private List<Member> members;

    public MemberRepository() {
        this.members = new ArrayList<>();
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public Member findMemberById(String memberId) {
        for (Member member : members) {
            if (member.getMemberId().equals(memberId)) {
                return member;
            }
        }
        return null;
    }

    public void updateMember(Member member) {
    }

    public void deleteMember(Member member) {
        members.remove(member);
    }

    public List<Member> getAllMembers() {
        return new ArrayList<>(members);
    }

    public boolean isMemberIdExists(String memberId) {
        return findMemberById(memberId) != null;
    }
}