package com.ubagroup.switchemv.api;

import com.ubagroup.switchemv.dto.FundWalletRequest;
import com.ubagroup.switchemv.service.WalletFundingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wallet")
@RequiredArgsConstructor
public class WalletController {

    private final WalletFundingService walletFundingService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/fund")
    public ResponseEntity<String> fund(@RequestBody FundWalletRequest req) {

        walletFundingService.fund(req.getUserId(), req.getAmount());
        return ResponseEntity.ok("Wallet funded");
    }
}
