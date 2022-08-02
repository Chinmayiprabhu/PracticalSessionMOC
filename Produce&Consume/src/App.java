import java.util.ArrayList;
import java.util.List;

class processor {
 private List<Integer> list = new ArrayList<>();
 private static final int MAX_Limit = 10;
 private static final int MIN_Limit = 0;
 private final Object lock = new Object();
 private int value = 0 ;

 public void producer() throws InterruptedException {
 synchronized (lock) {
     while(true) {

         if(list.size() == MAX_Limit){
             System.out.println("Waiting for consumer to consume the numbers ....");
             lock.wait();
         } else{
             System.out.println("Adding the numbers" +value + " to list ..");
             list.add(value);
             value++;
             lock.notify();
             // here we synchronise not on class level blocking we do it on lock which is object level blocking
         }
         Thread.sleep(500);
     }

 }

    }

    public void consumer() throws InterruptedException {

        synchronized (lock) {
            while(true) {

                if(list.size() == MIN_Limit){
                    System.out.println("Waiting for producer to produce the numbers ....");
                    value=0;
                    lock.wait();
                } else{
                    System.out.println("Removing the numbers" +list.remove(list.size()-1)+ " from list ..");
                    lock.notify();
                    // here we synchronise not on class level blocking we do it on lock which is object level blocking
                }
                Thread.sleep(500);
            }

        }
    }

}




public class App {


    public static void main( String[] args) {
        processor process = new processor();

        Thread T1 = new Thread(new Runnable() {
            @Override
            public void run() {
               try{
                   process.producer();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
            }
        });


        Thread T2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    process.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        T1.start();
        T2.start();
        try {
            T1.join();
            T2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
