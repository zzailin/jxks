package function.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.AssertUtil;

import dao.HibernateDao;
import dto.PaginationQueryResultDto;
import entity.QuestionEntity;
import entity.UserEntity;
import function.UserFunction;
@Service
@Transactional
public class UserFunctionImpl implements UserFunction{
	@Autowired
	protected HibernateDao dao;
	@Override
	public UserEntity login(String account, String password) throws Exception {
		// TODO Auto-generated method stub
		AssertUtil.assertNotBlank("用户名不能为空", account);
		AssertUtil.assertNotBlank("密码不能为空", password);
		UserEntity u = dao.getByUK(UserEntity.class, "account", account);
		AssertUtil.assertNotNull("账户不存在",u);
		AssertUtil.assertEquals("密码不匹配", password, u.getPassword());
		return u;
		
//		UserEntity u = new UserEntity();
//		u.setAccount(account);
//		u.setPassword(password);
//		u.setCreateTime(new Date());
//		
//		
//		UserInfoEntity ui = new UserInfoEntity();
//		ui.setSubject("1");
//		ui.setExaminationTime(new Date());
//		ui.setUser(u);
//		
//		dao.save(u);
//		dao.save(ui);
//		return null;
	}

	@Override
	public PaginationQueryResultDto<QuestionEntity> findStudentByKey(String key,
			String pageSize, String pageNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
