package com.epam.esm.controller;

import com.epam.esm.model.service.GiftCertificateService;
import com.epam.esm.model.service.dto.CertificateDTO;
import com.epam.esm.model.service.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/certificate")
public class GiftCertificateController {

    private final GiftCertificateService certificateService;

    public GiftCertificateController(GiftCertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Response> handleException(ServiceException exception) {
        Response response = new Response(exception.getLocalizedMessage(), (long) HttpStatus.NOT_FOUND.value());
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CertificateDTO> find(@PathVariable(name = "id") Long id) throws ServiceException {
        CertificateDTO certificateDTO = certificateService.find(id);
        return ResponseEntity.ok(certificateDTO);

    }

    @PostMapping(value = "/")
    public ResponseEntity<Boolean> add(@RequestBody CertificateDTO certificateDTO) throws ServiceException {
        boolean result = certificateService.add(certificateDTO);
        return ResponseEntity.ok(result);
    }

    @PutMapping(value = "/", consumes = "application/json")
    public ResponseEntity<Boolean> update(@RequestBody CertificateDTO certificateDTO) throws ServiceException {
        boolean result = certificateService.update(certificateDTO);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(name = "id") Long id) throws ServiceException {
        boolean result = certificateService.delete(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/byName/{name}")
    public ResponseEntity<List<CertificateDTO>> findByTag(@PathVariable(name = "name") String tagName) throws ServiceException {
        List<CertificateDTO> certificates = certificateService.findByTag(tagName);
        return ResponseEntity.ok(certificates);
    }

    @GetMapping("/byPart/{part}")
    public ResponseEntity<List<CertificateDTO>> searchByNameOrDesc(@PathVariable(name = "part") String part) throws ServiceException {
        List<CertificateDTO> certificates = certificateService.searchByNameOrDesc(part);
        return ResponseEntity.ok(certificates);
    }

    @GetMapping("/sortByName")
    public ResponseEntity<List<CertificateDTO>> sortByName() throws ServiceException {
        List<CertificateDTO> certificates = certificateService.sortByName();
        return ResponseEntity.ok(certificates);
    }

    @GetMapping("/sortByNameDesc")
    public ResponseEntity<List<CertificateDTO>> sortByNameDesc() throws ServiceException {
        List<CertificateDTO> certificates = certificateService.sortByNameDesc();
        return ResponseEntity.ok(certificates);
    }

    @GetMapping("/sortByDate")
    public ResponseEntity<List<CertificateDTO>> sortByDate() throws ServiceException {
        List<CertificateDTO> certificates = certificateService.sortByDate();
        return ResponseEntity.ok(certificates);
    }

    @GetMapping("/sortByDateDesc")
    public ResponseEntity<List<CertificateDTO>> sortByDateDesc() throws ServiceException {
        List<CertificateDTO> certificates = certificateService.sortByDateDesc();
        return ResponseEntity.ok(certificates);
    }
}
