package common;

public class AssertUtil {

	/**
	 * 断言不为空，长度不为0，否则抛异常
	 * @param message
	 * @param s
	 * @return s
	 */
	public static String assertNotBlank(String message,String s){
		if(s==null||(s=s.trim()).length()==0){
			throw new LINException(message);
		}
		return s;
	}
	public static String $(String message,String s){
		return assertNotBlank(message, s);
	}
	/**
	 * 断言a,b相等，不相等抛异常
	 * @param message
	 * @param a
	 * @param b
	 */
	public static void assertEquals(String message,Object a,Object b){
		if(!a.equals(b)){
			throw new LINException(message);
		}
	}
	/**
	 * 断言a,b不等,相等抛异常
	 * @param message
	 * @param a
	 * @param b
	 */
	public static void assertNotEquals(String message,Object a,Object b){
		if(a.equals(b)){
			throw new LINException(message);
		}
	}
	/**
	 * 断言b为真,不为真抛异常
	 * @param message
	 * @param b
	 */
	public static void assertTrue(String message,boolean b){
		if(!b){
			throw new LINException(message);
		}
	}
	/**
	 * 断言b不为空,为空抛异常
	 * @param message
	 * @param b
	 */
	public static void assertNotNull(String message,Object b){
		if(b==null){
			throw new LINException(message);
		}
	}
	/**
	 * 断言b为空,不为空抛异常
	 * @param message
	 * @param b
	 */
	public static void assertNull(String message,Object b){
		if(b!=null){
			throw new LINException(message);
		}
	}
	/**
	 * 断言b为假,为真抛异常
	 * @param message
	 * @param b
	 */
	public static void assertFalse(String message,boolean b){
		if(b){
			throw new LINException(message);
		}
	}
	
	public static void assertIsID(String message,String id){
		boolean result = false;
		try {
			result = LINUtil.isID(id);
		} catch (Exception e) {
			throw new LINException(message);
		}
		if(!result){
			throw new LINException(message);
		}
	}
	
	public static void asserIsTel(String message,String tel){
		if(!LINUtil.isTel(tel))
			throw new LINException(message);
	}
}
