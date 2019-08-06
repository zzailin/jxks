package action.admin;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.opensymphony.xwork2.ActionSupport;

import common.LINException;
import dto.PaginationQueryResultDto;
import entity.QuestionEntity;
import function.AdminFunction;

/**
 * 新增考试题目 主要是上传图片问题
 * 分页查询考试题目
 * 
 * @author LIN
 *
 */
@Controller
@ParentPackage("struts-default")
@Namespace("/")
public class QuestionAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private AdminFunction function;

	private String question;
	private String item1;
	private String item2;
	private String item3;
	private String item4;
	private String answer;
	private String explains;
	private String subject;
	private File image;
	private String imageFileName;
	private String imageContentType;

	@Action(value = "addQuestion", results = { @Result(name = "success", location = "/jsp/admin/addQuestion.jsp") })
	public String addQuestion() {
		Map<String, Object> session = ServletActionContext.getContext()
				.getSession();
		String url = savePicture();
		QuestionEntity exam = new QuestionEntity();
		exam.setAnswer(HtmlUtils.htmlEscape(answer));
		exam.setExplains(HtmlUtils.htmlEscape(explains));
		exam.setItem1(HtmlUtils.htmlEscape(item1));
		exam.setItem2(HtmlUtils.htmlEscape(item2));
		exam.setItem3(HtmlUtils.htmlEscape(item3));
		exam.setItem4(HtmlUtils.htmlEscape(item4));
		exam.setQuestion(HtmlUtils.htmlEscape(question));
		exam.setUrl(url);
		exam.setSubject(subject);
		try {
			function.addQuestion(exam);
			session.put("message", "添加成功！");
		} catch (LINException lin) {
			session.put("message", lin.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	private String savePicture() {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (imageFileName != null && imageFileName.length() != 0) {
			// 保存到根目录下的uploadImages文件夹下
			String realPath = ServletActionContext.getServletContext()
					.getRealPath("/img/uploadImages");
			// System.out.println("realPath:" + realPath);
			Random random = new Random(99999);
			int tempInt = random.nextInt();
			Date datenew = new Date();
			SimpleDateFormat simpleDateFormatnew = new SimpleDateFormat(
					"yyyyMMddhhmmss");
			int last = imageFileName.lastIndexOf(".");
			String type = imageFileName.substring(last);
			imageFileName = simpleDateFormatnew.format(datenew) + tempInt
					+ type;
			// System.out.println("新的文件名称是：" + imageFileName);
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
			String picturePath = basePath + "img/uploadImages" + "/"
					+ imageFileName;
			// System.out.println("图片现在地址:" + picturePath);

			// 创建父文件夹
			if (image != null) {
				File saveFile = new File(new File(realPath), imageFileName);
				if (!saveFile.getParentFile().exists()) { // 如果Images文件夹不存在
					saveFile.getParentFile().mkdirs(); // 则创建新的多级文件夹
				}
				try {
					FileUtils.copyFile(image, saveFile); // 保存文件
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return picturePath;
		}
		return "";
	}

	private String key;
	private String pageNo;
	private String pageSize;
	@Action(value="queryQuestion",results={
			@Result(name="success",location="/jsp/admin/queryQuestion.jsp")
	})
	public String queryQuestion(){
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			PaginationQueryResultDto<QuestionEntity> result = function
					.findQuestionByKey(key, pageSize, pageNo);
			request.setAttribute("result", result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	private String id;
	@Action(value="checkQuestion",results={
			@Result(name="success",location="/jsp/admin/modifyQuestion.jsp")
	})
	public String checkQuestion(){
		Map<String, Object> session = ServletActionContext.getContext()
				.getSession();
		try {
			QuestionEntity questionEntity =function.checkQuestion(id);
			System.out.println(questionEntity.toString());
			session.put("questionEntity", questionEntity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	@Action(value="modifyQuestion",results={
			@Result(name="success",location="/jsp/admin/modifyQuestion.jsp"),
			@Result(name="error",location="/jsp/admin/modifyQuestion.jsp")
	})
	public String modifyQuestion(){
		Map<String, Object> session = ServletActionContext.getContext()
				.getSession();
		
		String url = savePicture();
		QuestionEntity exam = new QuestionEntity();
		exam.setId(Integer.parseInt(id));
		exam.setAnswer(HtmlUtils.htmlEscape(answer));
		exam.setExplains(HtmlUtils.htmlEscape(explains));
		exam.setItem1(HtmlUtils.htmlEscape(item1));
		exam.setItem2(HtmlUtils.htmlEscape(item2));
		exam.setItem3(HtmlUtils.htmlEscape(item3));
		exam.setItem4(HtmlUtils.htmlEscape(item4));
		exam.setQuestion(HtmlUtils.htmlEscape(question));
		exam.setUrl(url);
		exam.setSubject(subject);
		try{
			QuestionEntity questionEntity = function.modifyQuestion(exam);
			session.put("questionEntity", questionEntity);
			session.put("message","修改成功!");
		}catch(LINException lin){
			session.put("message", lin.getMessage());
			return ERROR;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}
	@Action(value="deleteQuestion",results={
			@Result(name="success",location="/queryQuestion.action",type="redirect")
	})
	public String deleteQuestion(){
	Map<String, Object> session = ServletActionContext.getContext().getSession();
		try {
			function.deleteQuestion(id);
			session.put("message", "删除成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getItem1() {
		return item1;
	}

	public void setItem1(String item1) {
		this.item1 = item1;
	}

	public String getItem2() {
		return item2;
	}

	public void setItem2(String item2) {
		this.item2 = item2;
	}

	public String getItem3() {
		return item3;
	}

	public void setItem3(String item3) {
		this.item3 = item3;
	}

	public String getItem4() {
		return item4;
	}

	public void setItem4(String item4) {
		this.item4 = item4;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getExplains() {
		return explains;
	}

	public void setExplains(String explains) {
		this.explains = explains;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public AdminFunction getFunction() {
		return function;
	}

	public void setFunction(AdminFunction function) {
		this.function = function;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}
