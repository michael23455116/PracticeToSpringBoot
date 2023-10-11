package Model;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class Product {
    private Integer product_id;
    private String product_name;
    private String category;
    private String image_url;
    private BigDecimal price;
    private BigDecimal stock;
    private String description;
    private Date created_date;
    private Date last_modified_date;
}
