package RMI;

import java.io.Serializable;

public class ProductX implements Serializable {
    private String id ;
    private String discountCode ;
    private int discount ;
    private String code ;
    private static final long serialVersionUID = 20171107 ;


    public ProductX(String id, String discountCode, int discount, String code) {
        this.id = id;
        this.discountCode = discountCode;
        this.discount = discount;
        this.code = code;
    }

    @Override
    public String toString() {
        return "ProductX{" +
                "id='" + id + '\'' +
                ", discountCode='" + discountCode + '\'' +
                ", discount=" + discount +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
