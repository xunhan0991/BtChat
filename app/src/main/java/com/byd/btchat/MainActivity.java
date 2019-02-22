package com.byd.btchat;

import android.content.Intent;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
//https://blog.csdn.net/anddlecn/article/details/51880117
public class MainActivity extends AppCompatActivity {
    private MenuItem mConnectionMenuItem;
    private final int RESULT_CODE_BTDEVICE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        mConnectionMenuItem = menu.findItem(R.id.connect_menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.connect_menu: {
                //根据当前连接到状态，判断对应的响应方式，
                //目前，我们就暂时将它设计成满足条件1的状况，
//                //启动DeviceListActivity获取可以连接到设备名称
//                Intent i = new Intent(this, DeviceListActivity.class);
//                startActivityForResult(i, RESULT_CODE_BTDEVICE);

            }
            return true;

            case R.id.about_menu: {
                //启动关于界面
            }
            return true;

            default:
                return false;
        }
    }
}
