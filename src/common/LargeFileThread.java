package common;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;


//stream - s precizen kontrol.
// s files move
//thread factory? How to do it
//trqbva da izvikame super konstruktura

//executive servise po-dobre ot thread factory


class LargeFileThread extends Thread {
    private Path sourcePath;
    private Path destinationPath;
    private File file;

    LargeFileThread(File file, Path sourcePath, Path destinationPath) throws IOException {
        super();
        this.sourcePath = sourcePath;
        this.destinationPath = destinationPath;
        this.file = file;

    }

    @Override
    public void run() {
        try {
//            System.out.println("We are in the private method of the additional thread");
//            System.out.println("file name is: "  + file.getName());
            Files.move(sourcePath, destinationPath);
//            System.out.println("Moving of " + file.getName()+" completed");
        }catch (FileAlreadyExistsException aee) {
            //Result of File.delete() is ignored => Zashto?
            file.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
