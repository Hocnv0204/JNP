
package webservice;
import vn.medianews.* ;
import java.util.* ;
public class WebService {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String studentCode = "B22DCCN100" ;
        String qCode = "ijAdw31K" ;
        ObjectService_Service service = new ObjectService_Service() ;
        ObjectService port = service.getObjectServicePort() ;
        List<EmployeeY> request = port.requestListEmployeeY(studentCode, qCode) ;
        request.sort(Comparator.comparing((e -> e.getStartDate().toGregorianCalendar().getTime())));
        port.submitListEmployeeY(studentCode, qCode, request);

    }


}
