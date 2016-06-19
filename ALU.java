/**
 * ģ��ALU���������͸���������������
 * 
 * @author [151250121_ɣ��]
 *
 */

public class ALU {

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
		int decIntegerPart;// ʮ������������
		double decDoublePart;// ʮ����С������
		String mantissa = "";// β������
		String binIntegerPart = "";// ��������������
		String binDoublePart = "";// ������С������

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
		} else if (decTrueValue > Math.pow(2, Math.pow(2, eLength)) * (2 - Math.pow(2, -sLength))) {
			// ������
			result += "0";
			for (int i = 0; i < eLength; i++) {
				result += "1";
			}
			for (int i = 0; i < sLength; i++) {
				result += "0";
			}

		} else if (decTrueValue < -((Math.pow(2, Math.pow(2, eLength)) * (2 - Math.pow(2, -sLength))))) {
			// ������
			result += "1";
			for (int i = 0; i < eLength; i++) {
				result += "1";
			}
			for (int i = 0; i < sLength; i++) {
				result += "0";
			}
		} else if (decTrueValue < Math.pow(2, Math.pow(2, 1 - eLength))
				&& decTrueValue > -Math.pow(2, Math.pow(2, 1 - eLength))) {
			// �����
			if (decTrueValue > 0) {
				result += "0";
			} else {
				result += "1";
			}
			for (int i = 0; i < eLength; i++) {
				result += "0";
			}
			// ����β��
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
			// ����β������
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
			// �ó�����λ�����ֳ��������ֺ�С������
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
				// �����������������
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
			// ����ָ����β��
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
			// ����β������
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
		String result = this.floatRepresentation(number, 8, 23);
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
				if (operand.charAt(i) == '1')
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

		// �ж��Ƿ�Ϊ0
		boolean operandIs0 = true;
		for (int i = 0; i < operand.length(); i++) {
			if (operand.charAt(i) == '1')
				operandIs0 = false;
		}
		if (operandIs0)
			return "0";
		else {
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
		S = this.xor(x, this.xor(y, c));
		Ci = this.or(this.or(this.and(x, y), this.and(x, c)), this.and(y, c));
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
		String result = "";
		char Ci = '0', Cj = '0';// ��λ
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
		String result = this.adder(operand1, operand2, '0', length);
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
		operand2 = this.negation(operand2);
		operand2 = this.oneAdder(operand2).substring(1);
		String result = this.adder(operand1, operand2, '0', length);
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
		String Y = operand2 + "0";// ������0��Y0=0
		String _X = this.oneAdder(this.negation(operand1)).substring(1);// ��ñ������Ĳ���

		for (int i = 0; i < operand1.length(); i++) {
			result += "0";
		}

		for (int i = 0; i < X.length(); i++) {

			if (Y.charAt(Y.length() - 1) == Y.charAt(Y.length() - 2)) {
				// Y0-Y1=0,ֱ������
				rightShift = this.ariRightShift(result + Y, 1);
				result = rightShift.substring(0, X.length());
				Y = rightShift.substring(X.length());
			} else if (Y.charAt(Y.length() - 1) > Y.charAt(Y.length() - 2)) {
				// Y0-Y1=1,result+X������
				result = this.integerAddition(result, X, X.length()).substring(1);
				// if (Integer.parseInt(myALU.integerTrueValue("0"+result)) >
				// Math.pow(2, X.length() - 1) - 1) {
				// // result+X������ʾ��Χ
				// overflow="1";
				// }
				rightShift = this.ariRightShift(result + Y, 1);
				result = rightShift.substring(0, X.length());
				Y = rightShift.substring(X.length());
			} else {
				// Y0-Y1=-1,result+(-X)������
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
				// �����ͳ������Ų�ͬ�����ӷ�
				reminder = this.integerAddition(reminder, divisor, reminder.length()).substring(1);
			} else {
				// �����ͳ���������ͬ��������
				reminder = this.integerSubtraction(reminder, divisor, reminder.length()).substring(1);
			}

			if (reminder.charAt(0) == divisor.charAt(0)) {
				// ��������������ͬ���̺�1
				quotient += "1";
			} else {
				// ����0
				quotient += "0";
			}
			leftShift = this.leftShift(reminder + quotient, 1);// ����
			reminder = leftShift.substring(0, reminder.length());
			quotient = leftShift.substring(reminder.length(), leftShift.length() - 1);
		}

		if (divisor.charAt(0) != reminder.charAt(0)) {
			// �����ͳ������Ų�ͬ�����ӷ�
			reminder = this.integerAddition(reminder, divisor, reminder.length()).substring(1);
		} else {
			// �����ͳ���������ͬ��������
			reminder = this.integerSubtraction(reminder, divisor, reminder.length()).substring(1);
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
			quotient = this.oneAdder(quotient).substring(1);
		}
		if (reminder.charAt(0) != operand1.charAt(0)) {
			// �������ͱ��������Ų�ͬ
			if (reminder.charAt(0) == divisor.charAt(0)) {
				// ������������������ͬ�������������
				reminder = this.integerSubtraction(reminder, divisor, reminder.length()).substring(1);
			} else {
				// �������ӷ�
				reminder = this.integerAddition(reminder, divisor, reminder.length()).substring(1);
			}
		}

		String result = "0" + quotient + reminder;

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
		boolean exception = false;
		String newOperand1 = operand1.substring(1);
		String newOperand2 = operand2.substring(1);
		// ��������������
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
			// ����������������ͬ��ȥ������λ���ӷ�
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
			// X��Y���
			if (operand1.substring(1).equals(operand2.substring(1))) {
				for (int i = 0; i < length + 2; i++) {
					result += "0";
				}
			} else {
				newOperand2 = this.oneAdder(this.negation(newOperand2)).substring(1);// ��Yȡ����һ
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
					// �н�λ���ѽ�λȥ���������뱻������ͬ
					result = "0" + operand1.substring(0, 1) + result;
				} else {
					// �޽�λ���ѽ��ȡ����һ�������뱻�����෴
					result = this.oneAdder(this.negation(result)).substring(1);
					result = "0" + String.valueOf(1 - Integer.parseInt(operand1.substring(0, 1))) + result;
				}
			}

		}
		if (exception) {
			if (result.charAt(2) != operand1.charAt(0)) {
				// ���
				return "1" + result.substring(1, 2) + result.substring(result.length() - (length - 1));
			} else {
				return "0" + result.substring(1, 2) + result.substring(result.length() - (length - 1));
			}

		} else {
			return result;
		}

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
		boolean Xis0 = true;
		boolean Yis0 = true;
		boolean normalExcute = false;// �Ƿ������������
		String mantissa1 = "1" + operand1.substring(eLength + 1);
		String mantissa2 = "1" + operand2.substring(eLength + 1);// β��ǰ��1
		int exponent1 = Integer.parseInt(this.integerTrueValue("0" + operand1.substring(1, eLength + 1)));
		int exponent2 = Integer.parseInt(this.integerTrueValue("0" + operand2.substring(1, eLength + 1)));
		String exponent = "";
		String result = "";

		// ���ݱ���λ��չβ��
		for (int i = 0; i < gLength; i++) {
			mantissa1 += "0";
			mantissa2 += "0";
		}

		// ��鱻����X�Ƿ����0
		for (int i = 1; i < operand1.length(); i++) {
			if (operand1.charAt(i) == '1') {
				Xis0 = false;
				break;
			}
		}
		if (Xis0) {
			// ��X=0��Z=Y
			result = "0" + operand2;
			return result;
		} else {
			// X��0��������Y�Ƿ����0
			for (int i = 1; i < operand2.length(); i++) {
				if (operand2.charAt(i) == '1') {
					Yis0 = false;
					break;
				}
			}
			if (Yis0) {
				// ��Y=0��Z=X
				result = "0" + operand1;
				return result;
			} else {
				// X��Y����0
				normalExcute = true;
			}
		}

		if (normalExcute) {
			// �Ƚ�ָ�������Ƿ����
			if (exponent1 != exponent2) {
				// ָ������ȣ�������С��ָ����������Ӧβ��
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
							// ���ƺ�β��=0
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
							// ���ƺ�β��=0
							result = "0" + operand2;
							return result;
						}
					}
					exponent1 = exponent2;
				}
			}

			exponent = this.integerRepresentation(String.valueOf(exponent1), eLength);

			// ����ָ����ȣ����Ѿ������
			String significand = "";
			boolean continuing1 = false, continuing2 = false;
			significand = this.signedAddition(operand1.charAt(0) + mantissa1, operand2.charAt(0) + mantissa2,
					mantissa1.length());
			char overflow = significand.charAt(0);// β�����λ
			char sign = significand.charAt(1);// β������λ
			significand = significand.substring(2);

			int num = 0;
			for (int i = 0; i < significand.length(); i++) {
				if (significand.charAt(i) == '0') {
					num++;
				}
			}
			if (num == significand.length()) {
				// β��=0,Z=0
				for (int i = 0; i < 1 + eLength + sLength; i++) {
					result += "0";
				}
			} else if (overflow == '1') {
				// β�����
				continuing1 = true;
				significand = "1" + this.logRightShift(significand, 1).substring(1);// ����β��,��һλ��1
				exponent = this.oneAdder(this.integerRepresentation(String.valueOf(exponent1), eLength)).substring(1);// ָ��+1
				int numOf1 = 0;
				for (int i = 0; i < exponent.length(); i++) {
					if (exponent.charAt(i) == '1')
						numOf1++;
				}
				if (numOf1 == exponent.length()) {
					// ָ������
					String mantissa = "";
					// β������
					for (int i = 0; i < sLength; i++) {
						mantissa += "0";
					}
					result += "1";// ��������
					result += sign;
					result += exponent + mantissa;
				} else {
					result += "0";
					result += sign;
					result += exponent + significand.substring(1, sLength + 1);
				}
			} else if (!continuing1 || continuing2) {
				// ����Ƿ���
				exponent = this.integerRepresentation(String.valueOf(exponent1), eLength);
				for (int i = 0; i < significand.length(); i++) {
					if (significand.charAt(0) != '1') {
						// û�й�񻯣�����β��
						significand = this.leftShift(significand, 1);
						// ָ����һ
						exponent = this.integerSubtraction(exponent, "0001", eLength).substring(1);
						// ���ָ���Ƿ�����
						int numOf0 = 0;
						for (int j = 0; j < exponent.length(); j++) {
							if (exponent.charAt(j) == '0')
								numOf0++;
						}
						if (numOf0 == exponent.length()) {
							// ָ������
							String mantissa = "";
							// β������
							for (int z = 0; z < sLength; z++) {
								mantissa += "0";
							}
							result += "0";
							result += sign;
							result += exponent + mantissa;
							break;
						}
					} else {
						// ��������
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
		operand2 = this.negation(operand2.substring(0, 1)) + operand2.substring(1);
		String result = this.floatAddition(operand1, operand2, eLength, sLength, gLength);
		return result;
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
		String result = "";
		String mantissa = "";
		char sign = this.xor(operand1.charAt(0), operand2.charAt(0));// ����λ�����
		// �ж�X��Y�Ƿ�Ϊ0
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
			// X��Y��һ����0
			for (int i = 0; i < eLength + sLength + 2; i++) {
				result += "0";
			}
		} else {
			// X��Y����Ϊ0
			String exponent = this.signedAddition("0" + operand1.substring(1, eLength + 1),
					"0" + operand2.substring(1, eLength + 1), eLength).substring(2);
			exponent = this.integerRepresentation(
					String.valueOf(
							Integer.parseInt(this.integerTrueValue(exponent)) - (int) (Math.pow(2, eLength - 1) - 1)),
					eLength);// ��ȥƫֵ
			// ���ָ���Ƿ�����
			boolean overflow = true;
			for (int i = 0; i < exponent.length(); i++) {
				if (exponent.charAt(i) == '0') {
					overflow = false;
				}
			}
			if (overflow) {
				// ��������
				mantissa = "";
				for (int i = 0; i < sLength; i++) {
					mantissa += "0";
				}
				result += "1";
				result += sign;
				result += exponent + mantissa;
			}
			// ���ָ���Ƿ�����
			boolean underflow = true;
			for (int i = 0; i < exponent.length(); i++) {
				if (exponent.charAt(i) == '1') {
					underflow = false;
				}
			}
			if (underflow) {
				// ��������
				mantissa = "";
				for (int i = 0; i < sLength; i++) {
					mantissa += "0";
				}
				result += "0";
				result += sign;
				result += exponent + mantissa;
			}
			// β�����
			String mantissa1 = "1" + operand1.substring(eLength + 1);
			String mantissa2 = "1" + operand2.substring(eLength + 1);
			String multiMantissa = this.integerMultiplication("0" + mantissa1, "0" + mantissa2, 2 * (sLength + 2));// β��ǰ��һλ��0����ʾ�������
			char over = multiMantissa.charAt(0);// ���λ
			multiMantissa = multiMantissa.substring(3);// ȥ����ʾ���ŵ���λ��0��

			if (multiMantissa.charAt(0) == '1') {
				// ��һλΪ1��С����Ҫ����
				exponent = this.oneAdder(exponent).substring(1);// ָ����һ
				// ���ָ���Ƿ�����
				boolean overflow1 = true;
				for (int i1 = 0; i1 < exponent.length(); i1++) {
					if (exponent.charAt(i1) == '0') {
						overflow1 = false;
					}
				}
				if (overflow1) {
					// ��������
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
						// û�й�񻯣�����β��
						multiMantissa = this.leftShift(multiMantissa, 1);
						exponent = this.integerSubtraction(exponent, "0001", eLength).substring(1);// ָ����һ
						// ���ָ���Ƿ�����
						boolean underflow1 = true;
						for (int i1 = 0; i1 < exponent.length(); i1++) {
							if (exponent.charAt(i1) == '1') {
								underflow1 = false;
							}
						}
						if (underflow1) {
							// ��������
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
		String result = "";
		String mantissa = "";
		char sign = this.xor(operand1.charAt(0), operand2.charAt(0));// ����λ�����
		// �ж�X��Y�Ƿ�Ϊ0
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
			// ��������0���������0
			for (int i = 0; i < eLength + sLength + 2; i++) {
				result += "0";
			}
		} else if (Yis0) {
			// ������0���������������
			result += "0";
			for (int i = 0; i < eLength; i++) {
				result += "1";
			}
			for (int i = 0; i < sLength; i++) {
				result += "0";
			}
		} else {
			// �������ͳ�������Ϊ0

			String exponent = this
					.integerSubtraction(operand1.substring(1, eLength + 1), operand2.substring(1, eLength + 1), eLength)
					.substring(1);// ָ�����
			exponent = this.integerRepresentation(
					String.valueOf(
							Integer.parseInt(this.integerTrueValue(exponent)) + (int) (Math.pow(2, eLength - 1) - 1)),
					eLength);// ����ƫֵ
			// System.out.println("exponent:" + exponent);
			// ���ָ���Ƿ�����
			boolean overflow = true;
			for (int i = 0; i < exponent.length(); i++) {
				if (exponent.charAt(i) == '0') {
					overflow = false;
				}
			}
			if (overflow) {
				// ��������
				for (int i = 0; i < sLength; i++) {
					mantissa += "0";
				}
				result += "1";
				result += sign;
				result += exponent + mantissa;
			}
			// ���ָ���Ƿ�����
			boolean underflow = true;
			for (int i = 0; i < exponent.length(); i++) {
				if (exponent.charAt(i) == '1') {
					underflow = false;
				}
			}
			if (underflow) {
				// ��������
				for (int i = 0; i < sLength; i++) {
					mantissa += "0";
				}
				result += "0";
				result += sign;
				result += exponent + mantissa;
			}
			// β�����
			String mantissa1 = "1" + operand1.substring(eLength + 1);
			String mantissa2 = "1" + operand2.substring(eLength + 1);
			String reminder = mantissa1;
			String divisor = mantissa2;
			String quotient = "";
			// �̲�0
			while (quotient.length() != mantissa1.length()) {
				quotient += "0";
			}
			while (divisor.length() != mantissa2.length() * 2) {
				divisor += "0";
			}
			for (int i = 0; i < reminder.length(); i++) {
				if (Integer.parseInt(this.integerTrueValue(
						this.integerSubtraction(reminder, divisor, mantissa1.length()).substring(1))) >= 0) {
					// enough,reminder-divisor,��1����
					reminder = this.integerSubtraction(reminder, divisor, mantissa1.length()).substring(1);
					quotient += "1";
					reminder = this.leftShift(reminder + quotient, 1).substring(0, mantissa1.length());
					quotient = this.leftShift(reminder + quotient, 1).substring(mantissa1.length(),
							(reminder + quotient).length() - 1);
					// System.out.println("enough reminder:" + reminder);
					// System.out.println("quotient:" + quotient);
				} else {
					// not enough,��0����
					quotient += "0";
					reminder = this.leftShift(reminder + quotient, 1).substring(0, mantissa1.length());
					quotient = this.leftShift(reminder + quotient, 1).substring(mantissa1.length(),
							(reminder + quotient).length() - 1);
					// System.out.println("not reminder:" + reminder);
					// System.out.println("quotient:" + quotient);
				}
			}

			// ����Ƿ���
			if (quotient.charAt(0) == '1') {
				// �����
				result += "0";
				result += sign;
				result += exponent + quotient.substring(1, sLength + 1);
			} else {
				// û�й��
				for (int i = 0; i < quotient.length(); i++) {
					if (quotient.charAt(0) != '1') {
						quotient = this.leftShift(quotient, 1);
						exponent = this.integerSubtraction(exponent, "0001", eLength).substring(1);// ָ����һ
						if (Integer.parseInt(this.integerTrueValue(exponent)) == 0) {
							// ָ������
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
