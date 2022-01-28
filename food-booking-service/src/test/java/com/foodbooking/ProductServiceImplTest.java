package com.foodbooking;

import com.foodbooking.entity.FoodItem;
import com.foodbooking.feign.PaymentClient;
import com.foodbooking.repository.ProductRepository;
import com.foodbooking.service.UserOrderService;
import com.foodbooking.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.function.EntityResponse;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ProductServiceImplTest {


    @Mock
    ProductRepository productRepository;

    @Mock
    PaymentClient paymentClient;

    @Mock
    UserOrderService userOrderService;

    @InjectMocks
    ProductServiceImpl productServiceImpl;

    @Test
    public void returnProductsForSearchInput(){
        FoodItem foodItem = getFoodItem();

        when(productRepository.findByNameContains(anyString())).thenReturn(Arrays.asList(foodItem));
        List<FoodItem> list = productServiceImpl.searchProducts("");
        assertEquals("Cheesecake", list.get(0).getName());
    }

    private FoodItem getFoodItem() {
        FoodItem foodItem = new FoodItem();
        foodItem.setId(0L);
        foodItem.setName("Cheesecake");
        foodItem.setVendorId(0L);
        foodItem.setDescription("");
        foodItem.setPrice(0);
        return foodItem;
    }

    @Test
    public void returnProductsForNullSearchInput(){
        when(productRepository.findByNameContains(null)).thenReturn(Arrays.asList());
        List<FoodItem> list = productServiceImpl.searchProducts(null);
        assertThat(list).hasSize(0);
    }

    @Test
    public void returnProductsForEmptySearchInput(){
        when(productRepository.findByNameContains("")).thenReturn(Arrays.asList());
        List<FoodItem> list = productServiceImpl.searchProducts("");
        assertThat(list).hasSize(0);
    }

    @Test
    public void buyProductWithValidAccount(){
        when(productRepository.getById(anyLong())).thenReturn(getFoodItem());
        when(paymentClient.createPayment(anyString(), anyString(), any(BigDecimal.class), anyString(), anyString())).thenReturn("Your payment id : 1");
        ResponseEntity response = productServiceImpl.buyProduct(1L, 1, 1L);
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.OK);
    }

    @Test
    public void buyProductWithInvalidAccount(){
        when(productRepository.getById(anyLong())).thenReturn(getFoodItem());
        when(paymentClient.createPayment(anyString(), anyString(), any(BigDecimal.class), anyString(), anyString())).thenReturn("something else");
        ResponseEntity response = productServiceImpl.buyProduct(1L, 1, 1L);
        assertThat(response.getStatusCode()).isSameAs(HttpStatus.BAD_REQUEST);
    }

}
