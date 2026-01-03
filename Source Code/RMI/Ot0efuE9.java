import RMI.ByteService;
import RMI.CharacterService;
import RMI.DataService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.* ;
import java.util.stream.Collectors;

public class Ot0efuE9 {
    public static void main(String[] args) throws Exception {
        String studentCode = "B22DCCN352";
        String qCode = "Ot0efuE9";
        int port = 1099;
        String server = "203.162.10.109";
        Registry registry = LocateRegistry.getRegistry(server, port);
        DataService dataService = (DataService) registry.lookup("RMIDataService");
        Integer amount = (Integer)dataService.requestData(studentCode , qCode ) ;

        List<Integer> coins = new ArrayList<>() ;
        int[] denominations = {10,5,2,1} ;
        for(int i = 0 ; i < 4 ; i ++){
            while(amount >= denominations[i]){
                amount -= denominations[i] ;
                coins.add(denominations[i]) ;
            }
        }
        String result = coins.size() + "; " + String.join("," , coins.stream().map(String:: valueOf).collect(Collectors.toList())) ;
        dataService.submitData(studentCode , qCode , result);
    }
}