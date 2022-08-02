

public class Application {

    public static int counter = 0;

    public static void process() {

        Thread T1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++)
                    counter++;
            }
        });

        Thread T2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++)
                    counter++;
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