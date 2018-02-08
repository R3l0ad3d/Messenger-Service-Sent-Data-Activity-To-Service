package test.net.atshq.messengerservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

public class MessengerService extends Service {

    public static final int SAY_HELLO = 1;

    //create handler for messenger communication
    Handler messageHandeler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            if(msg.what==SAY_HELLO){
                Log.d("Service","From Messenger");
                Toast.makeText(MessengerService.this, "From Messenger", Toast.LENGTH_SHORT).show();
            }
        }
    };

    Messenger messenger = new Messenger(messageHandeler);
    public MessengerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return messenger.getBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Service","onCreate");
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Service","onDestroy");
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
    }
}
