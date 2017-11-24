package com.unistrong.luowei.baseio

import android.Manifest
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var tcpSocket: TcpSocket

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET)) {
            tcpSocket = TcpSocket()
            tcpSocket.connect({ buffer, size ->
                runOnUiThread {
                    receiveTextView.append("\n")
                    receiveTextView.append(String(buffer, 0, size))
                }
            })
            sendButton.setOnClickListener { if (!tcpSocket.write("hello world".toByteArray())) receiveTextView.text = "发送失败" }
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.INTERNET), 0)
        }

    }
}
