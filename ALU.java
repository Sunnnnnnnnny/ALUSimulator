/**
 * ģ��ALU���������͸���������������
 * 
 * @author [151250121_ɣ��]
 *
 */

public class ALU {

	ALU myALU = new ALU();

	/**
	 * �������ţ����ţ������
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
	 * ����ʮ���������Ķ����Ʋ����ʾ��<br/>
	 * ����integerRepresentation("9", 8)
	 * 
	 * @param number
	 *            ʮ������������Ϊ���������һλΪ��-������Ϊ������ 0�����޷���λ
	 * @param length
	 *            �����Ʋ����ʾ�ĳ���
	 * @return number�Ķ����Ʋ����ʾ������Ϊlength
	 */
	public String integerRepresentation(String number, int length) {
		// TODO YOUR CODE HERE.
		String result = "";
		if (number.startsWith("-")) {
			// ����Ǹ������������������
			String numberToexecute = number.substring(1);
			String myresult = "";
			int executeNumber = Integer.parseInt(numberToexecute);
			for (int i = 0; i < length; i++) {
				myresult += (executeNumber % 2);
				if (executeNumber != 0)
					executeNumber = executeNumber / 2;
			}
			myresult = new StringBuffer(myresult).reverse().toString();
			// ȡ����1
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
			// �����㷨
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
	 * ����ʮ���Ƹ������Ķ����Ʊ�ʾ�� ��Ҫ���� 0������񻯡����������+Inf���͡�-Inf������ NaN�����أ������� IEEE 754��
	 * �������Ϊ��0���롣<br/>
	 * ����floatRepresentation("11.375", 8, 11)
	 * 
	 * @param number
	 *            ʮ���Ƹ�����������С���㡣��Ϊ���������һλΪ��-������Ϊ������ 0�����޷���λ
	 * @param eLength
	 *            ָ���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param sLength
	 *            β���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @return number�Ķ����Ʊ�ʾ������Ϊ 1+eLength+sLength���������ң�����Ϊ���š�ָ���������ʾ����β������λ���أ�
	 */
	public String floatRepresentation(String number, int eLength, int sLength) {
		// TODO YOUR CODE HERE.
		String result = "";
		String decIntegerPart = "";// ʮ������������
		String decDoublePart = "";// ʮ����С������

		double decTrueValue = Double.parseDouble(number);
		// �����������Ϊ0
		if (decTrueValue == 0) {
			result += "0";
			for (int i = 0; i < eLength; i++) {
				result += "0";
			}
			for (int i = 0; i < sLength; i++) {
				result += "0";
			}
		} else if (decTrueValue > Math.pow(2, eLength - 1) * (2 - Math.pow(2, -sLength))) {
			// ������
			result += "0";
			for (int i = 0; i < eLength; i++) {
				result += "1";
			}
			for (int i = 0; i < sLength; i++) {
				result += "0";
			}

		} else if (decTrueValue < -(Math.pow(2, eLength - 1) * (2 - Math.pow(2, -sLength)))) {
			// ������
			result += "1";
			for (int i = 0; i < eLength; i++) {
				result += "1";
			}
			for (int i = 0; i < sLength; i++) {
				result += "0";
			}
		}

		// �ó�����λ�����ֳ��������ֺ�С������
		if (number.startsWith("-")) {
			result += "1";
			decIntegerPart = number.substring(1).split("\\.")[0];
			decDoublePart = number.substring(1).split("\\.")[1];
		} else {
			result += "0";
			decIntegerPart = number.split("\\.")[0];
			decDoublePart = number.split("\\.")[1];
		}

		// ����������������ֺ�С������
		String binIntegerPart = "";
		String binDoublePart = "";

		binIntegerPart += Integer.parseInt(decIntegerPart) % 2;
		return result;
	}

	/**
	 * ����ʮ���Ƹ�������IEEE 754��ʾ��Ҫ�����{@link #floatRepresentation(String, int, int)
	 * floatRepresentation}ʵ�֡�<br/>
	 * ����ieee754("11.375", 32)
	 * 
	 * @param number
	 *            ʮ���Ƹ�����������С���㡣��Ϊ���������һλΪ��-������Ϊ������ 0�����޷���λ
	 * @param length
	 *            �����Ʊ�ʾ�ĳ��ȣ�Ϊ32��64
	 * @return number��IEEE 754��ʾ������Ϊlength���������ң�����Ϊ���š�ָ���������ʾ����β������λ���أ�
	 */
	public String ieee754(String number, int length) {
		// TODO YOUR CODE HERE.
		String result = myALU.floatRepresentation(number, 8, 23);
		return result;
	}

	/**
	 * ��������Ʋ����ʾ����������ֵ��<br/>
	 * ����integerTrueValue("00001001")
	 * 
	 * @param operand
	 *            �����Ʋ����ʾ�Ĳ�����
	 * @return operand����ֵ����Ϊ���������һλΪ��-������Ϊ������ 0�����޷���λ
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
	 * ���������ԭ���ʾ�ĸ���������ֵ��<br/>
	 * ����floatTrueValue("01000001001101100000", 8, 11)
	 * 
	 * @param operand
	 *            �����Ʊ�ʾ�Ĳ�����
	 * @param eLength
	 *            ָ���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param sLength
	 *            β���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @return operand����ֵ����Ϊ���������һλΪ��-������Ϊ������ 0�����޷���λ����������ֱ��ʾΪ��+Inf���͡�-Inf����
	 *         NaN��ʾΪ��NaN��
	 */
	public String floatTrueValue(String operand, int eLength, int sLength) {
		// TODO YOUR CODE HERE.
		String result = "";
		int eLengthIs0 = 0, eLengthIs1 = 0, sLengthIs0 = 0, sLengthIs1 = 0;

		// ����ָ��Ϊ0,1�ĸ���
		for (int i = 1; i < eLength + 1; i++) {
			if (operand.charAt(i) == '0') {
				eLengthIs0++;
			} else if (operand.charAt(i) == '1') {
				eLengthIs1++;
			}
		}

		// ����β��Ϊ0,1�ĸ���
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
			String exponent = operand.substring(1, eLength + 1);// ָ������
			String mantissa = operand.substring(eLength + 1);// β������
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
				// ���ָ��Ϊ�����ֳ�������С������
				integerPart = "1" + mantissa.substring(0, exponentOfInteger);
				doublePart = mantissa.substring(exponentOfInteger);
			} else {
				// ���ָ��Ϊ������ǰ����0
				String toAdd = "";
				for (int i = 1; i < (-exponentOfInteger); i++) {
					toAdd += "0";
				}
				doublePart = toAdd + "1" + mantissa;
			}

			System.out.println(doublePart);

			int integerResult = 0;
			double doubleResult = 0;
			// ������������
			for (int i = 0; i < integerPart.length(); i++) {
				if (integerPart.charAt(i) == '1') {
					integerResult += Math.pow(2, integerPart.length() - i - 1);
				}
			}
			// ����С������
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
	 * ��λȡ��������<br/>
	 * ����negation("00001001")
	 * 
	 * @param operand
	 *            �����Ʊ�ʾ�Ĳ�����
	 * @return operand��λȡ���Ľ��
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
	 * ���Ʋ�����<br/>
	 * ����leftShift("00001001", 2)
	 * 
	 * @param operand
	 *            �����Ʊ�ʾ�Ĳ�����
	 * @param n
	 *            ���Ƶ�λ��
	 * @return operand����nλ�Ľ��
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
	 * �߼����Ʋ�����<br/>
	 * ����logRightShift("11110110", 2)
	 * 
	 * @param operand
	 *            �����Ʊ�ʾ�Ĳ�����
	 * @param n
	 *            ���Ƶ�λ��
	 * @return operand�߼�����nλ�Ľ��
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
	 * �������Ʋ�����<br/>
	 * ����logRightShift("11110110", 2)
	 * 
	 * @param operand
	 *            �����Ʊ�ʾ�Ĳ�����
	 * @param n
	 *            ���Ƶ�λ��
	 * @return operand��������nλ�Ľ��
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
	 * ȫ����������λ�Լ���λ���мӷ����㡣<br/>
	 * ����fullAdder('1', '1', '0')
	 * 
	 * @param x
	 *            ��������ĳһλ��ȡ0��1
	 * @param y
	 *            ������ĳһλ��ȡ0��1
	 * @param c
	 *            ��λ�Ե�ǰλ�Ľ�λ��ȡ0��1
	 * @return ��ӵĽ�����ó���Ϊ2���ַ�����ʾ����1λ��ʾ��λ����2λ��ʾ��
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
	 * 4λ���н�λ�ӷ�����Ҫ�����{@link #fullAdder(char, char, char) fullAdder}��ʵ��<br/>
	 * ����claAdder("1001", "0001", '1')
	 * 
	 * @param operand1
	 *            4λ�����Ʊ�ʾ�ı�����
	 * @param operand2
	 *            4λ�����Ʊ�ʾ�ļ���
	 * @param c
	 *            ��λ�Ե�ǰλ�Ľ�λ��ȡ0��1
	 * @return ����Ϊ5���ַ�����ʾ�ļ����������е�1λ�����λ��λ����4λ����ӽ�������н�λ��������ѭ�����
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
	 * ��һ����ʵ�ֲ�������1�����㡣 ��Ҫ�������š����š�����ŵ�ģ�⣬ ������ֱ�ӵ���
	 * {@link #fullAdder(char, char, char) fullAdder}��
	 * {@link #claAdder(String, String, char) claAdder}��
	 * {@link #adder(String, String, char, int) adder}��
	 * {@link #integerAddition(String, String, int) integerAddition}������<br/>
	 * ����oneAdder("00001001")
	 * 
	 * @param operand
	 *            �����Ʋ����ʾ�Ĳ�����
	 * @return operand��1�Ľ��������Ϊoperand�ĳ��ȼ�1�����е�1λָʾ�Ƿ���������Ϊ1������Ϊ0��������λΪ��ӽ��
	 */
	public String oneAdder(String operand) {
		// TODO YOUR CODE HERE.
		String s = "";
		String toAdd = "";
		char c = '0';// ��λ
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
	 * �ӷ�����Ҫ�����{@link #claAdder(String, String, char)}����ʵ�֡�<br/>
	 * ����adder("0100", "0011", ��0��, 8)
	 * 
	 * @param operand1
	 *            �����Ʋ����ʾ�ı�����
	 * @param operand2
	 *            �����Ʋ����ʾ�ļ���
	 * @param c
	 *            ���λ��λ
	 * @param length
	 *            ��Ų������ļĴ����ĳ��ȣ�Ϊ4�ı�����length��С�ڲ������ĳ��ȣ���ĳ���������ĳ���С��lengthʱ��
	 *            ��Ҫ�ڸ�λ������λ
	 * @return ����Ϊlength+1���ַ�����ʾ�ļ����������е�1λָʾ�Ƿ���������Ϊ1������Ϊ0������lengthλ����ӽ��
	 */
	public String adder(String operand1, String operand2, char c, int length) {
		// TODO YOUR CODE HERE.
		String toAdd = "";
		char Ci = c, Cj = c;
		// ������������������
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
	 * �����ӷ���Ҫ�����{@link #adder(String, String, char, int) adder}����ʵ�֡�<br/>
	 * ����integerAddition("0100", "0011", 8)
	 * 
	 * @param operand1
	 *            �����Ʋ����ʾ�ı�����
	 * @param operand2
	 *            �����Ʋ����ʾ�ļ���
	 * @param length
	 *            ��Ų������ļĴ����ĳ��ȣ�Ϊ4�ı�����length��С�ڲ������ĳ��ȣ���ĳ���������ĳ���С��lengthʱ��
	 *            ��Ҫ�ڸ�λ������λ
	 * @return ����Ϊlength+1���ַ�����ʾ�ļ����������е�1λָʾ�Ƿ���������Ϊ1������Ϊ0������lengthλ����ӽ��
	 */
	public String integerAddition(String operand1, String operand2, int length) {
		// TODO YOUR CODE HERE.
		String result = myALU.adder(operand1, operand2, '0', length);
		return result;
	}

	/**
	 * �����������ɵ���{@link #adder(String, String, char, int) adder}����ʵ�֡�<br/>
	 * ����integerSubtraction("0100", "0011", 8)
	 * 
	 * @param operand1
	 *            �����Ʋ����ʾ�ı�����
	 * @param operand2
	 *            �����Ʋ����ʾ�ļ���
	 * @param length
	 *            ��Ų������ļĴ����ĳ��ȣ�Ϊ4�ı�����length��С�ڲ������ĳ��ȣ���ĳ���������ĳ���С��lengthʱ��
	 *            ��Ҫ�ڸ�λ������λ
	 * @return ����Ϊlength+1���ַ�����ʾ�ļ����������е�1λָʾ�Ƿ���������Ϊ1������Ϊ0������lengthλ��������
	 */
	public String integerSubtraction(String operand1, String operand2, int length) {
		// TODO YOUR CODE HERE.
		operand2 = myALU.negation(operand2);
		operand2 = myALU.oneAdder(operand2).substring(1);
		String result = myALU.adder(operand1, operand2, '0', length);
		return result;
	}

	/**
	 * �����˷���ʹ��Booth�㷨ʵ�֣��ɵ���{@link #adder(String, String, char, int) adder}�ȷ�����
	 * <br/>
	 * ����integerMultiplication("0100", "0011", 8)
	 * 
	 * @param operand1
	 *            �����Ʋ����ʾ�ı�����
	 * @param operand2
	 *            �����Ʋ����ʾ�ĳ���
	 * @param length
	 *            ��Ų������ļĴ����ĳ��ȣ�Ϊ4�ı�����length��С�ڲ������ĳ��ȣ���ĳ���������ĳ���С��lengthʱ��
	 *            ��Ҫ�ڸ�λ������λ
	 * @return ����Ϊlength+1���ַ�����ʾ����˽�������е�1λָʾ�Ƿ���������Ϊ1������Ϊ0������lengthλ����˽��
	 */
	public String integerMultiplication(String operand1, String operand2, int length) {
		// TODO YOUR CODE HERE.
		// ��������������
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
		String Y = operand2 + "0";// ������0��Y0=0
		String _X = myALU.oneAdder(myALU.negation(operand1)).substring(1);// ��ñ������Ĳ���

		for (int i = 0; i < operand1.length(); i++) {
			result += "0";
		}

		for (int i = 0; i < X.length(); i++) {

			if (Y.charAt(Y.length() - 1) == Y.charAt(Y.length() - 2)) {
				// Y0-Y1=0,ֱ������
				rightShift = myALU.ariRightShift(result + Y, 1);
				result = rightShift.substring(0, X.length());
				Y = rightShift.substring(X.length());
			} else if (Y.charAt(Y.length() - 1) > Y.charAt(Y.length() - 2)) {
				// Y0-Y1=1,result+X������
				result = myALU.integerAddition(result, X, X.length()).substring(1);
				rightShift = myALU.ariRightShift(result + Y, 1);
				result = rightShift.substring(0, X.length());
				Y = rightShift.substring(X.length());
			} else {
				// Y0-Y1=-1,result+(-X)������
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
			// ���������ȫ����λ
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
	 * �����Ĳ��ָ������������ɵ���{@link #adder(String, String, char, int) adder}�ȷ���ʵ�֡�<br/>
	 * ����integerDivision("0100", "0011", 8)
	 * 
	 * @param operand1
	 *            �����Ʋ����ʾ�ı�����
	 * @param operand2
	 *            �����Ʋ����ʾ�ĳ���
	 * @param length
	 *            ��Ų������ļĴ����ĳ��ȣ�Ϊ4�ı�����length��С�ڲ������ĳ��ȣ���ĳ���������ĳ���С��lengthʱ��
	 *            ��Ҫ�ڸ�λ������λ
	 * @return ����Ϊ2*length+1���ַ�����ʾ�������������е�1λָʾ�Ƿ���������Ϊ1������Ϊ0�������lengthλΪ�̣�
	 *         ���lengthλΪ����
	 */
	public String integerDivision(String operand1, String operand2, int length) {
		// TODO YOUR CODE HERE.
		// ��������������
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
				// �����ͳ������Ų�ͬ�����ӷ�
				reminder = myALU.integerAddition(reminder, divisor, reminder.length()).substring(1);
			} else {
				// �����ͳ���������ͬ��������
				reminder = myALU.integerSubtraction(reminder, divisor, reminder.length()).substring(1);
			}

			if (reminder.charAt(0) == divisor.charAt(0)) {
				// ��������������ͬ���̺�1
				quotient += "1";
			} else {
				// ����0
				quotient += "0";
			}
			leftShift = myALU.leftShift(reminder + quotient, 1);// ����
			reminder = leftShift.substring(0, reminder.length());
			quotient = leftShift.substring(reminder.length(), leftShift.length() - 1);
		}

		if (divisor.charAt(0) != reminder.charAt(0)) {
			// �����ͳ������Ų�ͬ�����ӷ�
			reminder = myALU.integerAddition(reminder, divisor, reminder.length()).substring(1);
		} else {
			// �����ͳ���������ͬ��������
			reminder = myALU.integerSubtraction(reminder, divisor, reminder.length()).substring(1);
		}
		if (reminder.charAt(0) == divisor.charAt(0)) {
			// ��������������ͬ���̺�1
			quotient += "1";
		} else {
			// ����0
			quotient += "0";
		}

		// ������
		quotient = quotient.substring(1);
		if (operand1.charAt(0) != operand2.charAt(0)) {
			// ������������������෴����+1
			quotient = myALU.oneAdder(quotient).substring(1);
		}
		if (quotient.charAt(0) != operand1.charAt(0)) {
			// �������ͱ��������Ų�ͬ
			if (reminder.charAt(0) == divisor.charAt(0)) {
				// ������������������ͬ�������������
				reminder = myALU.integerSubtraction(reminder, divisor, reminder.length()).substring(1);
			} else {
				// �������ӷ�
				reminder = myALU.integerAddition(reminder, divisor, reminder.length()).substring(1);
			}
		}

		String result = "0" + reminder + quotient;

		return result;

	}

	/**
	 * �����������ӷ������Ե���{@link #adder(String, String, char, int) adder}�ȷ�����
	 * ������ֱ�ӽ�������ת��Ϊ�����ʹ��{@link #integerAddition(String, String, int)
	 * integerAddition}�� {@link #integerSubtraction(String, String, int)
	 * integerSubtraction}��ʵ�֡�<br/>
	 * ����signedAddition("1100", "1011", 8)
	 * 
	 * @param operand1
	 *            ������ԭ���ʾ�ı����������е�1λΪ����λ
	 * @param operand2
	 *            ������ԭ���ʾ�ļ��������е�1λΪ����λ
	 * @param length
	 *            ��Ų������ļĴ����ĳ��ȣ�Ϊ4�ı�����length��С�ڲ������ĳ��ȣ����������ţ�����ĳ���������ĳ���С��lengthʱ��
	 *            ��Ҫ���䳤����չ��length
	 * @return ����Ϊlength+2���ַ�����ʾ�ļ����������е�1λָʾ�Ƿ���������Ϊ1������Ϊ0������2λΪ����λ��
	 *         ��lengthλ����ӽ��
	 */
	public String signedAddition(String operand1, String operand2, int length) {
		// TODO YOUR CODE HERE.
		// ��������������
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
	 * �������ӷ����ɵ���{@link #signedAddition(String, String, int) signedAddition}
	 * �ȷ���ʵ�֡�<br/>
	 * ����floatAddition("00111111010100000", "00111111001000000", 8, 8, 8)
	 * 
	 * @param operand1
	 *            �����Ʊ�ʾ�ı�����
	 * @param operand2
	 *            �����Ʊ�ʾ�ļ���
	 * @param eLength
	 *            ָ���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param sLength
	 *            β���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param gLength
	 *            ����λ�ĳ���
	 * @return ����Ϊ2+eLength+sLength���ַ�����ʾ����ӽ�������е�1λָʾ�Ƿ�ָ�����磨���Ϊ1������Ϊ0����
	 *         ����λ����������Ϊ���š�ָ���������ʾ����β������λ���أ����������Ϊ��0����
	 */
	public String floatAddition(String operand1, String operand2, int eLength, int sLength, int gLength) {
		// TODO YOUR CODE HERE.
		return null;
	}

	/**
	 * �������������ɵ���{@link #floatAddition(String, String, int, int, int)
	 * floatAddition}����ʵ�֡�<br/>
	 * ����floatSubtraction("00111111010100000", "00111111001000000", 8, 8, 8)
	 * 
	 * @param operand1
	 *            �����Ʊ�ʾ�ı�����
	 * @param operand2
	 *            �����Ʊ�ʾ�ļ���
	 * @param eLength
	 *            ָ���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param sLength
	 *            β���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param gLength
	 *            ����λ�ĳ���
	 * @return ����Ϊ2+eLength+sLength���ַ�����ʾ�������������е�1λָʾ�Ƿ�ָ�����磨���Ϊ1������Ϊ0����
	 *         ����λ����������Ϊ���š�ָ���������ʾ����β������λ���أ����������Ϊ��0����
	 */
	public String floatSubtraction(String operand1, String operand2, int eLength, int sLength, int gLength) {
		// TODO YOUR CODE HERE.
		return null;
	}

	/**
	 * �������˷����ɵ���{@link #integerMultiplication(String, String, int)
	 * integerMultiplication}�ȷ���ʵ�֡�<br/>
	 * ����floatMultiplication("00111110111000000", "00111111000000000", 8, 8)
	 * 
	 * @param operand1
	 *            �����Ʊ�ʾ�ı�����
	 * @param operand2
	 *            �����Ʊ�ʾ�ĳ���
	 * @param eLength
	 *            ָ���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param sLength
	 *            β���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @return ����Ϊ2+eLength+sLength���ַ�����ʾ����˽��,���е�1λָʾ�Ƿ�ָ�����磨���Ϊ1������Ϊ0����
	 *         ����λ����������Ϊ���š�ָ���������ʾ����β������λ���أ����������Ϊ��0����
	 */
	public String floatMultiplication(String operand1, String operand2, int eLength, int sLength) {
		// TODO YOUR CODE HERE.
		return null;
	}

	/**
	 * �������������ɵ���{@link #integerDivision(String, String, int) integerDivision}
	 * �ȷ���ʵ�֡�<br/>
	 * ����floatDivision("00111110111000000", "00111111000000000", 8, 8)
	 * 
	 * @param operand1
	 *            �����Ʊ�ʾ�ı�����
	 * @param operand2
	 *            �����Ʊ�ʾ�ĳ���
	 * @param eLength
	 *            ָ���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param sLength
	 *            β���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @return ����Ϊ2+eLength+sLength���ַ�����ʾ����˽��,���е�1λָʾ�Ƿ�ָ�����磨���Ϊ1������Ϊ0����
	 *         ����λ����������Ϊ���š�ָ���������ʾ����β������λ���أ����������Ϊ��0����
	 */
	public String floatDivision(String operand1, String operand2, int eLength, int sLength) {
		// TODO YOUR CODE HERE.
		return null;
	}
}
