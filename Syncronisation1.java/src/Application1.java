

public class Application1 {

    public static int counter = 0;
   public static synchronized void increment() {
    counter++;
}



    public static void process() {

        Thread T1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++)
                    increment();
            }
        });

        Thread T2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++)
                    increment();
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
        System.out.println("The counter is : " + counter);
    }


    public static void main(String[] args) {
        process();
    }

}