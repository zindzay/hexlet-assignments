package exercise;

class App {

    public static void main(String[] args) throws InterruptedException {
        // BEGIN
        final SafetyList safetyList = new SafetyList();

        final ListThread listThread1 = new ListThread(safetyList);
        final ListThread listThread2 = new ListThread(safetyList);

        listThread1.start();
        listThread2.start();

        listThread1.join();
        listThread2.join();

        System.out.println(safetyList.getSize());
        // END
    }
}

