package com.gl.emms.whale.demo;

import com.gl.emms.client.android.EMMSListenerManager;
import com.gl.emms.client.android.EMMSPushManager;
import com.gl.emms.client.android.OnEMMSMessageListener;
import com.gl.emms.nio.mutual.ReplyBody;
import com.gl.emms.nio.mutual.RequestBody;
import com.gl.emms.nio.mutual.ServerMessage;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class DemoMainActivity extends ActionBarActivity implements OnEMMSMessageListener{
	boolean isConnected;
	public Handler handler ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demomain);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
			.add(R.id.container, new PlaceholderFragment()).commit();
		}
		handler = new Handler() {  
			@Override public void handleMessage(Message msg) {//覆盖handleMessage方法  
				switch (msg.what) {//根据收到的消息的what类型处理  
				case 0x01:  
					Bundle bundle = msg.getData();
					EMMSPushManager.connect(DemoMainActivity.this,bundle.getString("ip"), bundle.getInt("port"));
					break;  
				default:  
					super.handleMessage(msg);//这里最好对不需要或者不关心的消息抛给父类，避免丢失消息  
					break;  
				}  
			}  
		};
		EMMSListenerManager.registerMessageListener(this,this);
		//EMMSPushManager.connect(this,"192.168.0.101", 12345);
	}
	@Override
	public void finish() {
		super.finish();
		EMMSListenerManager.removeMessageListener(this);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.demo_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public class PlaceholderFragment extends Fragment implements OnClickListener {
		

		private Button openButton;
		
		private TextView ipText;
		private TextView portText;
		public PlaceholderFragment() {
		}
		@Override
		public void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			//启动时注册监听器
			
		}
		@Override
		public void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
			//关闭时移除监听器
			
		}
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_demomain,
					container, false);
			openButton = (Button) rootView.findViewById(R.id.open_btn);
			openButton.setOnClickListener(this);
			
			ipText = (TextView) rootView.findViewById(R.id.address);
			portText = (TextView) rootView.findViewById(R.id.port);
			return rootView;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.open_btn:
				if (isConnected) {
					Log.v("demo", "断开");
				}else {
					Log.v("demo", "连接");
					Message message = new Message();
					message.what = 0x01;
					Bundle bundle = new Bundle();
					bundle.putString("ip", ipText.getText().toString());
					bundle.putInt("port", Integer.parseInt(portText.getText().toString()));
					message.setData(bundle);
					handler.sendMessage(message);
				}

				break;

			default:
				break;
			}
		}

	}

	@Override
	public void onConnectionStatus(boolean arg0) {
		// TODO Auto-generated method stub
		if (arg0) {
			Log.v("demo", "成功");
		}else {
			Log.v("demo", "失败");
		}
	}
	@Override
	public void onConnectionSucceed() {
		// TODO Auto-generated method stub
		Log.v("demo", "连接成功");
		Toast.makeText(this, "连接成功", Toast.LENGTH_LONG);
	}
	@Override
	public void onMessageReceived(ServerMessage arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onNetworkChanged(NetworkInfo arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onReplyReceived(ReplyBody arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onRequestReceived(RequestBody arg0) {
		// TODO Auto-generated method stub
		
	}

}
