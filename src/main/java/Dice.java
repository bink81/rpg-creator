import java.io.Serializable;
import java.util.Random;

// Popular dice throwing representation, only one Dice object in the who application
// possible expressions for example: 4, 4d10, 3d1 + 4	

public class Dice implements Serializable {
	private static final long serialVersionUID = 1L;
	Random generator;

	public Dice() {
		// initialising
		generator = new Random();
	}

	// throw the dice
	int throwDice(String strValue) {
		int result = 0, index, tempInt = 0;
		String tempStr;

		// detect errors
		if (strValue == null)
			return 0;
		if (strValue.equals(""))
			return 0;

		// detect expressing parts
		for (int i = 0; i < strValue.length(); i++)
			if ((strValue.charAt(i) > '0') || (strValue.charAt(i) < '9') || (strValue.charAt(i) == '+')
					|| (strValue.charAt(i) == '-') || (strValue.charAt(i) == 'd') || (strValue.charAt(i) == ' '))
				;
			else {
				return 0;
			}

		// last (constant) part of expression
		boolean plus = false;
		index = strValue.indexOf('+');
		if (index == -1)
			index = strValue.indexOf('-');
		else
			plus = true;
		if (index != -1) {
			tempStr = strValue.substring(index + 1, strValue.length()).trim();
			try {
				tempInt = Integer.valueOf(tempStr).intValue();
				if (plus)
					result += tempInt;
				else
					result -= tempInt;
			} catch (NumberFormatException E) {
				System.out.println(E);
				return 0;
			}
			strValue = strValue.substring(0, index).trim();
		}

		// probable part of expression
		index = strValue.indexOf('d');
		if (index != -1) {
			int multipler, randomizer;
			tempStr = strValue.substring(0, index).trim();
			try {
				multipler = Integer.valueOf(tempStr).intValue();
			} catch (NumberFormatException E) {
				System.out.println(E);
				return 0;
			}
			tempStr = strValue.substring(index + 1, strValue.length()).trim();
			try {
				randomizer = Integer.valueOf(tempStr).intValue();
			} catch (NumberFormatException E) {
				System.out.println(E);
				return 0;
			}

			// main computation
			for (int i = 0; i < multipler; i++) {
				result += generator.nextInt(randomizer) + 1;
			}
		} else {
			try {
				tempInt = Integer.valueOf(strValue).intValue();
				result += tempInt;
			} catch (NumberFormatException E) {
				System.out.println(E);
				return 0;
			}
		}
		return result;
	}
}
