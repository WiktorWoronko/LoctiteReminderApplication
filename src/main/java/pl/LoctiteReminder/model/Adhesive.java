package pl.LoctiteReminder.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Adhesive implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String productName;
    @NotNull
    private String idhNumber;
    @NotNull
    private String amountOfProduct;
    @NotNull
    private LocalDate expiryDate;
    @NotNull
    private LocalDate remindDate;

    private String additionalRemarks;

    public Adhesive(Integer id, @NotNull String productName, @NotNull String idhNumber, @NotNull String amountOfProduct, @NotNull LocalDate expiryDate, @NotNull LocalDate remindDate, String additionalRemarks) {
        this.id = id;
        this.productName = productName;
        this.idhNumber = idhNumber;
        this.amountOfProduct = amountOfProduct;
        this.expiryDate = expiryDate;
        this.remindDate = remindDate;
        this.additionalRemarks = additionalRemarks;
    }

    public Adhesive() {
    }

    public Integer getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getIdhNumber() {
        return idhNumber;
    }

    public void setIdhNumber(String idhNumber) {
        this.idhNumber = idhNumber;
    }

    public String getAmountOfProduct() {
        return amountOfProduct;
    }

    public void setAmountOfProduct(String amountOfProduct) {
        this.amountOfProduct = amountOfProduct;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LocalDate getRemindDate() {
        return remindDate;
    }

    public void setRemindDate(LocalDate remindDate) {
        this.remindDate = remindDate;
    }

    public String getAdditionalRemarks() {
        return additionalRemarks;
    }

    public void setAdditionalRemarks(String additionalRemarks) {
        this.additionalRemarks = additionalRemarks;
    }


}
