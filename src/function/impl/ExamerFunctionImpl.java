package function.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.LINUtil;
import dao.HibernateDao;
import dto.PaginationQueryResultDto;
import entity.QuestionEntity;
import entity.UserEntity;
import entity.UserInfoEntity;
import function.ExamerFunction;

@Service
@Transactional
public class ExamerFunctionImpl implements ExamerFunction {
	@Autowired
	private HibernateDao dao;

	public PaginationQueryResultDto<QuestionEntity> findStudentByKey(String key,
			String pageSize, String pageNo) throws Exception {
		// TODO Auto-generated method stub
		// 验证参数
		if (key != null && (key = key.trim()).length() == 0) {
			key = null;
		}
		int pageSizeInt = 5;
		int pageNoInt = 1;
		Map<String, Object> params = new HashMap();
		if (key != null) {
			params.put("key", key);
		}
		// 业务逻辑
		StringBuilder hql = new StringBuilder();
		hql.append("from ").append(QuestionEntity.class.getName());
		if (key != null) {
			hql.append(" where ").append("question like :key");
		}
		Number total = dao.selectOne("select count(*) " + hql, params);
		// 组装业务结果
		PaginationQueryResultDto dto = new PaginationQueryResultDto<>();
		dto.setPageNo(pageNoInt);
		dto.setPageSize(pageSizeInt);
		dto.setTotalRows(total.intValue());
		if (total.intValue() != 0) {
			// 执行分页查询
			List<QuestionEntity> pageList = dao.selectPagination(pageNoInt,
					pageSizeInt, hql, params);
			dto.setRows(pageList);
		}
		return dto;
	}

	@Override
	public List<QuestionEntity> queryExam(int examNum,String subject) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder hql = new StringBuilder();
		// String str = ExamEntity.class.getName();
		// String str1 = "FROM ";
		// String str2 =
		// " AS t1 JOIN fetch (SELECT ROUND(RAND() * ((SELECT MAX(id) FROM ";
		// String str3 = ")-(SELECT MIN(id) FROM ";
		// String str4 = "))+(SELECT MIN(id) FROM ";
		// String str5
		// =")) AS id) AS t2 WHERE t1.id >= t2.id  ORDER BY t1.id limit 1";
		// hql.append(str1).append(str).append(str2).append(str).append(str3).append(str).append(str4).append(str).append(str5);
		// hql.append("select top 1 * from (select top 2 * FROM ").append(ExamEntity.class.getName()).append(" order by ID asc)as A order by A.ID desc");
		hql.append("select count(*) from ").append(QuestionEntity.class.getName()).append(" where subject =").append(subject);
		List<QuestionEntity> list = new ArrayList<QuestionEntity>(100);
		Number total = dao.selectOne(hql);
		
		StringBuilder listHql = new StringBuilder();
		listHql.append("from ").append(QuestionEntity.class.getName()).append(" where subject = ").append(subject);
		List<QuestionEntity> listAll = dao.selectAll(listHql);

		int[] num = LINUtil.randomCommon(1, total.intValue(), examNum);
		for (int i = 0,len=num.length; i < len; i++) {
			list.add(listAll.get(num[i]));
		}
		return list;
	}

	@Override
	public void saveScore(int score, int total,UserEntity user) throws Exception {
		// TODO Auto-generated method stub
		int lastScore = 0;
		if("1".equals(user.getUserInfo().getSubject())){
			lastScore = user.getUserInfo().getScore1();
			if(score>lastScore){
				user.getUserInfo().setScore1(score);
				user.getUserInfo().setTime1(new Date());
				if(score/total>0.9){
					user.getUserInfo().setSubject("4");
				}
			}
		}else if("4".equals(user.getUserInfo().getSubject())){
			lastScore = user.getUserInfo().getScore4();
			if(score>lastScore){
				user.getUserInfo().setScore4(score);
				user.getUserInfo().setTime4(new Date());
			}
		}
		dao.update(user.getUserInfo());
		dao.update(user);
	}
}
