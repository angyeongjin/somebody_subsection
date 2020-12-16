package com.ssafy.somebody.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssafy.somebody.dao.MemberDao;
import com.ssafy.somebody.service.MemberService;
import com.ssafy.somebody.vo.Members;
import com.ssafy.somebody.vo.Verify;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDao memberDao;
	@Override
	public int insertMember(Members member) {
		return memberDao.insertMember(member);
	}
	@Override
	public Members searchMember(String membersId) {
		return memberDao.searchMember(membersId);
	}
	@Override
	public List<Members> searchAllMember() {
		return memberDao.searchAllMember();
	}
	@Override
	public int updateMember(Members member) {
		return memberDao.updateMember(member);
	}
	@Override
	public int deleteMember(String memberId) {
		return memberDao.deleteMember(memberId);
	}
	@Override
	public Members passCheck(String membersId, String password) {
		return memberDao.passCheck(membersId, password);
	}
	@Override
	public int insertVerify(Verify verify) {
		return memberDao.insertVerify(verify);
	}
	@Override
	public int updateVerify(Verify verify) {
		return memberDao.updateVerify(verify);
	}
	@Override
	public Verify searchVerify(String email) {
		return memberDao.searchVerify(email);
	}
	@Override
	public Verify checkVerify(Verify verify) {
		return memberDao.checkVerify(verify);
	}
	@Override
	public Members searchMemberByEmail(String email) {
		return memberDao.searchMemberByEmail(email);
	}
	@Override
	public int updatePassword(Members members) {
		return memberDao.updatePassword(members);
	}
	@Override
	public int updateRank(Members members) {
		return memberDao.updateRank(members);
	}
}