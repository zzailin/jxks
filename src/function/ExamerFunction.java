package function;

import java.util.List;

import entity.QuestionEntity;
import entity.UserEntity;

public interface ExamerFunction{
	/**
	 * 查询出考试所需题目存放在list中
	 * @param examNum 所随机查询的题目数 subject 科目类型
	 * @return
	 * @throws Exception
	 */
public List<QuestionEntity> queryExam(int examNum,String subject) throws Exception;
/**
 * 保存成绩
 * @param score
 * @param user
 * @param total
 * @throws Exception
 */
void saveScore(int score,int total,UserEntity user)throws Exception;
}
