package test.net.atshq.messengerservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private boolean isConnect=false;

    //create messenger reference
    Messenger messengerService;

    //create service connection
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            isConnect=true;
            //get messenger reference
            messengerService = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isConnect=false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startService(View view) {
        Intent intent = new Intent(this,MessengerService.class);
        //start service
        bindService(intent,connection,BIND_AUTO_CREATE);
    }

    public void stopService(View view) {
        //stop service
        unbindService(connection);
    }

    public void doSomething(View view) {

        //create message for sending data to service
        Message message = Message.obtain(null,MessengerService.SAY_HELLO,0,0);

        if(isConnect){
            try {
                //data send to service
                messengerService.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

}
