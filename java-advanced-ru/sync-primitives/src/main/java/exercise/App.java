package exercise;

class App {

    public static void main(String[] args) {
        // BEGIN
        final SafetyList safetyList = new SafetyList();

        final ListThread listThread1 = new ListThread(safetyList);
        final ListThread listThread2 = new ListThread(safetyList);

        listThread1.start();
        listThread2.start();

        try {
            listThread1.join();
            listThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Size: " + safetyList.getSize());
        // END
    }
}

