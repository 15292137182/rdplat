//package io.rdplat.common;
//
//import redis.clients.jedis.HostAndPort;
//import redis.clients.jedis.JedisCluster;
//import redis.clients.jedis.JedisPoolConfig;
//
//import java.io.IOException;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//
//public class RedisCluster {
//
//    public static void main(String[] args) {
//        Set<HostAndPort> set = new HashSet<>();
//        set.add(new HostAndPort("192.168.5.15",8001));
//        set.add(new HostAndPort("192.168.5.15",8002));
//        set.add(new HostAndPort("192.168.5.15",8003));
//        set.add(new HostAndPort("192.168.5.15",8004));
//        set.add(new HostAndPort("192.168.5.15",8005));
//        set.add(new HostAndPort("192.168.5.15",8006));
//        set.add(new HostAndPort("192.168.5.15",8007));
//        set.add(new HostAndPort("192.168.5.15",8008));
//        set.add(new HostAndPort("192.168.5.15",8009));
//
//        JedisPoolConfig config = new JedisPoolConfig();
//        config.setMaxTotal(100);
//        config.setMaxIdle(10);
//        config.setTestOnBorrow(true);
//
//        JedisCluster cluster = new JedisCluster(set,5000,10,config);
//        System.out.println(cluster.get("8001"));
//        System.out.println(cluster.set("java","12312312testes_java"));
//        try {
//            cluster.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
