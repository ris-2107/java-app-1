package com.example.javaapp1.utils;

public class ServiceUtils {

    public String getEnv() {
        var clusterType = System.getenv("cluster_type");
        if (clusterType.equalsIgnoreCase("local")) return ClusterType.LOCAL.name();
        return ClusterType.PRODUCTION.name();
    }
}
