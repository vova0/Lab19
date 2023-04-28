import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class EchoServer {
    public static void main(String[] args) {
        Socket socket = null;
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Сервер запущен, ожидаем подключения");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            // Поток данных, то что получает сервер (сообщения пользователя)
            DataInputStream in = new DataInputStream(socket.getInputStream());
            // Поток данных, из сервера (ответ сервера)
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            while (true) {
                String str = in.readUTF();
                if (str.equals("/end")) {
                    break;
                }
                out.writeUTF("Echo: " + str);
            }
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }
}