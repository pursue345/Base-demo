//package com.donggl.common.config.es;
//
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.client.ClientConfiguration;
//import org.springframework.data.elasticsearch.client.RestClients;
//
///**
// * @ClassName EsConfig
// * @Description TODO
// * @Author donggl
// * @Date 2022/12/1 21:10
// * @Version 1.0
// */
//@Configuration
//public class EsConfig {
//
//    @Bean
//    public RestHighLevelClient highLevelClient(){
//        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
//                .connectedTo("localhost:9200")
//                .build();
//        return RestClients.create(clientConfiguration).rest();
//    }
//}
//
