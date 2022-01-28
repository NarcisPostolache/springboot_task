package com.foodbooking.controller;

import com.foodbooking.dto.RequestBodyDto;
import com.foodbooking.entity.FoodItem;
import com.foodbooking.entity.UserOrder;
import com.foodbooking.service.ProductService;
import com.foodbooking.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
public class FoodBookingController {

    @Autowired
    ProductService productService;

    @Autowired
    UserOrderService userOrderService;

    @GetMapping("/searchProduct")
//    public List<FoodItem> searchProducts(@RequestParam @Min(1) @Max(10) String searchInput){  //NOSONAR
    public List<FoodItem> searchProducts(@RequestParam String searchInput){
            return productService.searchProducts(searchInput);
    }

    @PutMapping("/buyProduct")
    public ResponseEntity buyProduct(@Valid @RequestBody RequestBodyDto request){
        return productService.buyProduct(request.getProductId(),
                request.getQuantity(),
                request.getAccountNumber());
    }

    @GetMapping("/getOrderHistory")
    public List<UserOrder> getOrderHistory(@RequestParam String userId){
        return userOrderService.getUserHistory(userId);
    }
}
