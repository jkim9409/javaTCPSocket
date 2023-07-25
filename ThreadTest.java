public class ThreadTest {
    static Data sharedData = new Data();

    public static void main(String[] args) {
        System.out.println("main 시작");

        ThreadA a = new ThreadA();
        ThreadB b = new ThreadB();

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

class Data{
    int i = 0;

}//

class ThreadA extends Thread{
    public void run() {
        for(int i = 0; i< 100000; i++) {
            ThreadTest.sharedData.i++; // 임계영역
        }
        System.out.println("ThreadA i: " + ThreadTest.sharedData.i);
    }
}//

class ThreadB extends Thread{
    public void run() {
        for(int i = 0; i< 100000; i++) {
            ThreadTest.sharedData.i++; // 임계영역
        }
        System.out.println("ThreadB i: " + ThreadTest.sharedData.i);
    }
}//
