package com.foodbooking;
import java.time.LocalDate;

import com.foodbooking.entity.UserOrder;
import com.foodbooking.repository.UserOrderRepository;
import com.foodbooking.service.impl.UserOrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserOrderServiceImplTest {
    @Mock
    UserOrderRepository userOrderRepository;

    @InjectMocks
    UserOrderServiceImpl userOrderServiceImpl;

    @Test
    public void testSaveOrder(){
        userOrderServiceImpl.saveOrder("","",1L,1,null,"");
        verify(userOrderRepository).save(any());
    }

    @Test
    public void testUserOrderHistory(){
        when(userOrderRepository.getByUserId(anyString())).thenReturn(getUserOrderList());
        List<UserOrder> list = userOrderServiceImpl.getUserHistory("");
        assertThat(list).hasSizeGreaterThan(0);
    }

    private List<UserOrder> getUserOrderList() {
        List<UserOrder> list = new ArrayList<>();
        UserOrder order = new UserOrder();
        order.setId(0L);
        order.setUserId("");
        order.setPaymentId("");
        order.setProductId(0L);
        order.setOrderDate(LocalDate.now());
        order.setTotalPrice(0);
        order.setAddress("");
        list.add(order);
        return list;
    }
}
