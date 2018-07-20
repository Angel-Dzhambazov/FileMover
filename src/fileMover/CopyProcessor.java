package fileMover;


import java.io.IOException;

class CopyProcessor extends Thread {

    private volatile boolean running = true;
    private CopyManager copyManager;

    public CopyProcessor(CopyManager copyManager) {
        this.copyManager = copyManager;
    }


    public void run() {
        while (running) {
            if (!copyManager.checkEmptyDirectory()) {
                try {
                    copyManager.copyFiles();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    

    public void shutdown() {
        this.running = false;
    }

}