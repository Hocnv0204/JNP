import RMI.*;


import java.io.*;
import java.math.BigInteger;
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
        String studentCode = "B22DCCN135";
        String qCode = "K5YpDeMP" ;
        int port = 1099 ;
        Registry registry = LocateRegistry.getRegistry(server , port) ;
        DataService dataService = (DataService) registry.lookup("RMIDataService") ;
        int number = (int) dataService.requestData(studentCode , qCode) ;
        int[] refer = {10 , 5 , 2 , 1} ;
        List<Integer> list = new ArrayList<>();
        int count = 0 ;
        for(int i = 0 ; i < 4 ; i++){
            while(number >= refer[i]){
                number -= refer[i] ;
                count ++ ;
                list.add(refer[i]) ;
            }
        }
        StringBuilder result = new StringBuilder() ;
        result.append(count).append("; ").append(String.join("," , list.stream().map(String :: valueOf).toList())) ;
        dataService.submitData(studentCode , qCode , result.toString());

    }




}