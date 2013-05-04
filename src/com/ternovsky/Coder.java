package com.ternovsky;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.CodeSource;

/**
 * Created with IntelliJ IDEA.
 * User: ternovsky
 * Date: 04.05.13
 * Time: 15:05
 * To change this template use File | Settings | File Templates.
 */
public class Coder {

    public static void main(String[] args) {
        if (args.length == 3) {
            try {
                byte[] inputBytes = Files.readAllBytes(Paths.get(args[0]));
                byte[] keyBytes = Files.readAllBytes(Paths.get(args[2]));

                Coder coder = new Coder();
                byte[] outputBytes = coder.code(inputBytes, keyBytes);

                Files.write(Paths.get(args[1]), outputBytes, StandardOpenOption.CREATE_NEW);
            } catch (Exception e) {
                printMessage();
            }
        } else {
            printMessage();
        }
    }

    private static void printMessage() {
        CodeSource codeSource = Coder.class.getProtectionDomain().getCodeSource();
        File jarFile = new File(codeSource.getLocation().getPath());
        System.out.println("Error: Invalid or corrupt jarfile " + jarFile.getName());
    }

    public byte[] code(byte[] message, byte[] key) {

        byte[] codedMessage = new byte[message.length];

        for (int i = 0; i < message.length; i++) {
            codedMessage[i] = (byte) (message[i] ^ key[i % key.length]);
        }

        return codedMessage;
    }

}
