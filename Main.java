import java.util.*;

public class Main {
  public static void main(String[] args) {
    // This method is complete. Do not change it.
    new Main();
    }
    public Main() {
      Q1Threads();
  }

  private void Q1Threads(){
    // This method is complete. Do not change it.
    Random rand = new Random();
    int allThreads = rand.nextInt(5) + 10;
    int size = rand.nextInt(50) + 1;
    while(size%allThreads != 0)size++;
    ArrayList<Integer> a = new ArrayList<Integer>(size);
    for(int i = 0; i < size; i++)
      a.add(rand.nextInt(100) + 10);
    sequentialSmallest(a);
    MyThread[] thrds = new MyThread[allThreads];
    int segment = size/allThreads;
    for(int i = 0; i <allThreads ; i++) {
      int firstIndex = i * segment;
      int lastIndex = i * segment + segment -1;
      thrds[i] = new MyThread(a, firstIndex, lastIndex);
    }
    
    for(int i = 0; i < allThreads; i++)
      thrds[i].start();
    
    try{
      for(int i = 0; i < allThreads; i++)
        thrds[i].join();
    }catch(Exception e){
      System.out.println("Error: " + e);
      System.exit(0);
    }
    Shared.display();
    System.out.println("The smallest number is: " + Shared.result);
  }
  
  private static void sequentialSmallest(ArrayList<Integer> a) {
    // This method is complete. Do not change it.
    int smallest = a.get(0);
    for(int i = 0; i < a.size(); i++)
      if(a.get(i) < smallest)
        smallest = a.get(i);
    System.out.println("The list of random numbers is:");
    for(int i = 0; i < a.size(); i++)
      System.out.print(a.get(i) + ", ");
    System.out.println("\nThe smallest number from the sequential list is: " + smallest);
  } 
}

class MyThread extends Thread{
	Object obj;
	public MyThread(Object obj) {
		this.obj = obj;
	}
  private ArrayList<Integer> a; 
  private int from, too;
  public MyThread(ArrayList<Integer> a, int from, int too) {
    this.a = a;
    this.from = from;
    this.too = too;    
  }
  
  public void run(){
    // Complete this method
	  synchronized(obj) {
	try {
		obj.wait();
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	}
	  for(int i = from; i < too; i++) {
		  Shared.increment(); 
		  System.out.println(Thread.currentThread().getName() + a);
	  }   
}  
}