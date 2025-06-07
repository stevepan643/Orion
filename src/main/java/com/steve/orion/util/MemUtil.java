package com.steve.orion.util;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

public class MemUtil {

    public static long getTotalMemory() {
        return Runtime.getRuntime().totalMemory();
    }

    public static long getFreeMemory() {
        return Runtime.getRuntime().freeMemory();
    }

    public static long getMaxMemory() {
        return Runtime.getRuntime().maxMemory();
    }

    public static long getUsedMemory() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    public static long getCommittedVirtualMemory() {
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        return osBean.getCommittedVirtualMemorySize();
    }

    public static long getTotalPhysicalMemory() {
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        return osBean.getTotalMemorySize();
    }

    public static long getFreePhysicalMemory() {
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        return osBean.getFreeMemorySize();
    }

    public static long getProcessMemoryUsageBytes() {
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        return osBean.getProcessCpuLoad() >= 0 ? (long) osBean.getProcessCpuLoad() : -1;
    }

    public static long getProcessUsedMemory() {
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        return osBean.getProcessCpuTime();
    }

    public static long getProcessResidentMemory() {
        com.sun.management.OperatingSystemMXBean sunOS =
                (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        return sunOS.getProcessCpuTime();
    }

    public static long toMB(long bytes) {
        return bytes / 1024 / 1024;
    }
}
