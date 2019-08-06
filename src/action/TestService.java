package action;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.LINUtil;

import dao.HibernateDao;
import entity.QuestionEntity;

@Service
@Transactional
public class TestService {

	@Autowired
	private HibernateDao dao;
	public void modify(){
		StringBuilder hql = new StringBuilder();
		hql.append("from ").append(QuestionEntity.class.getName()).append(" where subject = '4'");
		try {
			List<QuestionEntity> list = dao.selectAll(hql);
			System.out.println(list.toString());
			for (int i = 0,len = list.size(); i < len; i++) {
				QuestionEntity exam = list.get(i);
				if(Integer.parseInt(exam.getAnswer())>4){
					exam.setAnswer(LINUtil.format2v(exam.getAnswer()));
					dao.saveOrUpdate(exam);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
