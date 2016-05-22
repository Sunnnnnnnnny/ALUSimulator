/**
 * ģ��ALU���������͸���������������
 * 
 * @author [151250121_ɣ��]
 *
 */

public class ALU {

	ALU myALU = new ALU();

	/**
	 * �������ţ����ţ������,ȡ��
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
		String integerPart = "";
		String doublePart = "";

		// ����λ
		if (number.startsWith("-")) {
			result += "1";
			integerPart = number.split(".")[0];
		} else {
			result += "0";
		}

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
		return null;
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
		return result;
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
	 * 4λ���н�λ�ӷ�����<br/>
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
		return null;
	}

	/**
	 * ��һ����ʵ�ֲ�������1�����㡣 ��Ҫģ��{@link #fullAdder(char, char, char) fullAdder}
	 * ��ʵ�֣��������Ե���{@link #fullAdder(char, char, char) fullAdder}��<br/>
	 * ����oneAdder("00001001")
	 * 
	 * @param operand
	 *            �����Ʋ����ʾ�Ĳ�����
	 * @return operand��1�Ľ��������Ϊoperand�ĳ��ȼ�1�����е�1λָʾ�Ƿ���������Ϊ1������Ϊ0��������λΪ��ӽ��
	 */
	public String oneAdder(String operand) {
		// TODO YOUR CODE HERE.
		return null;
	}

	/**
	 * �����ӷ���Ҫ�����{@link #claAdder(String, String, char) claAdder}����ʵ�֡�<br/>
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
		return null;
	}

	/**
	 * �����������ɵ���{@link #integerAddition(String, String, char, int)
	 * integerAddition}����ʵ�֡�<br/>
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
		return null;
	}

	/**
	 * �����˷���ʹ��Booth�㷨ʵ�֣��ɵ���{@link #integerAddition(String, String, char, int)
	 * integerAddition}�ȷ�����<br/>
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
		return null;
	}

	/**
	 * �����Ĳ��ָ������������ɵ���{@link #integerAddition(String, String, char, int)
	 * integerAddition}�ȷ���ʵ�֡�<br/>
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
		return null;
	}

	/**
	 * �����������ӷ���Ҫ�����{@link #integerAddition(String, String, int) integerAddition}
	 * ��{@link #integerSubtraction(String, String, int) integerSubtraction}
	 * �ȷ���ʵ�֡� �����ŵ�ȷ��������Ƿ���������Ҫ��������㷨���У�����ֱ��תΪ�����ʾ��������ת����<br/>
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
		return null;
	}

	/**
	 * �������ӷ����ɵ���{@link #integerAddition(String, String, char, int)
	 * intergerAddition}�ȷ���ʵ�֡�<br/>
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
	 * �������˷����ɵ���{@link #integerAddition(String, String, char, int)
	 * integerAddition}�ȷ���ʵ�֡�<br/>
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
	 * �������������ɵ���{@link #integerAddition(String, String, char, int)
	 * integerAddition}�ȷ���ʵ�֡�<br/>
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
