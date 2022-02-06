package com.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class UseSpring implements InitializingBean, DisposableBean {
    private String url;

    public UseSpring() {
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

    @Override
    public void afterPropertiesSet() throws Exception {
        /** 초기화 콜백 **/
        System.out.println("UseSpring.afterPropertiesSet");
        connect();
        call("msg blabla");
    }

    @Override
    public void destroy() throws Exception {
        /** 소멸 callback **/
        System.out.println("UseSpring.destroy");
        disconnect();
    }
}
