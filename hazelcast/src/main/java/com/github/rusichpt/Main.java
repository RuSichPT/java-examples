package com.github.rusichpt;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();

        IMap<String, String> cache = hazelcastInstance.getMap("cache");

        cache.put("key1", "value1");
        cache.put("key2", "value2");

        if (cache.containsKey("key1"))
        {
            System.out.println("key1 is present");
        }

        cache.remove("key1");

        System.out.println(cache.get("key1"));

        Thread.sleep(2000);

        hazelcastInstance.shutdown();
    }
}