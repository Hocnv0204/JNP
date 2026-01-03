import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.* ;
public class Main {
   private static final String SERVER_HOST = "203.162.10.109";
   private static final int SERVER_PORT = 2206;
   private static final int TIMEOUT = 5000 ;

   public static void main(String[] args) throws IOException {
       // Thông tin sinh viên và mã câu hỏi
       String studentCode = "B22DCCN352"; // Thay đổi mã sinh viên của bạn
       String qCode = "0R9YJCfD";

       Socket socket = null ;
       InputStream is = null ;
       OutputStream os = null ;
       try {
           // connect to server
           socket = new Socket(SERVER_HOST , SERVER_PORT) ;
           socket.setSoTimeout(TIMEOUT);

            os = socket.getOutputStream() ;
            is = socket.getInputStream() ;

           String loginMessage = studentCode + ";" + qCode ;
           byte[] loginBytes = loginMessage.getBytes(StandardCharsets.UTF_8) ;
           os.write(loginBytes);
           os.flush() ;

           String numbersString = readStringFromInputStream(is) ;
           System.out.println("Các số trong chuỗi: " + numbersString);
           String result = solve(numbersString) ;

           byte[] sumBytes = result.getBytes() ;
           os.write(sumBytes);
           os.flush();
       } catch (UnknownHostException e) {
           e.printStackTrace();
       } catch (SocketException e) {
           throw new RuntimeException(e);
       } catch (IOException e) {
           throw new RuntimeException(e);
       } finally {
           // Close socket
           closeResources(socket , is , os ) ;
       }
   }
   private static String readStringFromInputStream(InputStream is) throws IOException {
       ByteArrayOutputStream buffer = new ByteArrayOutputStream();
       byte[] data = new byte[1024]; // Buffer 1KB
       int bytesRead;

       // Đọc data từ inputStream
       while ((bytesRead = is.read(data)) != -1) {
           buffer.write(data, 0, bytesRead);

           // Kiểm tra xem có thêm data không (non-blocking check)
           if (is.available() == 0) {
               break; // Không còn data ngay lập tức
           }
       }

       // Chuyển bytes thành string
       return buffer.toString(StandardCharsets.UTF_8.name()).trim();
   }


   private static String solve(String numbersString){
       int[] arr = Arrays.stream(numbersString.split(",")).mapToInt(Integer::parseInt).toArray();
       double avg = Arrays.stream(arr).average().orElse(0) ;
       double target = avg * 2 ;
       double minDiff = Double.MAX_VALUE ;
       String result = null ;
       for (int i = 0 ; i < arr.length ; i ++){
           for (int j = i + 1 ; j < arr.length ; j ++){
               double diff = Math.abs(target - arr[i] - arr[j]) ;
               if(diff <= minDiff){
                   minDiff = diff ;
                   int num1 = Math.min(arr[i] , arr[j]) ;
                   int num2 = Math.max(arr[i] , arr[j]) ;
                   result = num1 + "," + num2 ;
               }
           }
       }
       return result ;
   }
   private static void closeResources(Socket socket,InputStream is , OutputStream os ) throws IOException {
       if (os != null) {
           os.close();
           System.out.println("Đã đóng OutputStream");
       }

       try {
           if (is != null) {
               is.close();
               System.out.println("Đã đóng InputStream");
           }
       } catch (IOException e) {
           System.err.println("Lỗi khi đóng InputStream: " + e.getMessage());
       }

       try {
           System.out.println("Trạng thái socket: closed = " + socket.isClosed() + ", connected = " + socket.isConnected());
           if (socket != null && !socket.isClosed()) {
               socket.close();
               System.out.println("Đã đóng Socket");
           }
       } catch (IOException e) {
           System.err.println("Lỗi khi đóng Socket: " + e.getMessage());
       }
   }
}
// Phiên bản tối ưu O(n log n) cho dữ liệu lớn:
/*
public static String findClosestPairOptimized(List<Integer> numbers) {
    if (numbers.size() < 2) {
        throw new IllegalArgumentException("Need at least 2 numbers");
    }

    double sum = numbers.stream().mapToInt(Integer::intValue).sum();
    double target = 2.0 * sum / numbers.size();

    // Tạo danh sách các cặp (value, originalIndex) và sắp xếp
    List<int[]> sorted = IntStream.range(0, numbers.size())
            .mapToObj(i -> new int[]{numbers.get(i), i})
            .sorted((a, b) -> Integer.compare(a[0], b[0]))
            .collect(Collectors.toList());

    double minDiff = Double.MAX_VALUE;
    int num1 = 0, num2 = 0;

    // Two pointers approach
    int left = 0, right = sorted.size() - 1;

    while (left < right) {
        int a = sorted.get(left)[0];
        int b = sorted.get(right)[0];
        double currentSum = a + b;
        double diff = Math.abs(currentSum - target);

        if (diff < minDiff) {
            minDiff = diff;
            num1 = Math.min(a, b);
            num2 = Math.max(a, b);
        }

        if (currentSum < target) {
            left++;
        } else {
            right--;
        }
    }

    return num1 + "," + num2;
}
*/