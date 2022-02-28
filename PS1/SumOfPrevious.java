import java.util.Scanner;

public class SumOfPrevious {
//	public static boolean prevSum(int size,int[] arr,int num1,int num2,int pointNum) {
//		
//		if(arr.length>2) {
//			//if either nums add to pointNum return true
//			if(arr[num1]+arr[num2]==arr[pointNum] || 
//					arr[num1]*2==arr[pointNum] || arr[num2]*2==arr[pointNum]) {
//				System.out.println("First num "+arr[num1]+", Second num "+
//					arr[num2]+", Target num "+arr[pointNum]);
//				return true;
//			}
//			//go down arr list to calculate
//			if(num1+1<num2)
//				return prevSum(size,arr,num1+1,num2,pointNum);
//			else if(num2+1<pointNum)
//				return prevSum(size,arr,0,num2+1,pointNum);
//			else if(pointNum+1<size)
//				return prevSum(size,arr,0,1,pointNum+1);
//			else
//				return false;
//		}
//		//check if true for arr of len two
//		else if(arr.length==2 && arr[num1]*2==arr[num2])
//			return true;
//		else
//			return false;
//	}
	
public static int prevSum(int count, int size,int[] arr,int num1,int num2,int pointNum) {
		if(arr[num2] == arr[arr.length-1])
			return count;
		if(arr.length>2) {
			//if either nums add to pointNum return true
			if(arr[num1]+arr[num2]==arr[pointNum] || 
					arr[num1]*2==arr[pointNum] || arr[num2]*2==arr[pointNum]) {
				System.out.println("First num "+arr[num1]+", Second num "+
					arr[num2]+", Target num "+arr[pointNum]);
				count++;
			}
			//go down arr list to calculate
			if(num1+1<num2)
				return prevSum(count,size,arr,num1+1,num2,pointNum);
			else if(num2+1<pointNum)
				return prevSum(count,size,arr,0,num2+1,pointNum);
			else if(pointNum+1<size)
				return prevSum(count,size,arr,0,1,pointNum+1);
		}
		//check if true for arr of len two
		else if(arr.length==2 && arr[num1]*2==arr[num2])
			return 1;
		else
			return 0;
		return -1;
	}
	
	
	public static void reverseStr(String s) {
		if(s==null || s.length()<=1)
			System.out.println(s);
		else {
			System.out.print(s.charAt(s.length()-1));
			reverseStr(s.substring(0,s.length()-1));
		}	
	}
	
	public static boolean palindrome(String str,int s,int e) {
		if (s == e)
            return true;
        if ((str.charAt(s)) != (str.charAt(e)))
            return false;
        if (s < e + 1)
            return palindrome(str, s + 1, e - 1);
        return true;	
	}
	static boolean isPalindrome(String str){
        int n = str.length();
        if (n == 0)
            return true;
        return palindrome(str, 0, n - 1);
    }
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int size=s.nextInt();
		int[] nums = new int[size];
		for (int i = 0; i < size; i++)
			nums[i]=s.nextInt();
		int count=0;
		System.out.println(prevSum(count,size,nums,0,1,2));
		System.out.println();
		
		String str = "Horse";
		reverseStr(str);
		str = "tacocat";
		System.out.println(isPalindrome(str));
	}
}
