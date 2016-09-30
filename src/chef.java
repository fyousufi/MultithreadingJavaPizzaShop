import java.util.logging.Level;
import java.util.logging.Logger;
public class chef extends Thread {

	    private final int[] sharedbuffer; //this is essentially the plates
	    //private final int SIZE;
	    public static int in, out;
	    public static int count; //Extra credit: count will be a double since it will not track .25 increments as slices
	    int i=0;
	    public chef(int sharedbuffer[]) {
	        this.sharedbuffer = sharedbuffer;
	        //this.SIZE = size;
	    }

	    @Override
	    public void run() {
	        while (true) {
	            try {
	            	i=1;
	                produce(i);
	                //System.out.println("Chef makes a pizza.There are"+ count + "pizzas unsold" );
	                i++;
	            } catch (InterruptedException ex) {
	                Logger.getLogger(chef.class.getName()).log(Level.SEVERE, null, ex);
	            }

	        }
	    }
	    
	    private void produce(int i) throws InterruptedException {

	        //wait if queue is full
	        while (count == sharedbuffer.length) {
	            synchronized (sharedbuffer) {
	                System.out.println("Pizza Plates are full " + "Chef"
	                                    + " is waiting , pizzas on plates: " + sharedbuffer.length);

	                sharedbuffer.wait();
	            }
	        }

	        //producing element and notify consumers
	        synchronized (sharedbuffer) {
	        	sharedbuffer[in] = i;
	        	in = (in+1)% sharedbuffer.length;
	        	count++;
	        	System.out.println("Chef makes a pizza. There are "+ count +" pizzas left unsold.");
	            sharedbuffer.notifyAll();
	        }
	    }

	}