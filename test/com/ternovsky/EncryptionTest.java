package com.ternovsky;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: ternovsky
 * Date: 04.05.13
 * Time: 15:19
 * To change this template use File | Settings | File Templates.
 */
public class EncryptionTest {

    private static final int TEST_COUNT = 10000;
    private static Random random = new Random();

    @Test
    public void test() {

        Coder coder = new Coder();

        for (int n = 0; n < TEST_COUNT; n++) {
            byte[] key = randomBytes();
            byte[] message = randomBytes();

            byte[] codedMessage = coder.code(message, key);
            byte[] decodedMessage = coder.code(codedMessage, key);

            Assert.assertEquals(message.length, decodedMessage.length);
            for (int i = 0; i < message.length; i++) {
                Assert.assertEquals(message[i], decodedMessage[i]);
            }
        }
    }

    private byte[] randomBytes() {
        byte[] bytes = new byte[1 + random.nextInt(100)];
        random.nextBytes(bytes);

        return bytes;
    }
}
