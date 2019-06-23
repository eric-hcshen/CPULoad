package com.tsmc.ta.cpuload;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tsmc.ta.benchmark.RandomAccessFileTest;;

@RestController
@RequestMapping("/disk")

/**
 * DiskLoadController
 */
public class DiskLoadController {

    @GetMapping("/add")
    public String AddLoad() {
        StringWriter writer = new StringWriter();
        //new RandomAccessFileTest(runs).call(1000, new PrintWriter(System.out, true));
        try {
            RandomAccessFileTest RA = new RandomAccessFileTest(1000);
            RA.call(writer);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return writer.toString();
    }
    
}