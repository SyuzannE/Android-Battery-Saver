package com.example.raphael.tcc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Raphael on 18-Apr-16.
 */
public class ProcessesRunning {
    public void setProcessesRunning() {
        Process p;
        try {
            p = Runtime.getRuntime().exec("/system/bin/ps");
            returnStringFromProcess(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getCores() {
        Process p;
        StringBuilder cores = new StringBuilder();
        int ini, fim;
        System.out.println(getCoreUtilization(0));
        return cores.toString();
    }
    public int getCoreUtilization(int coreNumber){
        StringBuilder path = new StringBuilder();
        path.append("cat /sys/devices/system/cpu/cpu" + coreNumber + "/cpufreq/cpu_utilization");
        int utilization=-1;
        try {
            String p = returnStringFromProcess(Runtime.getRuntime().exec(path.toString()));
            utilization = Integer.parseInt(p.trim());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return utilization;
    }
    public String getGovernorOfCore(int coreNumber){
        StringBuilder path = new StringBuilder();
        path.append("cat /sys/devices/system/cpu/cpu" + coreNumber + "/cpufreq/scaling_governor");
        String p=null;
        try {
            p = returnStringFromProcess(Runtime.getRuntime().exec(path.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p;
    }
    public int getSpeedOfCore(int coreNumber) {
        StringBuilder path = new StringBuilder();
        path.append("cat /sys/devices/system/cpu/cpu" + coreNumber + "/cpufreq/scaling_cur_freq");
        int speed = -1;
        String p = null;
        try {
            p = returnStringFromProcess(Runtime.getRuntime().exec(path.toString()));
            speed = Integer.parseInt(p.trim());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return speed;
    }

    public String getNumberOfCores() {
        String cores = null;
        try {
            cores = returnStringFromProcess(Runtime.getRuntime().exec("cat /sys/devices/system/cpu/online "));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cores;
    }

    private String returnStringFromProcess(Process proc) throws IOException {
        StringBuilder ps = new StringBuilder();
        String s;
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        while ((s = stdInput.readLine()) != null) {
            ps.append(s);
            ps.append('\n');
        }
        return ps.toString();
    }
}

