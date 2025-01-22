package org.example.camerarentweb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

//@Configuration
//public class RedisConfig {
//    @Value("${redis.host}")
//    private String redisHost;
//
//    @Value("${redis.port}")
//    private int redisPort;
//
//    @Bean
//    public RedisConnectionFactory redisConnectionFactory() {
//        return new LettuceConnectionFactory( redisHost, redisPort);
//    }
//
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(connectionFactory);
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//        return template;
//    }
//
////    @Bean
////    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
////        return RedisCacheManager.create(connectionFactory);
////    }
//    @Bean
//    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
//
//        RedisCacheConfiguration equipmentKitsCacheConfig = RedisCacheConfiguration
//                .defaultCacheConfig()
//                .entryTtl(Duration.ofDays(1));
//        RedisCacheConfiguration mostPopularCacheConfig = RedisCacheConfiguration
//                .defaultCacheConfig()
//                .entryTtl(Duration.ofMinutes(10));
//        RedisCacheConfiguration equipmentSalesCacheConfig = RedisCacheConfiguration
//                .defaultCacheConfig()
//                .entryTtl(Duration.ofMinutes(10));
//
//        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration
//                .defaultCacheConfig()
//                .entryTtl(Duration.ofHours(1));
//
//
//        return RedisCacheManager.builder(connectionFactory)
//                .withCacheConfiguration("equipmentKits", equipmentKitsCacheConfig)
//                .withCacheConfiguration("mostPopular", mostPopularCacheConfig)
//                .withCacheConfiguration("equipmentSales", equipmentSalesCacheConfig)
//                .cacheDefaults(defaultCacheConfig)
//                .build();
//    }
//}
//
@Configuration
public class RedisConfig {
    @Value("${redis.host}")
    private String redisHost;

    @Value("${redis.port}")
    private int redisPort;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(redisHost, redisPort);

        return new LettuceConnectionFactory(configuration);
    }

    @Bean
    public RedisCacheManager cacheManager() {
        RedisCacheConfiguration cacheConfig = myDefaultCacheConfig(Duration.ofMinutes(10)).disableCachingNullValues();

        return RedisCacheManager.builder(redisConnectionFactory())
                .cacheDefaults(cacheConfig)
                .withCacheConfiguration("equipmentKits", myDefaultCacheConfig(Duration.ofMinutes(10)))
                .withCacheConfiguration("mostPopular", myDefaultCacheConfig(Duration.ofMinutes(10)))
                .withCacheConfiguration("equipmentSales", myDefaultCacheConfig(Duration.ofMinutes(10)))
                .withCacheConfiguration("equipmentTypesByCategoryAndPrice", myDefaultCacheConfig(Duration.ofMinutes(10)))
                .withCacheConfiguration("equipmentTypeByName", myDefaultCacheConfig(Duration.ofMinutes(10)))
                .build();
    }

    private RedisCacheConfiguration myDefaultCacheConfig(Duration duration) {
        return RedisCacheConfiguration
                .defaultCacheConfig()
                .entryTtl(duration)
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }
}