package common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LINUtil {
	public static int parseInt(String s, int defaultValue) {
		try {
			return Integer.parseInt(s);
		} catch (Exception e) {
			return defaultValue;
		}

	}

	public static boolean isBank(String s) {
		if (s == null || s.length() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 随机指定范围内N个不重复的数 最简单最基本的方法
	 * 
	 * @param min
	 *            指定范围最小值
	 * @param max
	 *            指定范围最大值
	 * @param n
	 *            随机数个数
	 */
	public static int[] randomCommon(int min, int max, int n) {
		if (n > (max - min + 1) || max < min) {
			return null;
		}
		int[] result = new int[n];
		int count = 0;
		while (count < n) {
			int num = (int) (Math.random() * (max - min)) + min;
			boolean flag = true;
			for (int j = 0; j < n; j++) {
				if (num == result[j]) {
					flag = false;
					break;
				}
			}
			if (flag) {
				result[count] = num;
				count++;
			}
		}
		return result;
	}

	private static int ID_LENGTH = 17;

	public static boolean isID(String idNum) throws Exception {
		// 系数列表
		int[] ratioArr = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };

		// 校验码列表
		char[] checkCodeList = { '1', '0', 'X', '9', '8', '7', '6', '5', '4',
				'3', '2' };

		// 获取身份证号字符数组
		char[] cIds = idNum.toCharArray();

		// 获取最后一位（身份证校验码）
		char oCode = cIds[ID_LENGTH];
		int[] iIds = new int[ID_LENGTH];
		int idSum = 0;// 身份证号第1-17位与系数之积的和
		int residue = 0;// 余数(用加出来和除以11，看余数是多少？)
		for (int i = 0; i < ID_LENGTH; i++) {
			iIds[i] = cIds[i] - '0';
			idSum += iIds[i] * ratioArr[i];
		}
		residue = idSum % 11;// 取得余数
		return Character.toUpperCase(oCode) == checkCodeList[residue];
	}

	/**
	 * 判断是否为电话号码
	 * 
	 * @param tel
	 * @return
	 */
	public static boolean isTel(String tel) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(tel);
		return m.matches();
	}

	/**
	 * 将选择的答案与数据库统一
	 * 
	 * @param answer
	 * @return
	 */
	public static String format2sql(String answer) {
		String result = "";
		switch (answer) {
		case "1, 2":
			result = "7";
			break;
		case "1, 3":
			result = "8";
			break;
		case "1, 4":
			result = "9";
			break;
		case "2, 3":
			result = "10";
			break;
		case "2, 4":
			result = "11";
			break;
		case "3, 4":
			result = "12";
			break;
		case "1, 2, 3":
			result = "13";
			break;
		case "1, 2, 4":
			result = "14";
			break;
		case "1, 3, 4":
			result = "15";
			break;
		case "2, 3, 4":
			result = "16";
			break;
		case "1, 2, 3, 4":
			result = "17";
			break;
		default:
			result = answer;
			break;
		}
		return result;
	}

	public static String format2v(String answer) {
		String result = "";
		switch (answer) {
		case "7":
			result = "1, 2";
			break;
		case "8":
			result = "1, 3";
			break;
		case "9":
			result = "1, 4";
			break;
		case "10":
			result = "2, 3";
			break;
		case "11":
			result = "2, 4";
			break;
		case "12":
			result = "3, 4";
			break;
		case "13":
			result = "1, 2, 3";
			break;
		case "14":
			result = "1, 2, 4";
			break;
		case "15":
			result = "1, 3, 4";
			break;
		case "16":
			result = "2, 3, 4";
			break;
		case "17":
			result = "1, 2, 3, 4";
			break;
		default:
			result = answer;
			break;
		}
		return result;
	}
}
