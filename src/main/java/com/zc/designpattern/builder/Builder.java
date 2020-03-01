package com.zc.designpattern.builder;

import lombok.Data;

import java.util.Date;

/**
 * @author chock
 * @since 2020/2/22
 */
public class Builder {

    public static void main(String[] args) {
        /*Product product = new Product();
        product.setName("goods");
        product.setCompany("O3");
        product.setProductionDate(new Date());
        product.setSaleBy("me");
        product.setSaleTo("you");*/

        DefaultBuilderProduct defaultBuilderProduct = new DefaultBuilderProduct();
        Direct direct = new Direct();
        Product product = direct.makeProduct("goods", "O3", new Date(), "me", "you");
        System.out.println(product);
    }

}

interface ProductBuilder {
    void builderName(String name);

    void builderCompany(String company);

    void builderDate(Date date);

    void builderSaleBy(String saleBy);

    void builderSaleTo(String saleTo);

    Product build ();
}

class DefaultBuilderProduct implements ProductBuilder {

    private String name;
    private String company;
    private Date productionDate;
    private String saleBy;
    private String saleTo;

    @Override
    public void builderName(String name) {
        this.name = name;
    }

    @Override
    public void builderCompany(String company) {
        this.company = company;
    }

    @Override
    public void builderDate(Date date) {
        this.productionDate = date;
    }

    @Override
    public void builderSaleBy(String saleBy) {
        this.saleBy = saleBy;
    }

    @Override
    public void builderSaleTo(String saleTo) {
        this.saleTo = saleTo;
    }

    @Override
    public Product build() {
        return new Product(this.name, this.company, this.productionDate, this.saleBy, this.saleTo);
    }
}

class Direct {
    private ProductBuilder builder;
    public Product makeProduct (String name, String company, Date productionDate, String saleBy, String saleTo) {
        builder.builderName(name);
        builder.builderCompany(company);
        builder.builderDate(productionDate);
        builder.builderName(name);
        builder.builderName(name);

        return builder.build();
    }
}

@Data
class Product {
    private String name;
    private String company;
    private Date productionDate;
    private String saleBy;
    private String saleTo;

    public Product() {
    }

    public Product(String name, String company, Date productionDate, String saleBy, String saleTo) {
        this.name = name;
        this.company = company;
        this.productionDate = productionDate;
        this.saleBy = saleBy;
        this.saleTo = saleTo;
    }
}
