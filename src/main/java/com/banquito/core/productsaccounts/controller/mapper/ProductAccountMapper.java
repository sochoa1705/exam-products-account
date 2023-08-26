package com.banquito.core.productsaccounts.controller.mapper;

import java.util.ArrayList;
import java.util.List;

import com.banquito.core.productsaccounts.controller.dto.ProductAccountRQRS;
import com.banquito.core.productsaccounts.model.ProductAccount;

public class ProductAccountMapper {
    
    public static ProductAccountRQRS mapToProductAccountRQRS(ProductAccount productAccount) {
        return ProductAccountRQRS.builder()
            .id(productAccount.getId())
            .acceptsChecks(productAccount.getAcceptsChecks())
            .name(productAccount.getName())
            .description(productAccount.getDescription())
            .minimunBalance(productAccount.getMinimunBalance())
            .state(productAccount.getState())
            .payInterest(productAccount.getPayInterest())
            .build();
    }

    public static List<ProductAccountRQRS> mapToList(List<ProductAccount> productAccountes) {
        List<ProductAccountRQRS> productAccountesRQRS = new ArrayList<>();
        if (productAccountes!=null) {
            for (ProductAccount productAccount : productAccountes) {
                productAccountesRQRS.add(mapToProductAccountRQRS(productAccount));
            }
        }
        return productAccountesRQRS;
    }

    public static ProductAccount mapToProductAccount(ProductAccountRQRS productAccountRQRS) {
        ProductAccount productAccount = new ProductAccount();
        productAccount.setId(productAccountRQRS.getId());
        productAccount.setAcceptsChecks(productAccountRQRS.getAcceptsChecks());
        productAccount.setName(productAccountRQRS.getName());
        productAccount.setDescription(productAccountRQRS.getDescription());
        productAccount.setMinimunBalance(productAccountRQRS.getMinimunBalance());
        productAccount.setState(productAccountRQRS.getState());
        productAccount.setPayInterest(productAccountRQRS.getPayInterest());
        return productAccount;
    }
}
