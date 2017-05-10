package com.example.jyotiprakashrai.bindserviceusindmessanger;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by jyotiprakashrai on 8/2/17.
 */

/*
If you need your interface to work across different processes,
 you can create an interface for the service with a Messenger.
 In this manner, the service defines a Handler that responds to different types of Message objects.
  This Handler is the basis for a Messenger that can then share an IBinder with the client, allowing
  the client to send commands to the service using Message objects. Additionally,
   the client can define a Messenger of its own, so the service can send messages back.

This is the simplest way to perform interprocess communication (IPC),
 because the Messenger queues all requests into a single thread so that
 you don't have to design your service to be thread-safe.
 */

public class MyService extends Service {


    Messenger mMessenger = new Messenger(new IncomingHandler());
    static final int JOB_1 = 1;
    static final int JOB_2 = 2;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        Toast.makeText(this, "Service Binding...", Toast.LENGTH_SHORT).show();
        return mMessenger.getBinder();
    }

    public class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {

                case JOB_1:
                    Toast.makeText(MyService.this, "Hello from Job 1", Toast.LENGTH_SHORT).show();
                    break;

                case JOB_2:
                    Toast.makeText(MyService.this, "Hellofrom Job 2", Toast.LENGTH_SHORT).show();
                    break;

                default:
                    super.handleMessage(msg);

            }
        }
    }
}
