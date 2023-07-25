public class ThreadTestThreadSafe {
    static DataThreadSafe sharedData = new DataThreadSafe();

    public static void main(String[] args) {
        System.out.println("main 시작");

        ThreadSafeA a = new ThreadSafeA();
        ThreadSafeB b = new ThreadSafeB();

        a.start();
        b.start();

        try {
                Thread.sleep(3000);
                // Code to execute after the delay
                System.out.println("Timeout occurred after 5 seconds.");
                System.out.println(sharedData.i);
            } catch (InterruptedException e) {
                // Handle the exception if the thread is interrupted while sleeping
                e.printStackTrace();
            }

        System.out.println("main 종료");
    }
}

class DataThreadSafe{
    int i = 0;

}//

class ThreadSafeA extends Thread{
    public void run() {
        for(int i = 0; i< 100000; i++) {
            int delayMillis = 3000; // 5 seconds

//            try {
//                Thread.sleep(delayMillis);
//                // Code to execute after the delay
////                System.out.println("Timeout occurred after 5 seconds.");
//            } catch (InterruptedException e) {
//                // Handle the exception if the thread is interrupted while sleeping
//                e.printStackTrace();
//            }
            synchronized(ThreadTestThreadSafe.sharedData) {
                ThreadTestThreadSafe.sharedData.i++;	// 임계영역
            }
        }
        System.out.println("ThreadA i: " + ThreadTestThreadSafe.sharedData.i);
    }
}//

class ThreadSafeB extends Thread{
    public void run() {
        for(int i = 0; i< 100000; i++) {
            synchronized(ThreadTestThreadSafe.sharedData) {
                ThreadTestThreadSafe.sharedData.i++;	// 임계영역
            }
        }
        System.out.println("ThreadB i: " + ThreadTestThreadSafe.sharedData.i);
    }
}