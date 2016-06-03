/**
 * 模拟ALU进行整数和浮点数的四则运算
 * 
 * @author [151250121_桑田]
 *
 */

public class ALU {

	ALU myALU = new ALU();

	/**
	 * 定义与门，或门，异或门
	 * 
	 */
	private char and(char a, char b) {
		char result;
		if (a == '1' && b == '1') {
			result = '1';
		} else {
			result = '0';
		}
		return result;
	}

	private char or(char a, char b) {
		char result;
		if (a == '0' && b == '0') {
			result = '0';
		} else {
			result = '1';
		}
		return result;
	}

	private char xor(char a, char b) {
		char result;
		if (a == b) {
			result = '0';
		} else {
			result = '1';
		}
		return result;
	}

	/**
	 * 生成十进制整数的二进制补码表示。<br/>
	 * 例：integerRepresentation("9", 8)
	 * 
	 * @param number
	 *            十进制整数。若为负数；则第一位为“-”；若为正数或 0，则无符号位
	 * @param length
	 *            二进制补码表示的长度
	 * @return number的二进制补码表示，长度为length
	 */
	public String integerRepresentation(String number, int length) {
		// TODO YOUR CODE HERE.
		String result = "";
		if (number.startsWith("-")) {
			// 如果是负数，先算出正数部分
			String numberToexecute = number.substring(1);
			String myresult = "";
			int executeNumber = Integer.parseInt(numberToexecute);
			for (int i = 0; i < length; i++) {
				myresult += (executeNumber % 2);
				if (executeNumber != 0)
					executeNumber = executeNumber / 2;
			}
			myresult = new StringBuffer(myresult).reverse().toString();
			// 取反加1
			for (int i = myresult.length() - 1; i >= 0; i--) {
				if (myresult.charAt(i) == '1') {
					String toReverse = myresult.substring(0, i);
					for (int j = 0; j < toReverse.length(); j++) {
						if (toReverse.charAt(j) == '0') {
							result += "1";
						} else {
							result += "0";
						}
					}
					result += myresult.substring(i);
					break;
				}
			}

		} else {
			// 正数算法
			int executeNumber = Integer.parseInt(number);
			for (int i = 0; i < length; i++) {
				result += (executeNumber % 2);
				if (executeNumber != 0)
					executeNumber = executeNumber / 2;
			}
			result = new StringBuffer(result).reverse().toString();

		}
		return result;
	}

	/**
	 * 生成十进制浮点数的二进制表示。 需要考虑 0、反规格化、正负无穷（“+Inf”和“-Inf”）、 NaN等因素，具体借鉴 IEEE 754。
	 * 舍入策略为向0舍入。<br/>
	 * 例：floatRepresentation("11.375", 8, 11)
	 * 
	 * @param number
	 *            十进制浮点数，包含小数点。若为负数；则第一位为“-”；若为正数或 0，则无符号位
	 * @param eLength
	 *            指数的长度，取值大于等于 4
	 * @param sLength
	 *            尾数的长度，取值大于等于 4
	 * @return number的二进制表示，长度为 1+eLength+sLength。从左向右，依次为符号、指数（移码表示）、尾数（首位隐藏）
	 */
	public String floatRepresentation(String number, int eLength, int sLength) {
		// TODO YOUR CODE HERE.
		String result = "";
		String decIntegerPart = "";// 十进制整数部分
		String decDoublePart = "";// 十进制小数部分

		double decTrueValue = Double.parseDouble(number);
		// 如果传入数字为0
		if (decTrueValue == 0) {
			result += "0";
			for (int i = 0; i < eLength; i++) {
				result += "0";
			}
			for (int i = 0; i < sLength; i++) {
				result += "0";
			}
		} else if (decTrueValue > Math.pow(2, eLength - 1) * (2 - Math.pow(2, -sLength))) {
			// 正无穷
			result += "0";
			for (int i = 0; i < eLength; i++) {
				result += "1";
			}
			for (int i = 0; i < sLength; i++) {
				result += "0";
			}

		} else if (decTrueValue < -(Math.pow(2, eLength - 1) * (2 - Math.pow(2, -sLength)))) {
			// 负无穷
			result += "1";
			for (int i = 0; i < eLength; i++) {
				result += "1";
			}
			for (int i = 0; i < sLength; i++) {
				result += "0";
			}
		}

		// 得出符号位，并分出整数部分和小数部分
		if (number.startsWith("-")) {
			result += "1";
			decIntegerPart = number.substring(1).split("\\.")[0];
			decDoublePart = number.substring(1).split("\\.")[1];
		} else {
			result += "0";
			decIntegerPart = number.split("\\.")[0];
			decDoublePart = number.split("\\.")[1];
		}

		// 计算二进制整数部分和小数部分
		String binIntegerPart = "";
		String binDoublePart = "";

		binIntegerPart += Integer.parseInt(decIntegerPart) % 2;
		return result;
	}

	/**
	 * 生成十进制浮点数的IEEE 754表示，要求调用{@link #floatRepresentation(String, int, int)
	 * floatRepresentation}实现。<br/>
	 * 例：ieee754("11.375", 32)
	 * 
	 * @param number
	 *            十进制浮点数，包含小数点。若为负数；则第一位为“-”；若为正数或 0，则无符号位
	 * @param length
	 *            二进制表示的长度，为32或64
	 * @return number的IEEE 754表示，长度为length。从左向右，依次为符号、指数（移码表示）、尾数（首位隐藏）
	 */
	public String ieee754(String number, int length) {
		// TODO YOUR CODE HERE.
		String result = myALU.floatRepresentation(number, 8, 23);
		return result;
	}

	/**
	 * 计算二进制补码表示的整数的真值。<br/>
	 * 例：integerTrueValue("00001001")
	 * 
	 * @param operand
	 *            二进制补码表示的操作数
	 * @return operand的真值。若为负数；则第一位为“-”；若为正数或 0，则无符号位
	 */
	public String integerTrueValue(String operand) {
		// TODO YOUR CODE HERE.
		int result = 0;
		String trueValue = "";
		if (operand.startsWith("0")) {
			for (int i = 0; i < operand.length(); i++) {
				if (operand.charAt(i) == '1') {
					result += Math.pow(2, operand.length() - i - 1);
				}
			}
			trueValue = String.valueOf(result);
		} else {
			for (int i = 1; i < operand.length(); i++) {
				result += Math.pow(2, operand.length() - i - 1);
			}
			result -= Math.pow(2, operand.length() - 1);
			trueValue = String.valueOf(result);
		}

		return trueValue;
	}

	/**
	 * 计算二进制原码表示的浮点数的真值。<br/>
	 * 例：floatTrueValue("01000001001101100000", 8, 11)
	 * 
	 * @param operand
	 *            二进制表示的操作数
	 * @param eLength
	 *            指数的长度，取值大于等于 4
	 * @param sLength
	 *            尾数的长度，取值大于等于 4
	 * @return operand的真值。若为负数；则第一位为“-”；若为正数或 0，则无符号位。正负无穷分别表示为“+Inf”和“-Inf”，
	 *         NaN表示为“NaN”
	 */
	public String floatTrueValue(String operand, int eLength, int sLength) {
		// TODO YOUR CODE HERE.
		String result = "";
		int eLengthIs0 = 0, eLengthIs1 = 0, sLengthIs0 = 0, sLengthIs1 = 0;

		// 计算指数为0,1的个数
		for (int i = 1; i < eLength + 1; i++) {
			if (operand.charAt(i) == '0') {
				eLengthIs0++;
			} else if (operand.charAt(i) == '1') {
				eLengthIs1++;
			}
		}

		// 计算尾数为0,1的个数
		for (int i = eLength + 1; i < operand.length(); i++) {
			if (operand.charAt(i) == '0') {
				sLengthIs0++;
			} else if (operand.charAt(i) == '1') {
				sLengthIs1++;
			}
		}

		if (operand.startsWith("0") && eLengthIs1 == eLength && sLengthIs0 == sLength) {
			result = "+Inf";
		} else if (operand.startsWith("1") && eLengthIs1 == eLength && sLengthIs0 == sLength) {
			result = "-Inf";
		} else if (eLengthIs1 == eLength && sLengthIs0 != sLength) {
			result = "NaN";
		} else {
			String exponent = operand.substring(1, eLength + 1);// 指数部分
			String mantissa = operand.substring(eLength + 1);// 尾数部分
			int exponentOfInteger = 0;
			for (int i = 0; i < exponent.length(); i++) {
				if (exponent.charAt(i) == '1') {
					exponentOfInteger += Math.pow(2, exponent.length() - i - 1);
				}
			}
			exponentOfInteger -= Math.pow(2, eLength - 1) - 1;

			String integerPart = "";
			String doublePart = "";
			if (exponentOfInteger >= 0) {
				// 如果指数为正，分出整数和小数部分
				integerPart = "1" + mantissa.substring(0, exponentOfInteger);
				doublePart = mantissa.substring(exponentOfInteger);
			} else {
				// 如果指数为负，在前面添0
				String toAdd = "";
				for (int i = 1; i < (-exponentOfInteger); i++) {
					toAdd += "0";
				}
				doublePart = toAdd + "1" + mantissa;
			}

			System.out.println(doublePart);

			int integerResult = 0;
			double doubleResult = 0;
			// 计算整数部分
			for (int i = 0; i < integerPart.length(); i++) {
				if (integerPart.charAt(i) == '1') {
					integerResult += Math.pow(2, integerPart.length() - i - 1);
				}
			}
			// 计算小数部分
			for (int i = 0; i < doublePart.length(); i++) {
				if (doublePart.charAt(i) == '1') {
					doubleResult += Math.pow(0.5, i + 1);
				}
			}

			if (operand.startsWith("0")) {
				result = String.valueOf(integerResult) + "." + String.valueOf(doubleResult).substring(2);
			} else {
				result = "-" + String.valueOf(integerResult) + "." + String.valueOf(doubleResult).substring(2);
			}
		}
		return result;
	}

	/**
	 * 按位取反操作。<br/>
	 * 例：negation("00001001")
	 * 
	 * @param operand
	 *            二进制表示的操作数
	 * @return operand按位取反的结果
	 */
	public String negation(String operand) {
		// TODO YOUR CODE HERE.
		String result = "";
		for (int i = 0; i < operand.length(); i++) {
			if (operand.charAt(i) == '0') {
				result += "1";
			} else {
				result += "0";
			}
		}
		return result;
	}

	/**
	 * 左移操作。<br/>
	 * 例：leftShift("00001001", 2)
	 * 
	 * @param operand
	 *            二进制表示的操作数
	 * @param n
	 *            左移的位数
	 * @return operand左移n位的结果
	 */
	public String leftShift(String operand, int n) {
		// TODO YOUR CODE HERE.
		String toAdd = "";
		String result = "";

		for (int i = 0; i < n; i++) {
			toAdd += "0";
		}
		result = operand + toAdd;
		String remain = result.substring(n);

		return remain;
	}

	/**
	 * 逻辑右移操作。<br/>
	 * 例：logRightShift("11110110", 2)
	 * 
	 * @param operand
	 *            二进制表示的操作数
	 * @param n
	 *            右移的位数
	 * @return operand逻辑右移n位的结果
	 */
	public String logRightShift(String operand, int n) {
		// TODO YOUR CODE HERE.
		String result = "";
		String toAdd = "";

		for (int i = 0; i < n; i++) {
			toAdd += "0";
		}
		result = toAdd + operand;
		result = result.substring(0, operand.length());
		return result;
	}

	/**
	 * 算术右移操作。<br/>
	 * 例：logRightShift("11110110", 2)
	 * 
	 * @param operand
	 *            二进制表示的操作数
	 * @param n
	 *            右移的位数
	 * @return operand算术右移n位的结果
	 */
	public String ariRightShift(String operand, int n) {
		// TODO YOUR CODE HERE.
		String result = "";
		String toAdd = "";
		if (operand.startsWith("0")) {
			for (int i = 0; i < n; i++) {
				toAdd += "0";
			}
			result = toAdd + operand;
		} else {
			for (int i = 0; i < n; i++) {
				toAdd += "1";
			}
			result = toAdd + operand;
		}
		result = result.substring(0, operand.length());
		return result;
	}

	/**
	 * 全加器，对两位以及进位进行加法运算。<br/>
	 * 例：fullAdder('1', '1', '0')
	 * 
	 * @param x
	 *            被加数的某一位，取0或1
	 * @param y
	 *            加数的某一位，取0或1
	 * @param c
	 *            低位对当前位的进位，取0或1
	 * @return 相加的结果，用长度为2的字符串表示，第1位表示进位，第2位表示和
	 */
	public String fullAdder(char x, char y, char c) {
		// TODO YOUR CODE HERE.
		char Ci, S;
		S = myALU.xor(x, myALU.xor(y, c));
		Ci = myALU.or(myALU.or(myALU.and(x, y), myALU.and(x, c)), myALU.and(y, c));
		String result = String.valueOf(Ci) + String.valueOf(S);
		return result;
	}

	/**
	 * 4位先行进位加法器。要求采用{@link #fullAdder(char, char, char) fullAdder}来实现<br/>
	 * 例：claAdder("1001", "0001", '1')
	 * 
	 * @param operand1
	 *            4位二进制表示的被加数
	 * @param operand2
	 *            4位二进制表示的加数
	 * @param c
	 *            低位对当前位的进位，取0或1
	 * @return 长度为5的字符串表示的计算结果，其中第1位是最高位进位，后4位是相加结果，其中进位不可以由循环获得
	 */
	public String claAdder(String operand1, String operand2, char c) {
		// TODO YOUR CODE HERE.
		String s = "";
		for (int i = operand1.length() - 1; i >= 0; i--) {
			s += myALU.fullAdder(operand1.charAt(i), operand2.charAt(i), c).charAt(1);
			c = myALU.fullAdder(operand1.charAt(i), operand2.charAt(i), c).charAt(0);
		}
		String result = c + new StringBuffer(s).reverse().toString();
		return result;
	}

	/**
	 * 加一器，实现操作数加1的运算。 需要采用与门、或门、异或门等模拟， 不可以直接调用
	 * {@link #fullAdder(char, char, char) fullAdder}、
	 * {@link #claAdder(String, String, char) claAdder}、
	 * {@link #adder(String, String, char, int) adder}、
	 * {@link #integerAddition(String, String, int) integerAddition}方法。<br/>
	 * 例：oneAdder("00001001")
	 * 
	 * @param operand
	 *            二进制补码表示的操作数
	 * @return operand加1的结果，长度为operand的长度加1，其中第1位指示是否溢出（溢出为1，否则为0），其余位为相加结果
	 */
	public String oneAdder(String operand) {
		// TODO YOUR CODE HERE.
		String s = "";
		String toAdd = "";
		char c = '0';// 进位
		for (int i = 0; i < operand.length() - 1; i++) {
			toAdd += "0";
		}
		toAdd += "1";
		for (int i = operand.length() - 1; i >= 0; i--) {
			s += myALU.xor(toAdd.charAt(i), myALU.xor(operand.charAt(i), c));
			c = myALU.or(myALU.or(myALU.and(toAdd.charAt(i), operand.charAt(i)), myALU.and(toAdd.charAt(i), c)),
					myALU.and(operand.charAt(i), c));
		}
		String result = new StringBuffer(s).reverse().toString();
		if (result.charAt(0) == operand.charAt(0)) {
			result = "0" + result;
		} else {
			result = "1" + result;
		}
		return result;
	}

	/**
	 * 加法器，要求调用{@link #claAdder(String, String, char)}方法实现。<br/>
	 * 例：adder("0100", "0011", ‘0’, 8)
	 * 
	 * @param operand1
	 *            二进制补码表示的被加数
	 * @param operand2
	 *            二进制补码表示的加数
	 * @param c
	 *            最低位进位
	 * @param length
	 *            存放操作数的寄存器的长度，为4的倍数。length不小于操作数的长度，当某个操作数的长度小于length时，
	 *            需要在高位补符号位
	 * @return 长度为length+1的字符串表示的计算结果，其中第1位指示是否溢出（溢出为1，否则为0），后length位是相加结果
	 */
	public String adder(String operand1, String operand2, char c, int length) {
		// TODO YOUR CODE HERE.
		String toAdd = "";
		char Ci = c, Cj = c;
		// 对齐两个操作数长度
		if (operand1.length() < length) {
			if (operand1.startsWith("0")) {
				for (int i = 0; i < length - operand1.length(); i++) {
					toAdd += "0";
				}
				operand1 = toAdd + operand1;
			} else if (operand1.startsWith("1")) {
				for (int i = 0; i < length - operand1.length(); i++) {
					toAdd += "1";
				}
				operand1 = toAdd + operand1;
			}

		}
		if (operand2.length() < length) {
			if (operand2.startsWith("0")) {
				for (int i = 0; i < length - operand2.length(); i++) {
					toAdd += "0";
				}
				operand2 = toAdd + operand2;
			} else if (operand2.startsWith("1")) {
				for (int i = 0; i < length - operand2.length(); i++) {
					toAdd += "1";
				}
				operand2 = toAdd + operand2;
			}
		}

		for (int i = length - 1; i >= 0; i--) {
			Ci = myALU.fullAdder(operand1.charAt(i), operand2.charAt(i), Ci).charAt(0);
		}
		for (int i = length - 1; i > 0; i--) {
			Cj = myALU.fullAdder(operand1.charAt(i), operand2.charAt(i), Ci).charAt(0);
		}
		char C = myALU.xor(Ci, Cj);
		String result = C + myALU.claAdder(operand1, operand2, c).substring(1);
		return result;
	}

	/**
	 * 整数加法，要求调用{@link #adder(String, String, char, int) adder}方法实现。<br/>
	 * 例：integerAddition("0100", "0011", 8)
	 * 
	 * @param operand1
	 *            二进制补码表示的被加数
	 * @param operand2
	 *            二进制补码表示的加数
	 * @param length
	 *            存放操作数的寄存器的长度，为4的倍数。length不小于操作数的长度，当某个操作数的长度小于length时，
	 *            需要在高位补符号位
	 * @return 长度为length+1的字符串表示的计算结果，其中第1位指示是否溢出（溢出为1，否则为0），后length位是相加结果
	 */
	public String integerAddition(String operand1, String operand2, int length) {
		// TODO YOUR CODE HERE.
		String result = myALU.adder(operand1, operand2, '0', length);
		return result;
	}

	/**
	 * 整数减法，可调用{@link #adder(String, String, char, int) adder}方法实现。<br/>
	 * 例：integerSubtraction("0100", "0011", 8)
	 * 
	 * @param operand1
	 *            二进制补码表示的被减数
	 * @param operand2
	 *            二进制补码表示的减数
	 * @param length
	 *            存放操作数的寄存器的长度，为4的倍数。length不小于操作数的长度，当某个操作数的长度小于length时，
	 *            需要在高位补符号位
	 * @return 长度为length+1的字符串表示的计算结果，其中第1位指示是否溢出（溢出为1，否则为0），后length位是相减结果
	 */
	public String integerSubtraction(String operand1, String operand2, int length) {
		// TODO YOUR CODE HERE.
		operand2 = myALU.negation(operand2);
		operand2 = myALU.oneAdder(operand2).substring(1);
		String result = myALU.adder(operand1, operand2, '0', length);
		return result;
	}

	/**
	 * 整数乘法，使用Booth算法实现，可调用{@link #adder(String, String, char, int) adder}等方法。
	 * <br/>
	 * 例：integerMultiplication("0100", "0011", 8)
	 * 
	 * @param operand1
	 *            二进制补码表示的被乘数
	 * @param operand2
	 *            二进制补码表示的乘数
	 * @param length
	 *            存放操作数的寄存器的长度，为4的倍数。length不小于操作数的长度，当某个操作数的长度小于length时，
	 *            需要在高位补符号位
	 * @return 长度为length+1的字符串表示的相乘结果，其中第1位指示是否溢出（溢出为1，否则为0），后length位是相乘结果
	 */
	public String integerMultiplication(String operand1, String operand2, int length) {
		// TODO YOUR CODE HERE.
		// 对齐两个操作数
		String toAdd = "";
		if (operand1.length() < length) {
			if (operand1.startsWith("0")) {
				for (int i = 0; i < length - operand1.length(); i++) {
					toAdd += "0";
				}
				operand1 = toAdd + operand1;
			} else if (operand1.startsWith("1")) {
				for (int i = 0; i < length - operand1.length(); i++) {
					toAdd += "1";
				}
				operand1 = toAdd + operand1;
			}

		}
		if (operand2.length() < length) {
			if (operand2.startsWith("0")) {
				for (int i = 0; i < length - operand2.length(); i++) {
					toAdd += "0";
				}
				operand2 = toAdd + operand2;
			} else if (operand2.startsWith("1")) {
				for (int i = 0; i < length - operand2.length(); i++) {
					toAdd += "1";
				}
				operand2 = toAdd + operand2;
			}
		}

		String result = "";
		String rightShift = "";
		String X = operand1;
		String Y = operand2 + "0";// 乘数后补0，Y0=0
		String _X = myALU.oneAdder(myALU.negation(operand1)).substring(1);// 获得被乘数的补码

		for (int i = 0; i < operand1.length(); i++) {
			result += "0";
		}

		for (int i = 0; i < X.length(); i++) {

			if (Y.charAt(Y.length() - 1) == Y.charAt(Y.length() - 2)) {
				// Y0-Y1=0,直接右移
				rightShift = myALU.ariRightShift(result + Y, 1);
				result = rightShift.substring(0, X.length());
				Y = rightShift.substring(X.length());
			} else if (Y.charAt(Y.length() - 1) > Y.charAt(Y.length() - 2)) {
				// Y0-Y1=1,result+X后右移
				result = myALU.integerAddition(result, X, X.length()).substring(1);
				rightShift = myALU.ariRightShift(result + Y, 1);
				result = rightShift.substring(0, X.length());
				Y = rightShift.substring(X.length());
			} else {
				// Y0-Y1=-1,result+(-X)后右移
				result = myALU.integerAddition(result, _X, X.length()).substring(1);
				rightShift = myALU.ariRightShift(result + Y, 1);
				result = rightShift.substring(0, X.length());
				Y = rightShift.substring(X.length());
			}
		}
		Y = result + Y.substring(0, Y.length() - 1);

		String toAdd1 = "";
		int numOf0 = 0, numOf1 = 0;
		if (Y.length() < length) {
			// 不溢出，补全符号位
			for (int i = 0; i < length - Y.length(); i++) {
				if (Y.startsWith("0")) {
					toAdd1 += "0";
				} else {
					toAdd1 += "1";
				}
			}
			Y = "0" + toAdd1 + Y;
		} else if (Y.length() == length) {
			Y = "0" + result;
		} else if (Y.length() > length) {
			String overFlow = Y.substring(0, Y.length() - length - 1);
			for (int i = 0; i < overFlow.length(); i++) {
				if (overFlow.charAt(i) == '1') {
					numOf1++;
				} else {
					numOf0++;
				}
			}
			if (numOf1 == overFlow.length() || numOf0 == overFlow.length()) {
				Y = "0" + Y.substring(Y.length() - length);
			} else {
				Y = "1" + Y.substring(Y.length() - length);
			}
		}

		return Y;
	}

	/**
	 * 整数的不恢复余数除法，可调用{@link #adder(String, String, char, int) adder}等方法实现。<br/>
	 * 例：integerDivision("0100", "0011", 8)
	 * 
	 * @param operand1
	 *            二进制补码表示的被除数
	 * @param operand2
	 *            二进制补码表示的除数
	 * @param length
	 *            存放操作数的寄存器的长度，为4的倍数。length不小于操作数的长度，当某个操作数的长度小于length时，
	 *            需要在高位补符号位
	 * @return 长度为2*length+1的字符串表示的相除结果，其中第1位指示是否溢出（溢出为1，否则为0），其后length位为商，
	 *         最后length位为余数
	 */
	public String integerDivision(String operand1, String operand2, int length) {
		// TODO YOUR CODE HERE.
		// 对齐两个操作数
		String toAdd = "";
		if (operand1.length() < length) {
			if (operand1.startsWith("0")) {
				for (int i = 0; i < length - operand1.length(); i++) {
					toAdd += "0";
				}
				operand1 = toAdd + operand1;
			} else if (operand1.startsWith("1")) {
				for (int i = 0; i < length - operand1.length(); i++) {
					toAdd += "1";
				}
				operand1 = toAdd + operand1;
			}

		}
		if (operand2.length() < length) {
			if (operand2.startsWith("0")) {
				for (int i = 0; i < length - operand2.length(); i++) {
					toAdd += "0";
				}
				operand2 = toAdd + operand2;
			} else if (operand2.startsWith("1")) {
				for (int i = 0; i < length - operand2.length(); i++) {
					toAdd += "1";
				}
				operand2 = toAdd + operand2;
			}
		}

		String reminder = "";
		String quotient = operand1;
		String divisor = operand2;
		String leftShift = "";

		for (int i = 0; i < operand1.length(); i++) {
			if (operand1.startsWith("1")) {
				reminder += "1";
			} else {
				reminder += "0";
			}
		}
		for (int i = 0; i < reminder.length(); i++) {
			if (divisor.charAt(0) != reminder.charAt(0)) {
				// 余数和除数符号不同，做加法
				reminder = myALU.integerAddition(reminder, divisor, reminder.length()).substring(1);
			} else {
				// 余数和除数符号相同，做减法
				reminder = myALU.integerSubtraction(reminder, divisor, reminder.length()).substring(1);
			}

			if (reminder.charAt(0) == divisor.charAt(0)) {
				// 结果与除数符号相同，商后补1
				quotient += "1";
			} else {
				// 否则补0
				quotient += "0";
			}
			leftShift = myALU.leftShift(reminder + quotient, 1);// 左移
			reminder = leftShift.substring(0, reminder.length());
			quotient = leftShift.substring(reminder.length(), leftShift.length() - 1);
		}

		if (divisor.charAt(0) != reminder.charAt(0)) {
			// 余数和除数符号不同，做加法
			reminder = myALU.integerAddition(reminder, divisor, reminder.length()).substring(1);
		} else {
			// 余数和除数符号相同，做减法
			reminder = myALU.integerSubtraction(reminder, divisor, reminder.length()).substring(1);
		}
		if (reminder.charAt(0) == divisor.charAt(0)) {
			// 结果与除数符号相同，商后补1
			quotient += "1";
		} else {
			// 否则补0
			quotient += "0";
		}

		// 左移商
		quotient = quotient.substring(1);
		if (operand1.charAt(0) != operand2.charAt(0)) {
			// 若被除数符号与除数相反，则+1
			quotient = myALU.oneAdder(quotient).substring(1);
		}
		if (quotient.charAt(0) != operand1.charAt(0)) {
			// 若余数和被除数符号不同
			if (reminder.charAt(0) == divisor.charAt(0)) {
				// 若除数与余数符号相同，则除数减余数
				reminder = myALU.integerSubtraction(reminder, divisor, reminder.length()).substring(1);
			} else {
				// 否则做加法
				reminder = myALU.integerAddition(reminder, divisor, reminder.length()).substring(1);
			}
		}

		String result = "0" + reminder + quotient;

		return result;

	}

	/**
	 * 带符号整数加法，可以调用{@link #adder(String, String, char, int) adder}等方法，
	 * 但不能直接将操作数转换为补码后使用{@link #integerAddition(String, String, int)
	 * integerAddition}、 {@link #integerSubtraction(String, String, int)
	 * integerSubtraction}来实现。<br/>
	 * 例：signedAddition("1100", "1011", 8)
	 * 
	 * @param operand1
	 *            二进制原码表示的被加数，其中第1位为符号位
	 * @param operand2
	 *            二进制原码表示的加数，其中第1位为符号位
	 * @param length
	 *            存放操作数的寄存器的长度，为4的倍数。length不小于操作数的长度（不包含符号），当某个操作数的长度小于length时，
	 *            需要将其长度扩展到length
	 * @return 长度为length+2的字符串表示的计算结果，其中第1位指示是否溢出（溢出为1，否则为0），第2位为符号位，
	 *         后length位是相加结果
	 */
	public String signedAddition(String operand1, String operand2, int length) {
		// TODO YOUR CODE HERE.
		// 对齐两个操作数
		String toAdd = "";
		if (operand1.length() < length) {
			if (operand1.startsWith("0")) {
				for (int i = 0; i < length - operand1.length(); i++) {
					toAdd += "0";
				}
				operand1 = toAdd + operand1;
			} else if (operand1.startsWith("1")) {
				for (int i = 0; i < length - operand1.length(); i++) {
					toAdd += "1";
				}
				operand1 = toAdd + operand1;
			}

		}
		if (operand2.length() < length) {
			if (operand2.startsWith("0")) {
				for (int i = 0; i < length - operand2.length(); i++) {
					toAdd += "0";
				}
				operand2 = toAdd + operand2;
			} else if (operand2.startsWith("1")) {
				for (int i = 0; i < length - operand2.length(); i++) {
					toAdd += "1";
				}
				operand2 = toAdd + operand2;
			}
		}
		return null;
	}

	/**
	 * 浮点数加法，可调用{@link #signedAddition(String, String, int) signedAddition}
	 * 等方法实现。<br/>
	 * 例：floatAddition("00111111010100000", "00111111001000000", 8, 8, 8)
	 * 
	 * @param operand1
	 *            二进制表示的被加数
	 * @param operand2
	 *            二进制表示的加数
	 * @param eLength
	 *            指数的长度，取值大于等于 4
	 * @param sLength
	 *            尾数的长度，取值大于等于 4
	 * @param gLength
	 *            保护位的长度
	 * @return 长度为2+eLength+sLength的字符串表示的相加结果，其中第1位指示是否指数上溢（溢出为1，否则为0），
	 *         其余位从左到右依次为符号、指数（移码表示）、尾数（首位隐藏）。舍入策略为向0舍入
	 */
	public String floatAddition(String operand1, String operand2, int eLength, int sLength, int gLength) {
		// TODO YOUR CODE HERE.
		return null;
	}

	/**
	 * 浮点数减法，可调用{@link #floatAddition(String, String, int, int, int)
	 * floatAddition}方法实现。<br/>
	 * 例：floatSubtraction("00111111010100000", "00111111001000000", 8, 8, 8)
	 * 
	 * @param operand1
	 *            二进制表示的被减数
	 * @param operand2
	 *            二进制表示的减数
	 * @param eLength
	 *            指数的长度，取值大于等于 4
	 * @param sLength
	 *            尾数的长度，取值大于等于 4
	 * @param gLength
	 *            保护位的长度
	 * @return 长度为2+eLength+sLength的字符串表示的相减结果，其中第1位指示是否指数上溢（溢出为1，否则为0），
	 *         其余位从左到右依次为符号、指数（移码表示）、尾数（首位隐藏）。舍入策略为向0舍入
	 */
	public String floatSubtraction(String operand1, String operand2, int eLength, int sLength, int gLength) {
		// TODO YOUR CODE HERE.
		return null;
	}

	/**
	 * 浮点数乘法，可调用{@link #integerMultiplication(String, String, int)
	 * integerMultiplication}等方法实现。<br/>
	 * 例：floatMultiplication("00111110111000000", "00111111000000000", 8, 8)
	 * 
	 * @param operand1
	 *            二进制表示的被乘数
	 * @param operand2
	 *            二进制表示的乘数
	 * @param eLength
	 *            指数的长度，取值大于等于 4
	 * @param sLength
	 *            尾数的长度，取值大于等于 4
	 * @return 长度为2+eLength+sLength的字符串表示的相乘结果,其中第1位指示是否指数上溢（溢出为1，否则为0），
	 *         其余位从左到右依次为符号、指数（移码表示）、尾数（首位隐藏）。舍入策略为向0舍入
	 */
	public String floatMultiplication(String operand1, String operand2, int eLength, int sLength) {
		// TODO YOUR CODE HERE.
		return null;
	}

	/**
	 * 浮点数除法，可调用{@link #integerDivision(String, String, int) integerDivision}
	 * 等方法实现。<br/>
	 * 例：floatDivision("00111110111000000", "00111111000000000", 8, 8)
	 * 
	 * @param operand1
	 *            二进制表示的被除数
	 * @param operand2
	 *            二进制表示的除数
	 * @param eLength
	 *            指数的长度，取值大于等于 4
	 * @param sLength
	 *            尾数的长度，取值大于等于 4
	 * @return 长度为2+eLength+sLength的字符串表示的相乘结果,其中第1位指示是否指数上溢（溢出为1，否则为0），
	 *         其余位从左到右依次为符号、指数（移码表示）、尾数（首位隐藏）。舍入策略为向0舍入
	 */
	public String floatDivision(String operand1, String operand2, int eLength, int sLength) {
		// TODO YOUR CODE HERE.
		return null;
	}
}
