package com.examenfinal.chasis.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.io.File;
import java.util.Arrays;

@RestController
public class ChasisController {

    ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
    long start = System.nanoTime();
    Runtime runtime = Runtime.getRuntime();
    int cpus = runtime.availableProcessors();
    long mmax = runtime.maxMemory() / 1024 / 1024;
    long mtotal = runtime.totalMemory() / 1024 / 1024;
    long mfree = runtime.freeMemory() / 1024 / 1024;
    File cDrive = new File("C:");
    // 1 s = 1,000 ms = 1,000,000 Âµs = 1,000,000,000 ns
    double elapseTime = (System.nanoTime() - start) / 1_000_000_000; /// *1.0e-9;

    @Setter
    @Getter
    public static class Chasis{
        Integer cpus;
        Long memoriaMaxima;
        Long memoriaTotal;
        Long memoriaLibre;
        Double tiempoSec;
        Double espacioTotal;
        Double espacioLibre;
    }

    @RequestMapping("/chasis")
    public Chasis getChasisData(){
        Chasis chasis = new Chasis();
        chasis.setCpus(runtime.availableProcessors());
        chasis.setMemoriaMaxima(runtime.maxMemory() / 1024 / 1024);
        chasis.setMemoriaTotal(runtime.totalMemory() / 1024 / 1024);
        chasis.setMemoriaLibre(runtime.freeMemory() / 1024 / 1024);
        chasis.setTiempoSec((double) ((System.nanoTime() - start) / 1_000_000_000));
        chasis.setEspacioTotal((double) cDrive.getTotalSpace() / 1073741824);
        chasis.setEspacioLibre((double) cDrive.getFreeSpace() / 1073741824);
        return chasis;
    }
}
