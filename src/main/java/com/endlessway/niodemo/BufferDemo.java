package com.endlessway.niodemo;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static com.endlessway.niodemo.ByteBufferUtil.debugAll;

/**
 * 将数据填充至多个 buffer
 */
public class BufferDemo {
    public static void main(String[] args) {
        /*
         * ByteBuffer 正确使用姿势
         * 1.向 buffer 写入数据，例如调用 channel.read(buffer)
         * 2.调用 flip() 切换至读模式
         * 3.从 buffer 读取数据，例如调用 buffer.get()
         * 4.调用 clear() 或 compact() 切换至写模式
         * 5.重复 1~4 步骤
         */
        try (RandomAccessFile file = new RandomAccessFile("helloword/3parts.txt", "rw")) {
            FileChannel channel = file.getChannel();
            ByteBuffer a = ByteBuffer.allocate(3);
            ByteBuffer b = ByteBuffer.allocate(3);
            ByteBuffer c = ByteBuffer.allocate(5);
            channel.read(new ByteBuffer[]{a, b, c});
            a.flip();
            b.flip();
            c.flip();
            debugAll(a);
            debugAll(b);
            debugAll(c);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 多个 buffer 的数据填充至 channel
 */
class BufferToChannel{
    public static void main(String[] args) {
        try (RandomAccessFile file = new RandomAccessFile("helloword/3parts.txt", "rw")) {
            FileChannel channel = file.getChannel();
            ByteBuffer d = ByteBuffer.allocate(4);
            ByteBuffer e = ByteBuffer.allocate(4);
            channel.position(11);
            d.put(new byte[]{'f', 'o', 'u', 'r'});
            e.put(new byte[]{'f', 'i', 'v', 'e'});
            d.flip();
            e.flip();
            debugAll(d);
            debugAll(e);
            channel.write(new ByteBuffer[]{d, e});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
