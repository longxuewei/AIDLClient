package cn.lxw.aidlclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button bt_add, bt_get;
    private BookManager manager;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        Intent intent = new Intent();
        intent.setAction("addBook");
        intent.setPackage("cn.lxw.aidlservers");

        bindService(intent, connection, Context.BIND_AUTO_CREATE);


    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            manager = BookManager.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private void initView() {
        bt_add = (Button) findViewById(R.id.bt_add);
        bt_get = (Button) findViewById(R.id.bt_get);

        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book("银瓶梅", 2);
                try {
                    manager.addBook(book);
                } catch (RemoteException e) {
                    e.printStackTrace();
                    Log.i(TAG, "onClick: 添加失败了");
                }
            }
        });


        bt_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    List<Book> books = manager.getBooks();
                    Log.i(TAG, "获取书本: " + books);
                } catch (RemoteException e) {
                    e.printStackTrace();

                }
            }
        });


    }


}
