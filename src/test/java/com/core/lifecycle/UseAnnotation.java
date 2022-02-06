package com.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class UseAnnotation {
    private String url;

    public UseAnnotation() {
        System.out.println("생성자 호출");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        System.out.println("## url: " + url + ", connection complete");
    }

    public void call(String msg) {
        System.out.println("## call " + url + ", message: " + msg);
    }

    public void disconnect() {
        System.out.println("## network close ##");
    }

    @PostConstruct
    public void init() throws Exception {
        /** 초기화 콜백 **/
        connect();
        call("msg blabla");
    }

    @PreDestroy
    public void close() throws Exception {
        /** 소멸 callback **/
        disconnect();
    }
}
