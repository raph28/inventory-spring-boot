package com.ennea.inventory.contollers;

import com.ennea.inventory.Services.FileUploadService;
import com.ennea.inventory.Services.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Map;

@RestController
public class InventoryController
{
    @Autowired
    FileUploadService fileUploadService;

    @Autowired
    InventoryService inventoryService;
    
    private static final Logger logger= LoggerFactory.getLogger(InventoryController.class);

    @RequestMapping("/")
    public String hello()
    {
        return "Hello User";
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {

        String message = "";
        try {
            InputStream inputStream = file.getInputStream();
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            int size=fileUploadService.processFile(inputStream);
            return ResponseEntity.status(HttpStatus.OK).body("Success");
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("failed");
        }
    }

    @GetMapping("/stock/{supplierId}")
    public ResponseEntity<Map<String,Object>> getAllStock(@PathVariable("supplierId") int supplierId,
                                                               @RequestParam(required = false) String name,
                                                               @RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int size){
        try{
            Map<String, Object> response;
            if(name == null){
                response=inventoryService.getBySupplierId(supplierId,page,size);
            }
            else{
                response=inventoryService.getBySupplierIdAndName(supplierId,name,page,size);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/stock/unExpired")
    public ResponseEntity<Map<String,Object>> getAllStock(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size){
        try{
            Map<String, Object> response=inventoryService.getUnExpiredProducts(page,size);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
