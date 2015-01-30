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
 *  ��Ϣ��ڣ�������Ϣ���ᾭ������
 * @author 3979434
 *
 */
public final class CustomMessageReceiver extends EMMSEnventListenerReceiver {
	//���յ���Ϣʱ����ִ��onMessageReceived����������Ϣ��һ���
	@Override
	public void onMessageReceived(com.gl.emms.nio.mutual.ServerMessage message) {

		Log.v("demo", "CustomMessageReceiver:message");
		for (int index = 0 ;index<EMMSListenerManager.getCIMListeners().size();index++) {

			Log.i(this.getClass().getSimpleName(), "########################"+ (EMMSListenerManager.getCIMListeners().get(index).getClass().getName() + ".onMessageReceived################"));

			EMMSListenerManager.getCIMListeners().get(index).onMessageReceived(message);
		}
	}


	//���ֻ���������״̬�仯ʱ����ִ��onNetworkChanged 
	@Override
	public void onNetworkChanged(NetworkInfo info) {

		for (int index = 0 ;index<EMMSListenerManager.getCIMListeners().size();index++) {
			EMMSListenerManager.getCIMListeners().get(index).onNetworkChanged(info);
		}
	}

	//���յ�sendbody����Ӧʱ����ִ��onReplyReceived 
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
