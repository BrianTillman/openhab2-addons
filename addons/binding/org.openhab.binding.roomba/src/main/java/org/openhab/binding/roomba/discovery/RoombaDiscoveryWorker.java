package org.openhab.binding.roomba.discovery;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RoombaDiscoveryWorker {
    private static final int RoombaDiscoveryPort = 5678;
    private static final String RoombaDiscoveryMessage = "irobotmcs";
    private static boolean isListening = false;
    private static final Logger logger = LoggerFactory.getLogger(RoombaDiscoveryWorker.class);
    private DatagramSocket discSock;

    public void startDiscovery() {
        bindDiscoverySocket();
        listenForRoombaDiscoveryResponse();
        sendDiscoveryMessage();
    }

    private void sendDiscoveryMessage() {
        byte[] msg = RoombaDiscoveryMessage.getBytes();
        int msgLength = msg.length;
        try {
            DatagramPacket packet = new DatagramPacket(msg, msgLength, InetAddress.getByName("255.255.255.255"),
                    RoombaDiscoveryPort);
        } catch (UnknownHostException e) {
            logger.error("Unable to send Roomba discovery message.", e);
            e.printStackTrace();
        }
    }

    private void listenForRoombaDiscoveryResponse() {
        isListening = true;
        new Thread() {
            @Override
            public void run() {
                while (isListening) {
                    receiveDiscoveryResponse();
                }
            }
        }.start();
    }

    private void receiveDiscoveryResponse() {
        DatagramPacket response = buildResponsePacket();
        try {
            discSock.receive(response);
            String msg = new String(response.getData());
            if (msg.length() > 0) {
                evalResponseForRoombaDevice(msg);
            }
        } catch (Exception e) {
            logger.error("Exception receiving discovery response.", e);
            e.printStackTrace();
        }
    }

    private void evalResponseForRoombaDevice(String response) {
        logger.error(response);
    }

    private DatagramPacket buildResponsePacket() {
        byte[] buf = new byte[100000];
        return new DatagramPacket(buf, buf.length);
    }

    private void bindDiscoverySocket() {
        try {
            // TOOD: Do we need to bind to a specific interface?
            discSock = new DatagramSocket(RoombaDiscoveryPort);
            discSock.setBroadcast(true);
            logger.debug("Bound discovery socket on UDP port: " + RoombaDiscoveryPort + " .");
        } catch (SocketException e) {
            logger.error("Unable to bind UDP socket on port: " + RoombaDiscoveryPort + " .", e);
        }
    }

    private void unbindDiscoverySocket() {
        if ((discSock != null) && (discSock.isBound())) {
            discSock.close();
            logger.debug("Closed discovery socket on UDP port: " + RoombaDiscoveryPort + " .");
        }
    }
}
