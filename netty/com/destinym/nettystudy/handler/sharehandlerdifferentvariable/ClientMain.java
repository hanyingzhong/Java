package com.destinym.nettystudy.handler.sharehandlerdifferentvariable;

/**
 * Created by destinym on 15/11/23.
 */
public class ClientMain {
    public static void main(String[] args) {
        String localhost = "127.0.0.1";
        String client = "deviceId";
        new Client(1, client, localhost);
        new Client(2, client, localhost);
    }
}
