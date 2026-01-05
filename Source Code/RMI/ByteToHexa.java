import RMI.*;


import java.io.*;
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
        String studentCode = "B22DCCN017";
        String qCode = "VNAtzL1E";
        int port = 1099 ;
        Registry registry = LocateRegistry.getRegistry(server , port) ;
        ByteService byteService = (ByteService) registry.lookup("RMIByteService") ;
        byte[] request = byteService.requestData(studentCode , qCode) ;
        StringBuilder result = new StringBuilder() ;
        for(byte b : request){
            result.append(convertToHex(b)) ;
        }
        byteService.submitData(studentCode , qCode, result.toString().getBytes());

    }

    private static String convertToHex(byte b) {
        return String.format("%02x", b & 0xFF);
    }


}