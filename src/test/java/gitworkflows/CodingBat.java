package gitworkflows;

public class CodingBat {

	public static void main(String[] args) {
		System.out.println(CodingBat.gHappy("xxggyygxx"));
	}

	public static boolean gHappy(String str) {

		char[] arr = str.toCharArray();
		int len = arr.length;

		if (len == 1)
			return false;

		if(len ==0)
			return true;
		
		int gCount = 0;
		boolean countStarted = false;

		// xxggyygxx
		for (int i = 0; i < len; i++) {
			switch (arr[i]) {
				case 'g':
					if (!countStarted) {
						countStarted = true;
					}
					gCount++;
					break;
				default:
					if (countStarted && gCount == 1) {
						return false;
					} else {
						countStarted = false;
						gCount = 0;
					}
					break;
			}
		}
		if (gCount == 1) {
			return false;
		} else {
			return true;
		}

	}

}
