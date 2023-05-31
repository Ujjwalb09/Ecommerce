package com.example.Ecommerce.controller;

import com.example.Ecommerce.dto.RequestDto.CheckoutCartRequestDto;
import com.example.Ecommerce.dto.RequestDto.ItemRequestDto;
import com.example.Ecommerce.dto.ResponseDto.CartResponseDto;
import com.example.Ecommerce.dto.ResponseDto.OrderResponseDto;
import com.example.Ecommerce.model.Item;
import com.example.Ecommerce.service.CartService;
import com.example.Ecommerce.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ItemService itemService;
    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity addCart(@RequestBody ItemRequestDto itemRequestDto){
        try{
             Item item = itemService.createItem(itemRequestDto);
            CartResponseDto cartResponseDto = cartService.addTocart(item, itemRequestDto);
            return new ResponseEntity<>(cartResponseDto, HttpStatus.ACCEPTED);
        } catch (Exception e){
             return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/checkout") //correct the bug
    public ResponseEntity checkoutCart(@RequestBody CheckoutCartRequestDto checkoutCartRequestDto){
        try {
            OrderResponseDto orderResponseDto = cartService.checkoutCart(checkoutCartRequestDto);
            return new ResponseEntity(orderResponseDto, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //add the functionality of email sending in direct order and checkout cart
    //kunaljindal995@gmail.com

    //integrate swagger
}
