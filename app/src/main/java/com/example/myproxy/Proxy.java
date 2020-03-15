package com.example.myproxy;

import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.CharacterData;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Handles a socket connection to the proxy server from the client and uses 2
 * threads to proxy between server and client
 *
 * @author jcgonzalez.com
 *
 */
class  Proxy extends Thread {
    private Socket sClient, server;
    private InputStream inFromClient, inFromServer;
    private OutputStream outToClient, outToServer;

    public Proxy(Socket sClient) {
        this.sClient = sClient;
    }

    public void run() {
        System.out.println("running");
        try {
            inFromClient = sClient.getInputStream();
            outToClient = sClient.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        int bytes_read;
        byte[] request = null;
        try {
            int len = 0;
            byte[] temp = new byte[4098];
            while (inFromClient.available() != 0 && (bytes_read = inFromClient.read(temp)) != -1) {
                System.out.println("reading request");
                if (len == 0) {
                    System.out.println("len = 0");
                    request = new byte[bytes_read];
                    System.arraycopy(temp, 0, request, 0, bytes_read);
                    len = 4098;
                    continue;
                }
                byte[] temp0 = request;
                request = new byte[len + bytes_read];
                System.arraycopy(temp0, 0, request, 0, len);
                System.arraycopy(temp, 0, request, len, bytes_read);
                len += bytes_read;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("finished reading");
        String message = "";
        final byte[] completeRequest = request;
        if (request != null) {
            message = new String(request, StandardCharsets.UTF_8);
            System.out.println(message);
        } else {
            try {
                sClient.close();
            }catch (IOException e){
                e.printStackTrace();
            }
            System.out.println("message is null");
            //return;
        }
        final String[] arr = message.split("\n");
        final String[] head = arr[0].split(" ");
        // System.out.println(head[1]);
        // Log.d("kimiya", temp.length + "");
        if (head.length == 3 && head[2].trim().equals("HTTP/1.1")) {
            System.out.println("true");
            String host;
            int port;
            host = arr[1].split(":")[1].trim();
            URL url;
            try {
                url = new URL(head[1]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return;
            }
            port = url.getPort();
            if (port == -1)
                port = 80;
            try {

                server = new Socket(java.net.Proxy.NO_PROXY);
                server.connect(new InetSocketAddress(host, port));
                System.out.println("connected to " + host);
                inFromServer = server.getInputStream();

                inFromClient.available();
                outToServer = server.getOutputStream();
                outToServer.write(completeRequest);
                outToServer.flush();
                server.shutdownOutput();

            } catch (ConnectException e1) {
                System.out.println(host);
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            byte[] reply = null;
            try {
                int len = 0;
                byte[] temp = new byte[4098];

                while ((bytes_read = inFromServer.read(temp)) != -1) {
                    outToClient.write(temp, 0, bytes_read);
                    outToClient.flush();
                    System.out.println("reading reply");
                    //   if(inFromServer.available() == 0)
                    //     break;
                }
                System.out.println("wrote");
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    if (server != null)
                        server.close();
                    if (sClient != null)
                        sClient.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

    }

}