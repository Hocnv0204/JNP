
package webservice;
import vn.medianews.* ;
import java.util.* ;
public class WebService {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String studentCode = "B22DCCN508" ;
        String qCode = "AdKI1K64" ;
        DataService_Service service = new DataService_Service() ;
        DataService port = service.getDataServicePort() ;
        List<Integer> request = port.getData(studentCode, qCode );
        System.out.println(request.size());
        List<String> result = new ArrayList<>() ;
        for(Integer num : request){
            result.add(convertToBinary(num)) ;
        }
        port.submitDataStringArray(studentCode, qCode, result);
    }
    private static String convertToBinary(Integer number){
        StringBuilder sb = new StringBuilder()  ;
        while(number > 0 ){
            int remainder = number % 2 ;
            sb.append(remainder) ;
            number /=2 ;
        }
        return sb.reverse().toString() ;
    }

}
