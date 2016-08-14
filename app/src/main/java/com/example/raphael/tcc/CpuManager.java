package com.example.raphael.tcc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CpuManager {
    private int numberOfCores;
    private String pathCPU = "cat /sys/devices/system/cpu/cpu";

    CpuManager(){
        String cores;
        try {
            cores = returnStringFromProcess(Runtime.getRuntime().exec("cat /sys/devices/system/cpu/present"));
            this.numberOfCores = Character.getNumericValue(cores.charAt(2))+1;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setProcessesRunning() {
        Process p;
        try {
            p = Runtime.getRuntime().exec("/system/bin/ps");
            returnStringFromProcess(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCoreUtilization(int coreNumber){
        StringBuilder path = new StringBuilder();
        String p= null;
        path.append("cat /sys/devices/system/cpu/cpu" + coreNumber + "/cpufreq/cpu_utilization");
        try {
             p = returnStringFromProcess(Runtime.getRuntime().exec(path.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p;
    }//ok

    public String getGovernorOfCore(int coreNumber){//ok
        String p=null;
        StringBuilder path = new StringBuilder();
        path.append(pathCPU + coreNumber + "/cpufreq/scaling_governor");
        try {
            p = returnStringFromProcess(Runtime.getRuntime().exec(path.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p;
    }//ok

    public String getSpeedOfCore(int coreNumber) {//ok
        StringBuilder path = new StringBuilder();
        path.append("cat /sys/devices/system/cpu/cpu" + coreNumber + "/cpufreq/scaling_cur_freq");
        String p = null;
        try {
            p = returnStringFromProcess(Runtime.getRuntime().exec(path.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p;
    }//ok

    public int getNumberOfCores() {
        return this.numberOfCores;
    }//ok

    private String returnStringFromProcess(Process proc) throws IOException {
        StringBuilder ps = new StringBuilder();
        String s;
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        while ((s = stdInput.readLine()) != null) {
            ps.append(s);
            ps.append('\n');
        }
        return ps.toString();
    }//ok
}

