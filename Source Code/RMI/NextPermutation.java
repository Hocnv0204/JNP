import RMI.*;
import TCP.Customer;
import UDP.Employee;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.* ;
import java.util.stream.Collectors;

public class Main{
    public static void main(String[] args) throws Exception {
        String server = "203.162.10.109";
        String studentCode = "B22DCCN244";
        String qCode = "jmf89e43";
        int port = 1099 ;
        Registry registry = LocateRegistry.getRegistry(server , port) ;
        DataService dataService = (DataService) registry.lookup("RMIDataService") ;
        String request = (String) dataService.requestData(studentCode , qCode) ;
        System.out.println(request);
        int[] arr = Arrays.stream(request.split(", ")).mapToInt(Integer :: parseInt).toArray() ;
        nextPermutation(arr);
        List<String> result = Arrays.stream(arr).mapToObj(String :: valueOf).toList() ;
        dataService.submitData(studentCode , qCode , String.join("," , result));
    }
    private static void nextPermutation(int[] arr){
        int n = arr.length ;
        int i = n - 2;
        // find arr[i] < arr[i+1] => mang giam dan
        while(i >= 0 && arr[i] >= arr[i+1]){
            i -- ;
        }
        if(i < 0 ){
            Arrays.sort(arr) ;
            return ;
        }
        // find arr[j] min > arr[i] ;
        int j = n - 1 ;
        while(arr[j] <= arr[i]){
            j -- ;
        }
        // swap
        int temp = arr[i] ;
        arr[i] = arr[j] ;
        arr[j] = temp ;
        // reverse
        reverse(arr , i + 1 , n - 1);
    }

    private static void reverse(int[] arr , int l , int r){
        while(l < r){
            int temp = arr[l] ;
            arr[l] = arr[r] ;
            arr[r] = temp ;
            l ++ ;
            r -- ;
        }
    }
}