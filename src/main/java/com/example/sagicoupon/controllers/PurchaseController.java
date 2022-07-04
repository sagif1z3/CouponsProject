package com.example.sagicoupon.controllers;

import com.example.sagicoupon.model.Purchase;
import com.example.sagicoupon.services.PurchaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping("/addnew")
    public Purchase addNewPurchase(@RequestBody Purchase purchase){
        return purchaseService.addPurchase(purchase);
    }

    @GetMapping("/{id}/show")
    public Purchase showPurchaseById(@PathVariable Long id){
        return purchaseService.findPurchaseById(id);
    }

    @GetMapping("/showall")
    public List<Purchase> showAllPurchases() {
        return purchaseService.getAllPurchases();
    }

    @PutMapping("/update")
    public Purchase updatePurchase (@RequestBody Purchase purchase){
        return purchaseService.updatePurchase(purchase);
    }

    @GetMapping("/{id}/delete")
    public void deletePurchaseById(@PathVariable Long id){
        purchaseService.deletePurchaseById(id);
    }
}
