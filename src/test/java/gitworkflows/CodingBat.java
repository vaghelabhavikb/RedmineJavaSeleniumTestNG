package gitworkflows;

public class CodingBat {

	public static void main(String[] args) {
		System.out.println(CodingBat.countTriple("xxxabyyyycd"));
	}
	
	public static int countTriple(String str) {
		
		char[] arr = str.toCharArray();
		int len = arr.length;
		
		if (len == 0) 
			return 0;
		
		int totalCount = 0;
		int counter = 1;
		char lastChar = arr[0];
		
		for(int i=1; i<len; i++) {
			if(lastChar == arr[i]) {
				counter++;
				if (counter == 3) {
					totalCount++;
					counter--;
				}
			} else {
				lastChar = arr[i];
				counter = 1;
			}
		}
		
		return totalCount;
	}


}
