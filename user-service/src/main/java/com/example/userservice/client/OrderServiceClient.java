package com.example.userservice.client;

import com.example.userservice.error.FeignErrorDecoder;
import com.example.userservice.vo.ResponseOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="order-service", configuration = FeignErrorDecoder.class) // 이 애노테이션을 통해서 유레카에 등록된 msa 네임을 지정,
                                                                            // FeignErrorDecoder에 상속하고 있는 ErrorDecoder를 사용할 수 있게 지정
public interface OrderServiceClient {

    @GetMapping("/order-service/{userId}/orders")
    List<ResponseOrder> getOrders(@PathVariable String userId);
}
