import javax.swing.*;

class process {
 public void producer() throws InterruptedException {
     synchronized (this){
         System.out.println(" Ruunning in the producer method..");
         wait();
         System.out.println("Again I am in producer method...");
     }

    }
public void consumer() throws InterruptedException{

     Thread.sleep(1000);
     synchronized (this ) {
         System.out.println(" Now I am in consumer method..");
         notify();
     }
    }

        }


public class App {
    public static void main (String[] args){
        process process = new process();
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
