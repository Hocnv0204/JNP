import RMI.ByteService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class XP33PczM {
    public static void main(String[] args) throws Exception {
        String studentCode = "B22DCCN352" ;
        String qCode = "XP33PczM" ;
        String server = "203.162.10.109" ;
        int port = 1099 ;
        String xorKey = "PTIT" ;
        Registry registry = LocateRegistry.getRegistry(server, port) ;
        ByteService byteService = (ByteService) registry.lookup("RMIByteService") ;
        byte[] receivedData = byteService.requestData(studentCode , qCode) ;
        System.out.println("  - Chuỗi ASCII: " + new String(receivedData));
        byte[] result = xorEncode(receivedData , xorKey) ;
        byteService.submitData(studentCode , qCode , result);
    }
    public static byte[] xorEncode(byte[] data , String key){
        byte[] keyBytes = key.getBytes() ;
        byte[] encoded = new byte[data.length] ;
        for(int i = 0 ; i < data.length ; i ++){
            int keyIndex = i % keyBytes.length ;
            encoded[i] = (byte)(data[i] ^ keyBytes[keyIndex]) ;
        }
        return encoded ;
    }
}