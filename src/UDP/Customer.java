package UDP;

import java.io.Serializable;

public class Customer implements Serializable {
    private String id ;
    private String code ;
    private String name ;
    private String userName ;
    private String dayOfBirth ;

    private static final long serialVersionUID = 20151107;

    public Customer(String id, String code, String name, String username, String dateOfBirth) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.userName = username;
        this.dayOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", username='" + userName + '\'' +
                ", dateOfBirth='" + dayOfBirth + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getDateOfBirth() {
        return dayOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dayOfBirth = dateOfBirth;
    }
}
