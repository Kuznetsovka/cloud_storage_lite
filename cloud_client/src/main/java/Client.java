import IO.FileUtility;

import java.io.File;
import java.io.IOException;
import java.net.Socket;


public class Client extends FileUtility{
    private int id;
    private String file;
    public Client(int id,String file) {
        this.id = id;
        upload(file);
    }

    private void upload(String file) {
        try {
            sendFile(new Socket("localhost", 8189),
                    new File("./" + file),id);
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

//    @Override
//    public void run() {
//        upload(file);
//        try {
//            sleep(500); // Задержка в 0.5 сек
//        } catch (Exception e) {}
//    }

}
