package function.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.AssertUtil;
import common.LINException;
import common.LINUtil;
import dao.HibernateDao;
import dto.PaginationQueryResultDto;
import entity.QuestionEntity;
import entity.UserEntity;
import entity.UserInfoEntity;
import function.AdminFunction;
@Service
@Transactional
public class AdminFunctionImpl implements AdminFunction{
	@Autowired
	private HibernateDao dao;
	@Override
	public PaginationQueryResultDto<UserEntity> findExamerByKey(String key,
			String pageSize, String pageNo) throws Exception {
		// TODO Auto-generated method stub
		// 验证参数
				if (key != null && (key = key.trim()).length() == 0) {
					key = null;
				}
				int pageSizeInt = LINUtil.parseInt(pageSize, 6);
				int pageNoInt = LINUtil.parseInt(pageNo, 1);
				Map<String, Object> params = new HashMap<String, Object>();
				if (key != null) {
					params.put("key", "%"+key+"%");
				}
				// 业务逻辑
				StringBuilder hql = new StringBuilder();
				hql.append("from ").append(UserEntity.class.getName()).append(" where role='0' ");
				if (key != null) {
					hql.append("and name like :key or account like :key");
				}
				Number total = dao.selectOne("select count(*) " + hql, params);
				// 组装业务结果
				PaginationQueryResultDto dto = new PaginationQueryResultDto<>();
				dto.setPageNo(pageNoInt);
				dto.setPageSize(pageSizeInt);
				dto.setTotalRows(total.intValue());
				dto.setKey(key);
				if (total.intValue() != 0) {
					// 执行分页查询
					List<UserEntity> pageList = dao.selectPagination(pageNoInt,
							pageSizeInt, hql, params);
					dto.setRows(pageList);
				}
				return dto;
	}
	@Override
	public UserEntity modifyPersonInfo(UserEntity user) throws Exception {
		// TODO Auto-generated method stub
		AssertUtil.assertNotBlank("姓名不能为空！", user.getName());
		AssertUtil.assertNotBlank("电话不能为空！", user.getTel());
		AssertUtil.asserIsTel("电话号码不正确！", user.getTel());
		UserEntity u = dao.getByUK(UserEntity.class, "id", user.getId());
		u.setName(user.getName());
		u.setTel(user.getTel());
		dao.saveOrUpdate(u);
		return u;
	}
	@Override
	public void addExamer(UserEntity user) throws Exception {
		// TODO Auto-generated method stub
		
		AssertUtil.assertNotBlank("账号不能为空！", user.getAccount());
		AssertUtil.assertIsID("身份证号码错误！", user.getAccount());
		AssertUtil.assertNotBlank("姓名不能为空！", user.getName());
		AssertUtil.assertNotBlank("电话不能为空！", user.getTel());
		AssertUtil.asserIsTel("手机号码错误！", user.getTel());

		user.setPassword(user.getAccount().substring(12));
		UserInfoEntity userInfo = new UserInfoEntity();
		user.setUserInfo(userInfo);
		boolean result = dao.exists(UserEntity.class, "account", user.getAccount());
		if(result){
			throw new LINException("账户已存在!");
		}else
		dao.save(user.getUserInfo());
		dao.save(user);
	}
	@Override
	public void modifyPassword(String id, String password,String newPassword) throws Exception{
		// TODO Auto-generated method stub
		AssertUtil.assertNotBlank("新密码不能为空", newPassword);
		UserEntity user = dao.getByUK(UserEntity.class, "id", id);
		AssertUtil.assertEquals("密码错误！", password, user.getPassword());
		user.setPassword(newPassword);
		dao.update(user);
	}
	@Override
	public void addQuestion(QuestionEntity exam) throws Exception {
		// TODO Auto-generated method stub
		AssertUtil.assertNotBlank("题目不能为空！", exam.getQuestion());
		AssertUtil.assertNotBlank("至少应该存在两个选项！", exam.getItem1());
		AssertUtil.assertNotBlank("至少应该存在两个选项！", exam.getItem2());
		AssertUtil.assertNotBlank("请选择正确的答案!", exam.getItem3());
		AssertUtil.assertNotBlank("请输入答案解析！", exam.getExplains());
		dao.save(exam);
	}
	@Override
	public void addAdmin(UserEntity user) throws Exception {
		// TODO Auto-generated method stub
		AssertUtil.assertNotBlank("账号不能为空！", user.getAccount());
		AssertUtil.assertNotBlank("姓名不能为空！", user.getName());
		AssertUtil.assertNotBlank("电话不能为空！", user.getTel());
		AssertUtil.asserIsTel("电话号码不对！", user.getTel());
		dao.save(user);
	}
	@Override
	public PaginationQueryResultDto<QuestionEntity> findQuestionByKey(String key,
			String pageSize, String pageNo) throws Exception {
		// TODO Auto-generated method stub
		// 验证参数
		if (key != null && (key = key.trim()).length() == 0) {
			key = null;
		}
		int pageSizeInt = LINUtil.parseInt(pageSize, 5);
		int pageNoInt = LINUtil.parseInt(pageNo, 1);
		Map<String, Object> params = new HashMap();
		if (key != null) {
			params.put("key", "%"+key+"%");
		}
		// 业务逻辑
		StringBuilder hql = new StringBuilder();
		hql.append("from ").append(QuestionEntity.class.getName());
		if (key != null) {
			hql.append(" where ").append("question like :key or item1 like :key or item2 like :key or item3 like :key or subject like :key");
		}
		Number total = dao.selectOne("select count(*) " + hql, params);
		// 组装业务结果
		PaginationQueryResultDto dto = new PaginationQueryResultDto<>();
		dto.setPageNo(pageNoInt);
		dto.setPageSize(pageSizeInt);
		dto.setTotalRows(total.intValue());
		dto.setKey(key);
		if (total.intValue() != 0) {
			// 执行分页查询
			List<UserEntity> pageList = dao.selectPagination(pageNoInt,
					pageSizeInt, hql, params);
			dto.setRows(pageList);
		}
		return dto;
	}
	@Override
	public UserEntity checkExamer(String id) throws Exception {
		// TODO Auto-generated method stub
			UserEntity user = dao.getByUK(UserEntity.class, "id", id);
		return user;
	}
	@Override
	public UserEntity modifyExamer(UserEntity user) throws Exception {
		// TODO Auto-generated method stub
		AssertUtil.assertNotBlank("姓名不能为空！", user.getName());
		AssertUtil.assertNotBlank("电话号码不能为空！", user.getTel());
		AssertUtil.asserIsTel("电话号码格式不正确", user.getTel());
		AssertUtil.assertNotBlank("密码不能为空！", user.getPassword());
		UserEntity newUser = dao.getByUK(UserEntity.class, "id", user.getId());
		newUser.setName(user.getName());
		newUser.setPassword(user.getPassword());
		newUser.setTel(user.getTel());
		dao.saveOrUpdate(newUser);
		return newUser;
	}
	@Override
	public QuestionEntity checkQuestion(String id) throws Exception {
		// TODO Auto-generated method stub
		QuestionEntity question = dao.getByUK(QuestionEntity.class, "id", Integer.parseInt(id));
		return question;
	}
	@Override
	public QuestionEntity modifyQuestion(QuestionEntity questionEntity)
			throws Exception {
		// TODO Auto-generated method stub
		AssertUtil.assertNotBlank("题目不能为空！", questionEntity.getQuestion());
		AssertUtil.assertNotBlank("至少应该存在两个选项！", questionEntity.getItem1());
		AssertUtil.assertNotBlank("至少应该存在两个选项！", questionEntity.getItem2());
		AssertUtil.assertNotBlank("请选择正确的答案!", questionEntity.getItem3());
		AssertUtil.assertNotBlank("请输入答案解析！", questionEntity.getExplains());
		
		QuestionEntity question = dao.getByUK(QuestionEntity.class, "id", questionEntity.getId());
		if(LINUtil.isBank(questionEntity.getUrl())){
			questionEntity.setUrl(question.getUrl());
		}
		dao.clear();
		dao.update(questionEntity);
		return questionEntity;
	}
	@Override
	public PaginationQueryResultDto<UserEntity> findAdminByKey(String key,
			String pageSize, String pageNo) throws Exception {
		// TODO Auto-generated method stub
		if (key != null && (key = key.trim()).length() == 0) {
			key = null;
		}
		int pageSizeInt = LINUtil.parseInt(pageSize, 6);
		int pageNoInt = LINUtil.parseInt(pageNo, 1);
		Map<String, Object> params = new HashMap();
		if (key != null) {
			params.put("key", "%"+key+"%");
		}
		// 业务逻辑
		StringBuilder hql = new StringBuilder();
		hql.append("from ").append(UserEntity.class.getName()).append(" where role='1' ");
		if (key != null) {
			hql.append("and name like :key or account like :key");
		}
		Number total = dao.selectOne("select count(*) " + hql, params);
		// 组装业务结果
		PaginationQueryResultDto dto = new PaginationQueryResultDto<>();
		dto.setPageNo(pageNoInt);
		dto.setPageSize(pageSizeInt);
		dto.setTotalRows(total.intValue());
		dto.setKey(key);
		if (total.intValue() != 0) {
			// 执行分页查询
			List<UserEntity> pageList = dao.selectPagination(pageNoInt,
					pageSizeInt, hql, params);
			dto.setRows(pageList);
		}
		return dto;
	}
	@Override
	public boolean deleteAdmin(String id) throws Exception {
		// TODO Auto-generated method stub
		UserEntity admin = dao.getByUK(UserEntity.class, "id", id);
		dao.delete(admin);
		return true;
	}
	@Override
	public boolean deleteQuestion(String id) throws Exception {
		// TODO Auto-generated method stub
		QuestionEntity question = dao.getByUK(QuestionEntity.class, "id", Integer.parseInt(id));
		dao.delete(question);
		return true;
	}
	
}
