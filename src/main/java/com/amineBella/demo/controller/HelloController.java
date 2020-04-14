package com.amineBella.demo.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.amineBella.demo.entities.User;
import com.amineBella.demo.service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
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
    public InputStreamResource export() {
        return this.exportService.export();
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
