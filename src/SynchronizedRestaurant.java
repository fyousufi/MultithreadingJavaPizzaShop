
public class SynchronizedRestaurant {
	   private static int count = 0; 
	   private static int MAX_SEATING_SIZE;
	    
	    public SynchronizedRestaurant(int N)
	    {
	    	MAX_SEATING_SIZE = N; 
	    }
	    
	    public synchronized void enterdining() throws InterruptedException
	    {
		//	System.out.println("enter " + count);
		while (count == MAX_SEATING_SIZE)
		    wait();
		count ++;
		//	System.out.println("enter " + count);
		if (count > 10)
		    System.out.println(count + "error");
		
	    }

	    public synchronized void leavedining() throws InterruptedException
	    {
		notify();
		count --;
	    }
	    public synchronized int value()
	    {
		if (count > 10)
		     System.out.println(count + "errorcount");
		
		return (MAX_SEATING_SIZE - count);
	    }
}
