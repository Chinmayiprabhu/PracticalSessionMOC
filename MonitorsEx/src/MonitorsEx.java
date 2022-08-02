
public class MonitorsEx {

    public static int counter1 = 0;
    public static int counter2 = 0;
    public static synchronized void increment1() {
        counter1++;
    }

    public static synchronized void increment2() {
        counter2++;
    }

    public static void process() {

        Thread T1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++)
                    increment1();
            }
        });

        Thread T2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++)
                    increment2();
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
        System.out.println("The counter1 is : " + counter1);
        System.out.println("The counter2 is : " + counter2);
    }


    public static void main(String[] args) {
        process();
    }

}

