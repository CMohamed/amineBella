package com.amineBella.demo.controller;

import com.amineBella.demo.entities.User;
import com.amineBella.demo.service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.amineBella.demo.DemoApplication.users;

@RestController
@CrossOrigin("http://localhost:3000")
public class HelloController {

    private ExportService exportService;

    @Autowired
    public HelloController(ExportService exportService) {
        this.exportService = exportService;
    }

    @GetMapping("/export")
    public ResponseEntity<Resource> exportConnections() {
        try {
            InputStreamResource response = exportService.export();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=".concat("users").concat(".xlsx"))
                    .contentType(MediaType.APPLICATION_OCTET_STREAM).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/sum")
    public Integer sum(@RequestParam Integer a, @RequestParam Integer b) {
        return a+b;
    }

    @PostMapping("/user")
    public List<User> addUser(@RequestBody User user) {
        System.out.println(user.getFirstName());
        System.out.println(user.getLastName());
        System.out.println(user.getTel());
        // todo: you can add this user to your database
        users.add(user);
        return users;
    }
}
