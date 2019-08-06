package function;



import dto.PaginationQueryResultDto;
import entity.QuestionEntity;
import entity.UserEntity;

public interface UserFunction {
	UserEntity login(String account,String password)throws Exception;
	PaginationQueryResultDto<QuestionEntity> findStudentByKey(String key,String pageSize,String pageNo)throws Exception;
}
