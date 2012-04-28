package org.dsanderson.mn_baseball_radio;

import org.dsanderson.mn_baseball_radio.AboutActivity;
import org.dsanderson.mn_baseball_radio.PreferenceActivity;
import org.dsanderson.mn_baseball_radio.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class baseBallRadioActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.preferences:
			openPreferencesMenu();
			return true;
		case R.id.about:
			openAbout();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	// / Launch Preference activity
	private void openPreferencesMenu() {
		Intent i = new Intent(this, PreferenceActivity.class);
		startActivity(i);
	}

	// / Launch about menu activity
	private void openAbout() {
		Intent i = new Intent(this, AboutActivity.class);
		startActivity(i);
	}
}