package com.leyou.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @version V0.1
 * @项目名称：leyou
 * @类名：LetouCorsConfigration
 * @类描述：
 * @创建人：liujiaxin
 * @创建时间：2021/1/17 14:29
 */
@Configuration
public class LeyouCorsConfiguration {

    @Bean
    public CorsFilter corsFilter(){
        //初始化cors配置对象
        CorsConfiguration configuration = new CorsConfiguration();

        //设置允许跨域的域名，如果要携带cookie，不能写*。 *：代表所有域名都可以跨域访问
        configuration.addAllowedOrigin("http://manager.leyou.com");
        //设置允许携带cookie（资格证书）
        configuration.setAllowCredentials(true);
        //设置允许所有的请求方法:GET POST PUT DELETE ...
        configuration.addAllowedMethod("*");
        //允许携带任何头信息
        configuration.addAllowedHeader("*");


        //初始化cors配置源对象
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**",configuration);
        return new CorsFilter(configurationSource);

    }
}
