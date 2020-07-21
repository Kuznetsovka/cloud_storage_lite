package IO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends FileUtility {

    DataInputStream is;
    DataOutputStream os;
    ServerSocket server;
    String dirName = "./common/server/user";

    public Server() throws IOException {
        server = new ServerSocket(8189);
        Socket socket = server.accept();
        is = new DataInputStream(socket.getInputStream());
        os = new DataOutputStream(socket.getOutputStream());
        String[] arr = is.readUTF().split ("##");
        String fileName = arr[0];
        int id = Integer.parseInt (arr[1]);
        System.out.println("Client "+ id + " accepted!");

        System.out.println("fileName: " + fileName);
        createDirectory(dirName + id + "/");
        File file = new File(dirName + id + "/" + fileName);
        file.createNewFile();
        try (FileOutputStream os = new FileOutputStream(file)) {
            byte[] buffer = new byte[8192];
            while (true) {
                int r = is.read(buffer);
                if (r == -1) break;
                os.write(buffer, 0, r);
            }
        }
        System.out.println("File uploaded!");
    }

    public static void main(String[] args) throws IOException {
        new Server();
    }
}
