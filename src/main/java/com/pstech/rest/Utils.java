package com.pstech.rest;

import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.net.ssl.*;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

@Component
public class Utils implements ApplicationContextAware, EnvironmentAware {

    @Getter
    private static ApplicationContext applicationContext;

    @Getter
    private static Environment environment;

    public static Map<String, Object> getAllProperties() {
        Map<String, Object> result = new HashMap<>();
        for (PropertySource<?> propertySource : ((AbstractEnvironment) environment).getPropertySources()) {
            if (propertySource instanceof MapPropertySource) {
                result.putAll(((MapPropertySource) propertySource).getSource());
            }
        }
        return result;
    }

    public static String toMhs(double hashrate) {
        return String.format("%.2f", hashrate / 1000000) + " MH/s";
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Utils.applicationContext = applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        Utils.environment = environment;
    }

    public void setup() {

    }

    @PostConstruct
    public void trustManager() throws NoSuchAlgorithmException, KeyManagementException {
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509ExtendedTrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) {
                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    @Override
                    public void checkClientTrusted(X509Certificate[] x509Certificates, String s, Socket socket) {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] x509Certificates, String s, Socket socket) {
                    }

                    @Override
                    public void checkClientTrusted(X509Certificate[] x509Certificates, String s, SSLEngine sslEngine) {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] x509Certificates, String s, SSLEngine sslEngine) {
                    }
                }
        };

        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
    }
}
