package net.autch.android.web_uncomp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class RedirectActivity extends Activity {
	private static final int REQUEST_CHOOSER = 0x100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent it = getIntent();
		Intent it_to_send = new Intent(it);
		String url = it.getDataString();
		String action = it.getAction();
		
		if(action.equals(Intent.ACTION_SEND)) {
			url = it.getExtras().getString(Intent.EXTRA_STREAM);
			if(url == null) {
				url = it.getExtras().getString(Intent.EXTRA_TEXT);
				it_to_send.putExtra(Intent.EXTRA_TEXT, appendUncompressKey(url));
			} else {
				it_to_send.putExtra(Intent.EXTRA_STREAM, appendUncompressKey(url));
			}
		}
		if(action.equals(Intent.ACTION_VIEW)) {
			url = it.getDataString();
			it_to_send.setData(Uri.parse(appendUncompressKey(url)));
		}
		
		Intent chooser = Intent.createChooser(it_to_send, "ブラウザの選択");
		startActivityForResult(chooser, REQUEST_CHOOSER);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch(requestCode) {
		case REQUEST_CHOOSER:
			if(resultCode == RESULT_OK) {
				Intent intent = (Intent) data.getExtras().get(Intent.EXTRA_INTENT);
				if(intent != null) {
					startActivity(intent);
				}
			}
			break;
		default:
			break;
		}

		finish();
	}

	private String appendUncompressKey(String url) {
		if(url.indexOf("?") != -1) {
			url += "&mpswc=cHI9b3Jn";
		} else {
			url += "?mpswc=cHI9b3Jn";
		}
		return url;
	}
}
