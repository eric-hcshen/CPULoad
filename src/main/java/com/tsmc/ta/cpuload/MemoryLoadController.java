package com.tsmc.ta.cpuload;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/memory")
public class MemoryLoadController<Public> {
    static List<Byte[]> memoryBuffer;
    static final int OneG = 1024*1024*1024;
    static {
        memoryBuffer = new ArrayList<>();
    }
    @GetMapping("/info")
    public String GetInfo(){
        Runtime rt = Runtime.getRuntime();
        StringBuffer memoryInfo = new StringBuffer();
        memoryInfo.append("Total Memory: ").append(rt.totalMemory()/ OneG).append(" Free Memory: ")
        .append(rt.freeMemory() / OneG);  
        return memoryInfo.toString();    
    }

    @GetMapping("/add")
    public String AddLoad() {
        Byte[] oneGByte = new Byte[OneG/5];
        memoryBuffer.add(oneGByte);
        Runtime rt = Runtime.getRuntime();
        StringBuffer memoryInfo = new StringBuffer();
        memoryInfo.append("Total Memory: ").append(rt.totalMemory()/(1024*1024)).append(" Free Memory: ")
                .append(rt.freeMemory() / (1024*1024));
        return memoryInfo.toString();
    }

    @GetMapping("/release")
    public String Release() {
        memoryBuffer.clear();
        return "Released";
    }
    
}

