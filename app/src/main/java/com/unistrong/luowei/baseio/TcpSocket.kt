package com.unistrong.luowei.baseio

import com.unistrong.luowei.factorysuite.pc.serial.BaseIO
import java.net.Socket
import kotlin.concurrent.thread

/**
 * Created by luowei on 2017/11/24.
 */
class TcpSocket {
    val baseIo = BaseIO()
    fun connect(callback: (buffer: ByteArray, size: Int) -> Unit) {
        thread {
            val socket = Socket("192.168.1.104", 6688)
            val outputStream = socket.getOutputStream()
            val inputStream = socket.getInputStream()
            baseIo.start(inputStream, outputStream, callback)
        }
    }

    fun stop() {
        baseIo.stop()
    }

    fun write(buffer: ByteArray): Boolean {
        return baseIo.write(BaseIO.Packet(buffer))
    }

}