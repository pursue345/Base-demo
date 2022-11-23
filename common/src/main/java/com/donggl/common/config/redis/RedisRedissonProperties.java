package com.donggl.common.config.redis;

/**
 * @ClassName RedisRedissonProperties
 * @Description TODO
 * @Author donggl
 * @Date 2022/11/21 14:10
 * @Version 1.0
 */
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 202013031
 * @date 2022/1/11 11:30
 */

@Configuration
@ConfigurationProperties(prefix = "spring.redis")
public class RedisRedissonProperties {

    private Cluster cluster;
    public Cluster getCluster() {
        return cluster;
    }
    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    public static class Cluster {
        /**
         * Comma-separated list of "host:port" pairs to bootstrap from. This represents an
         * <p>
         * "initial" list of cluster nodes and is required to have at least one entry.
         */
        private List<String> nodes;
        /**
         * Maximum number of redirects to follow when executing commands across the
         * <p>
         * cluster.
         */
        private Integer maxRedirects;
        public List<String> getNodes() {
            return nodes;
        }
        public void setNodes(List<String> nodes) {
            this.nodes = nodes;
        }
        public Integer getMaxRedirects() {
            return maxRedirects;
        }
        public void setMaxRedirects(Integer maxRedirects) {
            this.maxRedirects = maxRedirects;
        }
    }
}
