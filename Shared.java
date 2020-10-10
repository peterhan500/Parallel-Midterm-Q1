public class Shared {
	private static int result = 0;
	
	public static void increment() {
		result++;
	}
	
	public static void display() {
		System.out.println("\nThe smallest number of the list is " + result);
	}

}
