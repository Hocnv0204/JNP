import RMI.ByteService;
import RMI.CharacterService;
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
public class Main{
    public static void main(String[] args) throws Exception {
        String server = "203.162.10.109";
        String studentCode = "B22DCCN352";
        String qCode = "RFIYmjuu";
        int port = 1099 ;
        Registry registry = LocateRegistry.getRegistry(server , port) ;
        ByteService byteService = (ByteService)registry.lookup("RMIByteService") ;
        byte[] request = byteService.requestData(studentCode , qCode) ;
        List<Byte> even = new ArrayList<>() ;
        List<Byte> odd = new ArrayList<>() ;
        for(byte element : request){
            if(element % 2 == 0){
                even.add(element) ;
            }
            else{
                odd.add(element) ;
            }
        }
        List<Byte> resultList = new ArrayList<>() ;
        for(byte b : even){
            resultList.add(b) ;
        }
        for(byte b : odd){
            resultList.add(b) ;
        }
        byte[] resultBytes = new byte[request.length] ;
        for(int i = 0 ; i < resultBytes.length ; i ++){
            resultBytes[i] = resultList.get(i) ;
        }
        byteService.submitData(studentCode , qCode , resultBytes);

    }
}