import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class 24YwKzy9{
    public static final String server = "203.162.10.109" ;
    public static final int port = 2206 ;
    public static void main(String[] args) throws Exception {
        String studentCode = "B22DCCN352" ;
        String qCode = "24YwKzy9" ;
        Socket socket = new Socket (server , port) ;
        socket.setSoTimeout(5000) ;
        String message = studentCode + ";" + qCode ;
        InputStream is = socket.getInputStream() ;
        OutputStream os = socket.getOutputStream() ;
        os.write(message.getBytes()) ;
        os.flush() ;
        byte[] data = new byte[1024] ;
        int len = is.read(data) ;
        String response = new String(data, 0 , len) ;
        int[] arr = Arrays.stream(response.split(","))
                .mapToInt(Integer :: parseInt)
                .toArray() ;
        System.out.println(findLongestIncreasingSubsequence(arr));
        String result = findLongestIncreasingSubsequence(arr) ;
        os.write(result.getBytes()) ;
        os.flush();
    }
    public static String findLongestIncreasingSubsequence(int[] arr){
        int n = arr.length ;
        if(n == 0 ) return "" ;
        int[] dp = new int[n] ;
        int[] parents = new int[n] ;
        Arrays.fill(parents, -1) ;
        Arrays.fill(dp, 1) ;

        int maxLen = 1 ;
        int lastIndex = 0 ;

        for(int i = 1 ; i < n ; i ++){
            for(int j = 0 ; j < i ; j ++){
                if(arr[j] < arr[i] && dp[j] + 1 > dp[i]){
                    dp[i] = Math.max(dp[i] , dp[j] + 1) ;
                    parents[i] = j ;
                }
            }
            if(dp[i] > maxLen){
                maxLen = dp[i] ;
                lastIndex = i ;
            }
        }
        List<Integer> lis = new ArrayList<>() ;
        while(lastIndex != - 1){
            lis.add(arr[lastIndex]) ;
            lastIndex = parents[lastIndex] ;
        }
        Collections.reverse(lis) ;
        String seq = String.join(",",lis.stream().map(String :: valueOf)
                .toList()
        ) ;
        return seq + ";" + maxLen;
    }
}