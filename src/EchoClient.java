import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {
    public static void main(String[] args) {
        String hostname = "localhost"; // Địa chỉ server, localhost là máy hiện tại
        int port = 12345;

        // Sử dụng try-with-resources để đóng socket và các stream tự động
        try (Socket socket = new Socket(hostname, port)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Đã kết nối tới server. Gõ 'exit' để thoát.");
            String userInput;
            while ((userInput = stdIn.readLine())!= null) {
                // Gửi dữ liệu người dùng nhập tới server
                out.println(userInput);

                // Đọc và in phản hồi từ server
                System.out.println(in.readLine());

                if ("exit".equalsIgnoreCase(userInput)) {
                    break;
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Không tìm thấy host: " + hostname);
        } catch (IOException e) {
            System.err.println("Lỗi I/O khi kết nối tới " + hostname + ": " + e.getMessage());
        }
    }

}
