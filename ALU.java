/**
 * 模拟ALU进行整数和浮点数的四则运算
 * 
 * @author [151250121_桑田]
 *
 */

public class ALU {

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
		int decIntegerPart;// 十进制整数部分
		double decDoublePart;// 十进制小数部分
		String mantissa = "";// 尾数部分
		String binIntegerPart = "";// 二进制整数部分
		String binDoublePart = "";// 二进制小数部分

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
		} else if (decTrueValue > Math.pow(2, Math.pow(2, eLength)) * (2 - Math.pow(2, -sLength))) {
			// 正无穷
			result += "0";
			for (int i = 0; i < eLength; i++) {
				result += "1";
			}
			for (int i = 0; i < sLength; i++) {
				result += "0";
			}

		} else if (decTrueValue < -((Math.pow(2, Math.pow(2, eLength)) * (2 - Math.pow(2, -sLength))))) {
			// 负无穷
			result += "1";
			for (int i = 0; i < eLength; i++) {
				result += "1";
			}
			for (int i = 0; i < sLength; i++) {
				result += "0";
			}
		} else if (decTrueValue < Math.pow(2, Math.pow(2, 1 - eLength))
				&& decTrueValue > -Math.pow(2, Math.pow(2, 1 - eLength))) {
			// 反规格化
			if (decTrueValue > 0) {
				result += "0";
			} else {
				result += "1";
			}
			for (int i = 0; i < eLength; i++) {
				result += "0";
			}
			// 计算尾数
			decDoublePart = Double.parseDouble("0." + number.split("\\.")[1]);
			while (decDoublePart != 1) {
				if (decDoublePart * 2 < 1) {
					binDoublePart += "0";
					decDoublePart = decDoublePart * 2;
				} else if (decDoublePart * 2 > 1) {
					binDoublePart += "1";
					decDoublePart = decDoublePart * 2 - 1;
				} else if (decDoublePart * 2 == 1) {
					binDoublePart += "1";
					break;
				}
			}
			mantissa = binDoublePart;
			// 调整尾数长度
			if (mantissa.length() < sLength) {
				for (int i = 0; i < sLength; i++) {
					mantissa += "0";
				}
			} else {
				mantissa = mantissa.substring(0, sLength);
			}

			String exponent = "";
			while (exponent.length() != eLength)
				exponent += "0";

			result += exponent + mantissa;

		} else {
			// 得出符号位，并分出整数部分和小数部分
			if (number.startsWith("-")) {
				result += "1";
				decIntegerPart = Integer.parseInt(number.substring(1).split("\\.")[0]);
				decDoublePart = Double.parseDouble("0." + number.substring(1).split("\\.")[1]);
			} else {
				result += "0";
				decIntegerPart = Integer.parseInt(number.split("\\.")[0]);
				decDoublePart = Double.parseDouble("0." + number.split("\\.")[1]);
			}

			while (decIntegerPart >= 0) {
				// 计算二进制整数部分
				binIntegerPart += decIntegerPart % 2;
				decIntegerPart = decIntegerPart / 2;
				if (decIntegerPart == 0)
					break;
			}
			binIntegerPart = new StringBuffer(binIntegerPart).reverse().toString();
			System.out.println(binIntegerPart);

			while (decDoublePart != 1) {
				if (decDoublePart * 2 < 1) {
					binDoublePart += "0";
					decDoublePart = decDoublePart * 2;
				} else if (decDoublePart * 2 > 1) {
					binDoublePart += "1";
					decDoublePart = decDoublePart * 2 - 1;
				} else if (decDoublePart * 2 == 1) {
					binDoublePart += "1";
					break;
				}
			}
			// 计算指数和尾数
			int integerExponent = 0;
			if (binIntegerPart.startsWith("1")) {
				integerExponent = binIntegerPart.length() - 1;
				integerExponent += Math.pow(2, eLength - 1) - 1;
				mantissa = (binIntegerPart + binDoublePart).substring(1);

			} else {
				for (int i = 0; i < binDoublePart.length(); i++) {
					if (binDoublePart.charAt(i) == '1') {
						integerExponent = -i - 1;
						integerExponent += Math.pow(2, eLength - 1) - 1;
						mantissa = binDoublePart.substring(i + 1);
						break;
					}
				}

			}
			// 调整尾数长度
			if (mantissa.length() < sLength) {
				while (mantissa.length() != sLength)
					mantissa += "0";
			} else {
				mantissa = mantissa.substring(0, sLength);
			}
			String binExponent = this.integerRepresentation(String.valueOf(integerExponent), eLength);
			result += binExponent + mantissa;
		}

		System.out.println(result);

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
		String result = this.floatRepresentation(number, 8, 23);
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
				if (operand.charAt(i) == '1')
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

		// 判断是否为0
		boolean operandIs0 = true;
		for (int i = 0; i < operand.length(); i++) {
			if (operand.charAt(i) == '1')
				operandIs0 = false;
		}
		if (operandIs0)
			return "0";
		else {
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
		S = this.xor(x, this.xor(y, c));
		Ci = this.or(this.or(this.and(x, y), this.and(x, c)), this.and(y, c));
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
		char[] P = new char[4], G = new char[4], C = new char[5];
		for (int i = 0; i < 4; i++) {
			P[i] = this.or(operand1.charAt(3 - i), operand2.charAt(3 - i));
			G[i] = this.and(operand1.charAt(3 - i), operand2.charAt(3 - i));

		}
		C[0] = c;
		// C1 = G1 + P1C0
		C[1] = this.or(G[0], this.and(P[0], C[0]));
		// C2 = G2 + P2G1 + P2P1C0
		C[2] = this.or(this.or(G[1], this.and(P[1], G[0])), this.and(this.and(P[1], P[0]), C[0]));
		// C3 = G3 + P3G2 + P3P2G1 + P3P2P1C0
		C[3] = this.or(this.or(this.or(G[2], this.and(P[2], G[1])), this.and(this.and(P[2], P[1]), G[0])),
				this.and(this.and(this.and(P[2], P[1]), P[0]), C[0]));
		// C4 = G4 + P4G3 + P4P3G2 + P4P3P2G1 + P4P3P2P1C0
		C[4] = or(or(or(G[3], and(P[3], G[2])), and(and(P[3], P[2]), G[1])),
				or(and(and(P[3], P[2]), and(P[1], G[0])), and(and(P[3], P[2]), and(and(P[1], P[0]), C[0]))));
		for (int i = 0; i < 4; i++) {
			s += this.xor(this.xor(operand1.charAt(3 - i), operand2.charAt(3 - i)), C[i]);
		}
		String result = C[4] + new StringBuffer(s).reverse().toString();
		return result;
	}

	public String myClaAdder(String operand1, String operand2, char c) {
		String s = "";
		for (int i = operand1.length() - 1; i >= 0; i--) {
			s += this.fullAdder(operand1.charAt(i), operand2.charAt(i), c).charAt(1);
			c = this.fullAdder(operand1.charAt(i), operand2.charAt(i), c).charAt(0);
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
		String result = "";
		char Ci = '0', Cj = '0';// 进位
		for (int i = 0; i < operand.length() - 1; i++) {
			toAdd += "0";
		}
		toAdd += "1";
		for (int i = operand.length() - 1; i >= 0; i--) {
			s += this.xor(toAdd.charAt(i), this.xor(operand.charAt(i), Ci));
			Ci = this.or(this.or(this.and(toAdd.charAt(i), operand.charAt(i)), this.and(toAdd.charAt(i), Ci)),
					this.and(operand.charAt(i), Ci));
		}
		for (int i = operand.length() - 1; i > 0; i--) {
			Cj = this.or(this.or(this.and(toAdd.charAt(i), operand.charAt(i)), this.and(toAdd.charAt(i), Cj)),
					this.and(operand.charAt(i), Cj));
		}
		if (Ci != Cj) {
			result = "1" + new StringBuffer(s).reverse().toString();
		} else {
			result = "0" + new StringBuffer(s).reverse().toString();
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
		toAdd = "";
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
			Ci = this.or(this.or(this.and(operand2.charAt(i), operand1.charAt(i)), this.and(operand2.charAt(i), Ci)),
					this.and(operand1.charAt(i), Ci));
		}
		for (int i = length - 1; i > 0; i--) {
			Cj = this.or(this.or(this.and(operand2.charAt(i), operand1.charAt(i)), this.and(operand2.charAt(i), Cj)),
					this.and(operand1.charAt(i), Cj));
		}
		char C = 0;
		if (Ci != Cj) {
			C = '1';
		} else {
			C = '0';
		}
		String result = C + this.myClaAdder(operand1, operand2, c).substring(1);
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
		String result = this.adder(operand1, operand2, '0', length);
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
		operand2 = this.negation(operand2);
		operand2 = this.oneAdder(operand2).substring(1);
		String result = this.adder(operand1, operand2, '0', length);
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
		toAdd = "";
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
		String overflow = "";
		String rightShift = "";
		String X = operand1;
		String Y = operand2 + "0";// 乘数后补0，Y0=0
		String _X = this.oneAdder(this.negation(operand1)).substring(1);// 获得被乘数的补码

		for (int i = 0; i < operand1.length(); i++) {
			result += "0";
		}

		for (int i = 0; i < X.length(); i++) {

			if (Y.charAt(Y.length() - 1) == Y.charAt(Y.length() - 2)) {
				// Y0-Y1=0,直接右移
				rightShift = this.ariRightShift(result + Y, 1);
				result = rightShift.substring(0, X.length());
				Y = rightShift.substring(X.length());
			} else if (Y.charAt(Y.length() - 1) > Y.charAt(Y.length() - 2)) {
				// Y0-Y1=1,result+X后右移
				result = this.integerAddition(result, X, X.length()).substring(1);
				// if (Integer.parseInt(myALU.integerTrueValue("0"+result)) >
				// Math.pow(2, X.length() - 1) - 1) {
				// // result+X超出表示范围
				// overflow="1";
				// }
				rightShift = this.ariRightShift(result + Y, 1);
				result = rightShift.substring(0, X.length());
				Y = rightShift.substring(X.length());
			} else {
				// Y0-Y1=-1,result+(-X)后右移
				result = this.integerAddition(result, _X, X.length()).substring(1);
				rightShift = this.ariRightShift(result + Y, 1);
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
		boolean o1is0 = true;
		boolean o2is0 = true;
		String s = "";
		for (int i = 0; i < operand1.length(); i++) {
			if (operand1.charAt(i) == '1') {
				o1is0 = false;
			}
			if (operand2.charAt(i) == '1') {
				o2is0 = false;
			}
		}
		if ((o1is0 && o2is0) || o2is0) {
			return "NaN";

		} else if (o1is0) {
			for (int i = 0; i < 2 * length + 1; i++) {
				s += "0";
			}
			return s;
		}
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
		toAdd = "";
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
				reminder = this.integerAddition(reminder, divisor, reminder.length()).substring(1);
			} else {
				// 余数和除数符号相同，做减法
				reminder = this.integerSubtraction(reminder, divisor, reminder.length()).substring(1);
			}

			if (reminder.charAt(0) == divisor.charAt(0)) {
				// 结果与除数符号相同，商后补1
				quotient += "1";
			} else {
				// 否则补0
				quotient += "0";
			}
			leftShift = this.leftShift(reminder + quotient, 1);// 左移
			reminder = leftShift.substring(0, reminder.length());
			quotient = leftShift.substring(reminder.length(), leftShift.length() - 1);
		}

		if (divisor.charAt(0) != reminder.charAt(0)) {
			// 余数和除数符号不同，做加法
			reminder = this.integerAddition(reminder, divisor, reminder.length()).substring(1);
		} else {
			// 余数和除数符号相同，做减法
			reminder = this.integerSubtraction(reminder, divisor, reminder.length()).substring(1);
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
			quotient = this.oneAdder(quotient).substring(1);
		}
		if (reminder.charAt(0) != operand1.charAt(0)) {
			// 若余数和被除数符号不同
			if (reminder.charAt(0) == divisor.charAt(0)) {
				// 若除数与余数符号相同，则除数减余数
				reminder = this.integerSubtraction(reminder, divisor, reminder.length()).substring(1);
			} else {
				// 否则做加法
				reminder = this.integerAddition(reminder, divisor, reminder.length()).substring(1);
			}
		}

		String result = "0" + quotient + reminder;

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
		boolean exception = false;
		String newOperand1 = operand1.substring(1);
		String newOperand2 = operand2.substring(1);
		// 对齐两个操作数
		if (operand1.length() > length || operand2.length() > length) {
			length += 1;
			exception = true;
		}
		String toAdd = "";
		if (newOperand1.length() < length) {
			for (int i = 0; i < length - newOperand1.length(); i++) {
				toAdd += "0";
			}
			newOperand1 = toAdd + newOperand1;
		}
		toAdd = "";
		if (newOperand2.length() < length) {
			for (int i = 0; i < length - newOperand2.length(); i++) {
				toAdd += "0";
			}
			newOperand2 = toAdd + newOperand2;
		}

		String result = "";
		char c = '0', s = '0';
		if (operand1.charAt(0) == operand2.charAt(0)) {
			// 两个操作数符号相同，去掉符号位做加法
			for (int i = length - 1; i >= 0; i--) {
				char tmp = this.fullAdder(newOperand1.charAt(i), newOperand2.charAt(i), c).charAt(0);
				s = this.fullAdder(newOperand1.charAt(i), newOperand2.charAt(i), c).charAt(1);
				result = s + result;
				c = tmp;
			}
			// result = this.integerAddition(newOperand1, newOperand2,
			// length).substring(1);
			result = "0" + operand1.substring(0, 1) + result;
		} else if (operand1.charAt(0) != operand2.charAt(0)) {
			// X和Y异号
			if (operand1.substring(1).equals(operand2.substring(1))) {
				for (int i = 0; i < length + 2; i++) {
					result += "0";
				}
			} else {
				newOperand2 = this.oneAdder(this.negation(newOperand2)).substring(1);// 对Y取反加一
				for (int i = length - 1; i >= 0; i--) {
					char tmp = this.fullAdder(newOperand1.charAt(i), newOperand2.charAt(i), c).charAt(0);
					s = this.fullAdder(newOperand1.charAt(i), newOperand2.charAt(i), c).charAt(1);
					result = s + result;
					c = tmp;
				}
				// c = this.integerAddition(newOperand1, newOperand2,
				// length).charAt(0);
				// result = this.integerAddition(newOperand1, newOperand2,
				// length).substring(1);
				if (c == '1') {
					// 有进位，把进位去掉，符号与被减数相同
					result = "0" + operand1.substring(0, 1) + result;
				} else {
					// 无进位，把结果取反加一，符号与被减数相反
					result = this.oneAdder(this.negation(result)).substring(1);
					result = "0" + String.valueOf(1 - Integer.parseInt(operand1.substring(0, 1))) + result;
				}
			}

		}
		if (exception) {
			if (result.charAt(2) != operand1.charAt(0)) {
				// 溢出
				return "1" + result.substring(1, 2) + result.substring(result.length() - (length - 1));
			} else {
				return "0" + result.substring(1, 2) + result.substring(result.length() - (length - 1));
			}

		} else {
			return result;
		}

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
		boolean Xis0 = true;
		boolean Yis0 = true;
		boolean normalExcute = false;// 是否进行正常计算
		String mantissa1 = "1" + operand1.substring(eLength + 1);
		String mantissa2 = "1" + operand2.substring(eLength + 1);// 尾数前补1
		int exponent1 = Integer.parseInt(this.integerTrueValue("0" + operand1.substring(1, eLength + 1)));
		int exponent2 = Integer.parseInt(this.integerTrueValue("0" + operand2.substring(1, eLength + 1)));
		String exponent = "";
		String result = "";

		// 根据保护位扩展尾数
		for (int i = 0; i < gLength; i++) {
			mantissa1 += "0";
			mantissa2 += "0";
		}

		// 检查被加数X是否等于0
		for (int i = 1; i < operand1.length(); i++) {
			if (operand1.charAt(i) == '1') {
				Xis0 = false;
				break;
			}
		}
		if (Xis0) {
			// 若X=0，Z=Y
			result = "0" + operand2;
			return result;
		} else {
			// X≠0，检查加数Y是否等于0
			for (int i = 1; i < operand2.length(); i++) {
				if (operand2.charAt(i) == '1') {
					Yis0 = false;
					break;
				}
			}
			if (Yis0) {
				// 若Y=0，Z=X
				result = "0" + operand1;
				return result;
			} else {
				// X和Y都≠0
				normalExcute = true;
			}
		}

		if (normalExcute) {
			// 比较指数部分是否对齐
			if (exponent1 != exponent2) {
				// 指数不相等，增量较小的指数并右移相应尾数
				if (exponent1 > exponent2) {
					for (int i = 0; i < exponent1 - exponent2; i++) {
						mantissa2 = this.logRightShift(mantissa2, 1);
						int num = 0;
						for (int j = 0; j < mantissa2.length(); j++) {
							if (mantissa2.charAt(j) == '0') {
								num++;
							}
						}
						if (num == mantissa2.length()) {
							// 右移后尾数=0
							result = "0" + operand1;
							return result;
						}
					}
					exponent2 = exponent1;
				} else {
					for (int i = 0; i < exponent2 - exponent1; i++) {
						mantissa1 = this.logRightShift(mantissa1, 1);
						int num = 0;
						for (int j = 0; j < mantissa1.length(); j++) {
							if (mantissa1.charAt(j) == '0') {
								num++;
							}
						}
						if (num == mantissa1.length()) {
							// 右移后尾数=0
							result = "0" + operand2;
							return result;
						}
					}
					exponent1 = exponent2;
				}
			}

			exponent = this.integerRepresentation(String.valueOf(exponent1), eLength);

			// 现在指数相等，且已经规格化了
			String significand = "";
			boolean continuing1 = false, continuing2 = false;
			significand = this.signedAddition(operand1.charAt(0) + mantissa1, operand2.charAt(0) + mantissa2,
					mantissa1.length());
			char overflow = significand.charAt(0);// 尾数溢出位
			char sign = significand.charAt(1);// 尾数符号位
			significand = significand.substring(2);

			int num = 0;
			for (int i = 0; i < significand.length(); i++) {
				if (significand.charAt(i) == '0') {
					num++;
				}
			}
			if (num == significand.length()) {
				// 尾数=0,Z=0
				for (int i = 0; i < 1 + eLength + sLength; i++) {
					result += "0";
				}
			} else if (overflow == '1') {
				// 尾数溢出
				continuing1 = true;
				significand = "1" + this.logRightShift(significand, 1).substring(1);// 右移尾数,第一位是1
				exponent = this.oneAdder(this.integerRepresentation(String.valueOf(exponent1), eLength)).substring(1);// 指数+1
				int numOf1 = 0;
				for (int i = 0; i < exponent.length(); i++) {
					if (exponent.charAt(i) == '1')
						numOf1++;
				}
				if (numOf1 == exponent.length()) {
					// 指数上溢
					String mantissa = "";
					// 尾数清零
					for (int i = 0; i < sLength; i++) {
						mantissa += "0";
					}
					result += "1";// 最后结果溢出
					result += sign;
					result += exponent + mantissa;
				} else {
					result += "0";
					result += sign;
					result += exponent + significand.substring(1, sLength + 1);
				}
			} else if (!continuing1 || continuing2) {
				// 检查是否规格化
				exponent = this.integerRepresentation(String.valueOf(exponent1), eLength);
				for (int i = 0; i < significand.length(); i++) {
					if (significand.charAt(0) != '1') {
						// 没有规格化，左移尾数
						significand = this.leftShift(significand, 1);
						// 指数减一
						exponent = this.integerSubtraction(exponent, "0001", eLength).substring(1);
						// 检查指数是否下溢
						int numOf0 = 0;
						for (int j = 0; j < exponent.length(); j++) {
							if (exponent.charAt(j) == '0')
								numOf0++;
						}
						if (numOf0 == exponent.length()) {
							// 指数下溢
							String mantissa = "";
							// 尾数清零
							for (int z = 0; z < sLength; z++) {
								mantissa += "0";
							}
							result += "0";
							result += sign;
							result += exponent + mantissa;
							break;
						}
					} else {
						// 结果规格化了
						result += "0";
						result += sign;
						result += exponent + significand.substring(1, sLength + 1);
						break;
					}
				}

			}
		}
		return result;
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
		operand2 = this.negation(operand2.substring(0, 1)) + operand2.substring(1);
		String result = this.floatAddition(operand1, operand2, eLength, sLength, gLength);
		return result;
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
		String result = "";
		String mantissa = "";
		char sign = this.xor(operand1.charAt(0), operand2.charAt(0));// 符号位做异或
		// 判断X和Y是否为0
		boolean Xis0 = true, Yis0 = true;
		for (int i = 1; i < operand1.length(); i++) {
			if (operand1.charAt(i) == '1') {
				Xis0 = false;
				break;
			}
		}
		for (int i = 1; i < operand2.length(); i++) {
			if (operand2.charAt(i) == '1') {
				Yis0 = false;
				break;
			}
		}
		if (Xis0 || Yis0) {
			// X和Y有一个是0
			for (int i = 0; i < eLength + sLength + 2; i++) {
				result += "0";
			}
		} else {
			// X和Y都不为0
			String exponent = this.signedAddition("0" + operand1.substring(1, eLength + 1),
					"0" + operand2.substring(1, eLength + 1), eLength).substring(2);
			exponent = this.integerRepresentation(
					String.valueOf(
							Integer.parseInt(this.integerTrueValue(exponent)) - (int) (Math.pow(2, eLength - 1) - 1)),
					eLength);// 减去偏值
			// 检查指数是否上溢
			boolean overflow = true;
			for (int i = 0; i < exponent.length(); i++) {
				if (exponent.charAt(i) == '0') {
					overflow = false;
				}
			}
			if (overflow) {
				// 报告上溢
				mantissa = "";
				for (int i = 0; i < sLength; i++) {
					mantissa += "0";
				}
				result += "1";
				result += sign;
				result += exponent + mantissa;
			}
			// 检查指数是否下溢
			boolean underflow = true;
			for (int i = 0; i < exponent.length(); i++) {
				if (exponent.charAt(i) == '1') {
					underflow = false;
				}
			}
			if (underflow) {
				// 报告下溢
				mantissa = "";
				for (int i = 0; i < sLength; i++) {
					mantissa += "0";
				}
				result += "0";
				result += sign;
				result += exponent + mantissa;
			}
			// 尾数相乘
			String mantissa1 = "1" + operand1.substring(eLength + 1);
			String mantissa2 = "1" + operand2.substring(eLength + 1);
			String multiMantissa = this.integerMultiplication("0" + mantissa1, "0" + mantissa2, 2 * (sLength + 2));// 尾数前加一位“0”表示正数相乘
			char over = multiMantissa.charAt(0);// 溢出位
			multiMantissa = multiMantissa.substring(3);// 去掉表示符号的两位“0”

			if (multiMantissa.charAt(0) == '1') {
				// 第一位为1，小数点要左移
				exponent = this.oneAdder(exponent).substring(1);// 指数加一
				// 检查指数是否上溢
				boolean overflow1 = true;
				for (int i1 = 0; i1 < exponent.length(); i1++) {
					if (exponent.charAt(i1) == '0') {
						overflow1 = false;
					}
				}
				if (overflow1) {
					// 报告上溢
					mantissa = "";
					for (int i1 = 0; i1 < sLength; i1++) {
						mantissa += "0";
					}
					result += "1";
					result += sign;
					result += exponent + mantissa;
				} else {
					result += "0";
					result += sign;
					result += exponent + multiMantissa.substring(1, sLength + 1);
				}
			} else
				for (int i = 0; i < multiMantissa.length(); i++) {
					if (multiMantissa.charAt(1) != '1') {
						// 没有规格化，左移尾数
						multiMantissa = this.leftShift(multiMantissa, 1);
						exponent = this.integerSubtraction(exponent, "0001", eLength).substring(1);// 指数减一
						// 检查指数是否下溢
						boolean underflow1 = true;
						for (int i1 = 0; i1 < exponent.length(); i1++) {
							if (exponent.charAt(i1) == '1') {
								underflow1 = false;
							}
						}
						if (underflow1) {
							// 报告下溢
							mantissa = "";
							for (int i1 = 0; i1 < sLength; i1++) {
								mantissa += "0";
							}
							result += "0";
							result += sign;
							result += exponent + mantissa;
						}
					} else {
						result += "0";
						result += sign;
						result += exponent + multiMantissa.substring(2, sLength + 2);
						break;
					}
				}

		}
		return result;
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
		String result = "";
		String mantissa = "";
		char sign = this.xor(operand1.charAt(0), operand2.charAt(0));// 符号位做异或
		// 判断X和Y是否为0
		boolean Xis0 = true, Yis0 = true;
		for (int i = 1; i < operand1.length(); i++) {
			if (operand1.charAt(i) == '1') {
				Xis0 = false;
				break;
			}
		}
		for (int i = 1; i < operand2.length(); i++) {
			if (operand2.charAt(i) == '1') {
				Yis0 = false;
				break;
			}
		}
		if (Xis0) {
			// 被除数是0，结果返回0
			for (int i = 0; i < eLength + sLength + 2; i++) {
				result += "0";
			}
		} else if (Yis0) {
			// 除数是0，结果返回正无穷
			result += "0";
			for (int i = 0; i < eLength; i++) {
				result += "1";
			}
			for (int i = 0; i < sLength; i++) {
				result += "0";
			}
		} else {
			// 被除数和除数都不为0

			String exponent = this
					.integerSubtraction(operand1.substring(1, eLength + 1), operand2.substring(1, eLength + 1), eLength)
					.substring(1);// 指数相减
			exponent = this.integerRepresentation(
					String.valueOf(
							Integer.parseInt(this.integerTrueValue(exponent)) + (int) (Math.pow(2, eLength - 1) - 1)),
					eLength);// 加上偏值
			// System.out.println("exponent:" + exponent);
			// 检查指数是否上溢
			boolean overflow = true;
			for (int i = 0; i < exponent.length(); i++) {
				if (exponent.charAt(i) == '0') {
					overflow = false;
				}
			}
			if (overflow) {
				// 报告上溢
				for (int i = 0; i < sLength; i++) {
					mantissa += "0";
				}
				result += "1";
				result += sign;
				result += exponent + mantissa;
			}
			// 检查指数是否下溢
			boolean underflow = true;
			for (int i = 0; i < exponent.length(); i++) {
				if (exponent.charAt(i) == '1') {
					underflow = false;
				}
			}
			if (underflow) {
				// 报告下溢
				for (int i = 0; i < sLength; i++) {
					mantissa += "0";
				}
				result += "0";
				result += sign;
				result += exponent + mantissa;
			}
			// 尾数相除
			String mantissa1 = "1" + operand1.substring(eLength + 1);
			String mantissa2 = "1" + operand2.substring(eLength + 1);
			String reminder = mantissa1;
			String divisor = mantissa2;
			String quotient = "";
			// 商补0
			while (quotient.length() != mantissa1.length()) {
				quotient += "0";
			}
			while (divisor.length() != mantissa2.length() * 2) {
				divisor += "0";
			}
			for (int i = 0; i < reminder.length(); i++) {
				if (Integer.parseInt(this.integerTrueValue(
						this.integerSubtraction(reminder, divisor, mantissa1.length()).substring(1))) >= 0) {
					// enough,reminder-divisor,添1左移
					reminder = this.integerSubtraction(reminder, divisor, mantissa1.length()).substring(1);
					quotient += "1";
					reminder = this.leftShift(reminder + quotient, 1).substring(0, mantissa1.length());
					quotient = this.leftShift(reminder + quotient, 1).substring(mantissa1.length(),
							(reminder + quotient).length() - 1);
					// System.out.println("enough reminder:" + reminder);
					// System.out.println("quotient:" + quotient);
				} else {
					// not enough,添0左移
					quotient += "0";
					reminder = this.leftShift(reminder + quotient, 1).substring(0, mantissa1.length());
					quotient = this.leftShift(reminder + quotient, 1).substring(mantissa1.length(),
							(reminder + quotient).length() - 1);
					// System.out.println("not reminder:" + reminder);
					// System.out.println("quotient:" + quotient);
				}
			}

			// 检查是否规格化
			if (quotient.charAt(0) == '1') {
				// 规格化了
				result += "0";
				result += sign;
				result += exponent + quotient.substring(1, sLength + 1);
			} else {
				// 没有规格化
				for (int i = 0; i < quotient.length(); i++) {
					if (quotient.charAt(0) != '1') {
						quotient = this.leftShift(quotient, 1);
						exponent = this.integerSubtraction(exponent, "0001", eLength).substring(1);// 指数减一
						if (Integer.parseInt(this.integerTrueValue(exponent)) == 0) {
							// 指数下溢
							mantissa = "";
							for (int i1 = 0; i1 < sLength; i1++) {
								mantissa += "0";
							}
							result += "0";
							result += sign;
							result += exponent + mantissa;
						} else {
							result += "0";
							result += sign;
							result += exponent + quotient.substring(1, sLength + 1);
							break;
						}
					}
				}
			}
		}
		// System.out.println(result);
		return result;
	}
}
