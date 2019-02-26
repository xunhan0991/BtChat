package com.byd.btchat;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.byd.btchat.adapter.DeviceItemAdapter;

import java.util.Set;

public class DeviceListActivity extends AppCompatActivity {
    private BluetoothAdapter mBluetoothAdapter;
    private ListView mBtDeviceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DeviceItemAdapter adapter=new DeviceItemAdapter(this,R.layout.listview);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mBtDeviceList = findViewById(R.id.device_list);
        mBtDeviceList.setAdapter(adapter);
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        //将其添加到设备列表中
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                adapter.add(device);
            }
            Log.d("MainActivity",pairedDevices.size()+"");
        }
        //注册广播
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(mReceiver, filter);
        //开始搜索

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            mBluetoothAdapter.startDiscovery();
        }
        return super.onOptionsItemSelected(item);
    }
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            //找到设备
            if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                Log.d("MainActivity", "开始扫描...");
            }
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                //避免重复添加已经绑定过的设备
                if (device!=null){
                    if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                        DeviceItemAdapter adapter = (DeviceItemAdapter) mBtDeviceList.getAdapter();
                        adapter.add(device);
                        adapter.notifyDataSetChanged();
                        Log.d("MainActivity","sss");
                    }
                }else {
                    Log.d("MainActivity","aaa");
                }
            }
            if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                Log.d("MainActivity", "扫描结束.");
            }
        }
    };
}