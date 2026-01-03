/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package webservice;
import vn.medianews.* ;
import java.util.* ;
public class WebService {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String studentCode = "B22DCCN412" ;
        String qCode = "4sdqsKct" ;
        ObjectService_Service service = new ObjectService_Service() ;
        ObjectService port = service.getObjectServicePort() ;
        List<Student> list =  port.requestListStudent(studentCode, qCode) ;
        System.out.println(list.size());
        List<Student> result = new ArrayList<>() ;
        for(Student student : list){
            if(student.getScore() >= 8.0 || student.getScore() < 5.0){
                result.add(student) ;
            }
        }
        port.submitListStudent(studentCode, qCode, result);
    }


}
