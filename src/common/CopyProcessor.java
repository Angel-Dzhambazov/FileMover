package common;


import java.io.IOException;

public class CopyProcessor extends Thread {
	// gui copy processor (thread)
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