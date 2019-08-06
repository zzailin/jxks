package function;

import dto.PaginationQueryResultDto;
import entity.QuestionEntity;
import entity.UserEntity;

public interface AdminFunction {
	/**
	 * 分页查询考生信息
	 * @param key
	 * @param pageSize
	 * @param pageNo
	 * @return
	 * @throws Exception
	 */
PaginationQueryResultDto<UserEntity> findExamerByKey(String key,String pageSize,String pageNo)throws Exception;

/**
 * 分页查询试题信息
 * @param key
 * @param pageSize
 * @param pageNo
 * @return
 * @throws Exception
 */
PaginationQueryResultDto<QuestionEntity> findQuestionByKey(String key,String pageSize,String pageNo)throws Exception;
/**
 * 管理员修改自己信息
 * @param user
 * @return
 * @throws Exception
 */
UserEntity modifyPersonInfo(UserEntity user)throws Exception;
/**
 * 添加考生
 * @param user
 * @throws Exception
 */
void addExamer(UserEntity user)throws Exception;
/**
 * 管理员修改自己密码
 * @param id
 * @param password
 */
void modifyPassword(String id,String password,String newPassword)throws Exception;
/**
 * 添加试题
 * @param exam
 * @throws Exception
 */
void addQuestion(QuestionEntity exam)throws Exception;
/**
 * 添加管理员
 * @throws Exception
 */
void addAdmin(UserEntity user)throws Exception;
/**
 * 通过id查询到考生 传去修改
 * @param id
 * @return
 * @throws Exception
 */
UserEntity checkExamer(String id)throws Exception;
/**
 * 修改考生信息
 * @param user
 * @return
 * @throws Exception
 */
UserEntity modifyExamer(UserEntity user)throws Exception;
/**
 * 通过id查试题
 * @param id
 * @return
 * @throws Exception
 */
QuestionEntity checkQuestion(String id)throws Exception;
/**
 * 修改题目信息
 * @param questionEntity
 * @return
 * @throws Exception
 */
QuestionEntity modifyQuestion(QuestionEntity questionEntity)throws Exception;
/**
 * 分页查询管理员信息
 * @param key
 * @param pageSize
 * @param pageNo
 * @return
 * @throws Exception
 */
PaginationQueryResultDto<UserEntity> findAdminByKey(String key,String pageSize,String pageNo)throws Exception;
/**
 * 删除普通管理员
 * @param id
 * @return
 * @throws Exception
 */
boolean deleteAdmin(String id)throws Exception;
/**
 * 删除试题
 * @param id
 * @return
 * @throws Exception
 */
boolean deleteQuestion(String id)throws Exception;
}
