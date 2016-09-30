import java.util.Random;
public class Customers extends Thread{
	   private final static int MAX_DINING_PERIOD = 10000;

	    private int threadid;
	    private SynchronizedRestaurant restaurant;
	    private int[] sharedbuffer;
	    //private final int SIZE;
	    //int in, out;
	    //private static int count; 


	    public Customers(int id, SynchronizedRestaurant dine_room, int[] sharedbuffer)
	    {
		threadid = id;
		restaurant = dine_room;
		this.sharedbuffer = sharedbuffer;
		//this.SIZE = size; 
	    }

	    public void run(){
		//int i;
		Random  rand = new Random();
		
	    try {
	         sleep(rand.nextInt(10)*1000);
	       } catch(InterruptedException ex) {};

		try {
		    restaurant.enterdining();
		    System.out.println("Customer " + (threadid+1) + "  gets a seat: currently there are " + restaurant.value()  + " seats remaining");
		       try {
		    	   System.out.println("Customer " + (threadid+1) + " gets pizza " + consume() +" and is eating. "+ chef.count + " pizzas left unsold.");
	                Thread.sleep(rand.nextInt(MAX_DINING_PERIOD));
		    	        } catch (InterruptedException ex) {}
		} 	catch (InterruptedException e) {
		    e.printStackTrace();
			}
		//for (i = 0; i < rand.nextInt(MAX_DINING_PERIOD) * 1000; i++);
		

		try {
		    restaurant.leavedining();
		    System.out.println("Customer " + (threadid+1) + " finishes eating and leaves: currently there are " + restaurant.value() + " seats remaining");
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		
	    }
	    
		 public int consume() throws InterruptedException {
		        //wait if queue is empty
		        while (chef.count == 0) {
		            synchronized (sharedbuffer) {
		            	
		                System.out.println("Count is " + chef.count +" Buffer is empty " + Thread.currentThread().getName()
		                                    + " is waiting , size: " + sharedbuffer.length);

		                sharedbuffer.wait();
		            }
		        }
		 
		        synchronized (sharedbuffer) {
		        	int c = sharedbuffer[chef.out];
		        	chef.out = (chef.out+1)%sharedbuffer.length;
		        	chef.count--;
		        	//System.out.println("Count is" + chef.count);
		            sharedbuffer.notifyAll();
		            return c;
		        }
		 }
		


		
	    
	    
}
