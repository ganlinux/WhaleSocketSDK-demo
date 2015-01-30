package com.gl.emms.whale.demo.receiver;

import android.net.NetworkInfo;
import android.util.Log;

import com.gl.emms.client.android.EMMSEnventListenerReceiver;
import com.gl.emms.client.android.EMMSListenerManager;
import com.gl.emms.client.android.EMMSPushManager;
import com.gl.emms.nio.mutual.ServerMessage;
import com.gl.emms.nio.mutual.ReplyBody;
import com.gl.emms.nio.mutual.RequestBody;


/**
 *  消息入口，所有消息都会经过这里
 * @author 3979434
 *
 */
public final class CustomMessageReceiver extends EMMSEnventListenerReceiver {
	//当收到消息时，会执行onMessageReceived，这里是消息第一入口
	@Override
	public void onMessageReceived(com.gl.emms.nio.mutual.ServerMessage message) {

		Log.v("demo", "CustomMessageReceiver:message");
		for (int index = 0 ;index<EMMSListenerManager.getCIMListeners().size();index++) {

			Log.i(this.getClass().getSimpleName(), "########################"+ (EMMSListenerManager.getCIMListeners().get(index).getClass().getName() + ".onMessageReceived################"));

			EMMSListenerManager.getCIMListeners().get(index).onMessageReceived(message);
		}
	}


	//当手机网络连接状态变化时，会执行onNetworkChanged 
	@Override
	public void onNetworkChanged(NetworkInfo info) {

		for (int index = 0 ;index<EMMSListenerManager.getCIMListeners().size();index++) {
			EMMSListenerManager.getCIMListeners().get(index).onNetworkChanged(info);
		}
	}

	//当收到sendbody的响应时，会执行onReplyReceived 
	@Override
	public void onReplyReceived(ReplyBody body) {
		Log.v("demo", "CustomMessageReceiver:reply");
		for (int index = 0 ;index<EMMSListenerManager.getCIMListeners().size();index++) {
			EMMSListenerManager.getCIMListeners().get(index).onReplyReceived(body);
		}
	}
	@Override
	public void onRequestReceived(RequestBody requestBody) {
		// TODO Auto-generated method stub
		Log.v("demo", "CustomMessageReceiver:request");
		for (int index = 0 ;index<EMMSListenerManager.getCIMListeners().size();index++) {
			EMMSListenerManager.getCIMListeners().get(index).onRequestReceived(requestBody);
		}
	}

	public void onConnectionSucceed() {
		Log.v("demo", "CustomMessageReceiver:onConnectionSucceed");
		for (int index = 0 ;index<EMMSListenerManager.getCIMListeners().size();index++) {
			EMMSListenerManager.getCIMListeners().get(index).onConnectionSucceed();

		}

	}

	@Override
	public void onConnectionStatus(boolean arg0) {
		for (int index = 0 ;index<EMMSListenerManager.getCIMListeners().size();index++) {
			EMMSListenerManager.getCIMListeners().get(index).onConnectionStatus(arg0);
		}
	}

}
