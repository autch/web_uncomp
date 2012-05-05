package net.autch.android.web_uncomp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class RedirectActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent it = getIntent();
		Uri from_uri = it.getData();
		
		if(from_uri != null) {
			startActivity(createRedirectIntent(from_uri));
		}
	}

	private Intent createRedirectIntent(Uri from_uri) {
		Uri.Builder builder = from_uri.buildUpon();
		builder.appendQueryParameter("mpswc", "cHI9b3Jn");
		Intent it = new Intent(Intent.ACTION_VIEW, builder.build());
		it.addCategory(Intent.CATEGORY_DEFAULT);
		return it;		
	}
}
