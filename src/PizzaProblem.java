import java.util.Random;
public class PizzaProblem{

	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		int M = Integer.parseInt(args[1]);
		int max_customers;
		
		Random rand = new Random();
		max_customers = rand.nextInt(21);
		if( N > 10 || M > 10){
			N = 10; 
			M = 10; 
		}
		
		System.out.println("N:"+ N +" M:" + M);
		System.out.println("The total number of customers is " + max_customers);
		int[] sharedbuffer = new int[M];
        //int size_pizza_buffer = M;
        chef bobChef = new chef(sharedbuffer);
        Customers[] threads = new Customers[max_customers];
    	SynchronizedRestaurant dining = new SynchronizedRestaurant(N);
    	//customers arrive randomly to the restaurant as indicated by random sleep 
    	bobChef.start();
    	for(int i =0; i< max_customers; i++){
    		threads[i]= new Customers(i, dining, sharedbuffer);
    		threads[i].start();
    	}
      
    	// wait for all worker threads finish
    	for (int i = 0; i < max_customers; i++) {
    	    try {
    		threads[i].join();
    	    }catch (InterruptedException e) {
    		e.printStackTrace();
    	    }
    	}
    	System.out.println("All customers are done eating, exiting main thread");
    	try{bobChef.join();}catch(InterruptedException e){e.printStackTrace();}
    	

	}
	
	
}
