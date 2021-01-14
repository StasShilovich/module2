package com.epam.esm.controller;

import com.epam.esm.model.dao.entity.GiftCertificate;
import com.epam.esm.model.service.GiftCertificateService;
import com.epam.esm.model.service.dto.CertificateDTO;
import com.epam.esm.model.service.exception.ServiceException;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/certificate")
public class GiftCertificateController {

    private GiftCertificateService certificateService;

    public GiftCertificateController(GiftCertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CertificateDTO> find(@PathVariable(name = "id") Long id) {
        try {
            CertificateDTO certificateDTO = certificateService.find(id);
            return ResponseEntity.ok(certificateDTO);
        } catch (ServiceException e) {
            return ResponseEntity.of(Optional.of(new CertificateDTO()));
        }

    }

    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity<Boolean> add(@RequestBody CertificateDTO certificateDTO) {
        try {
            boolean result = certificateService.add(certificateDTO);
            return ResponseEntity.ok(result);
        } catch (ServiceException e) {
            return ResponseEntity.ok(false);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(name = "id") Long id) {
        try {
            boolean result = certificateService.delete(id);
            return ResponseEntity.ok(result);
        } catch (ServiceException e) {
            return ResponseEntity.ok(false);
        }
    }
}
