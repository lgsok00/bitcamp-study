package bitcamp.myapp.service;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.TransactionTemplete;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Service
public class DefaultMemberService implements MemberService {

  MemberDao memberDao;
  TransactionTemplete txTemplete;

  public DefaultMemberService(MemberDao memberDao, PlatformTransactionManager txManager) {
    this.memberDao = memberDao;
    txTemplete = new TransactionTemplete(txManager);
  }

  @Override
  public int add(Member member) throws Exception {
    return txTemplete.execute(status -> memberDao.insert(member));
  }

  @Override
  public List<Member> list() throws Exception {
    return memberDao.findAll();
  }

  @Override
  public Member get(int memberNo) throws Exception {
    return memberDao.findBy(memberNo);
  }

  @Override
  public Member get(String email, String password) throws Exception {
    return memberDao.findByEmailAndPassword(email, password);
  }

  @Override
  public int update(Member member) throws Exception {
    return txTemplete.execute(status -> memberDao.update(member));
  }

  @Override
  public int delete(int memberNo) throws Exception {
    return txTemplete.execute(status -> memberDao.delete(memberNo));
  }
}