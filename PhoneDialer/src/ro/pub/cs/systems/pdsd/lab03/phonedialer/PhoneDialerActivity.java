package ro.pub.cs.systems.pdsd.lab03.phonedialer;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender.OnFinished;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

public class PhoneDialerActivity extends Activity {

	static final String[] numbers = new String[] { "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "0" };

	private final OnClickListenerImpl onClickListenerImpl = new OnClickListenerImpl();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phone_dialer);

		final EditText inputPhoneNumber = (EditText) findViewById(R.id.input_phone_number);

		ArrayList<? super View> phoneButtons = new ArrayList<View>();
		
		ImageButton callButton = (ImageButton) findViewById(R.id.call_button);
		callButton.setOnClickListener(onClickListenerImpl);
		phoneButtons.add(callButton);
		
		ImageButton hangupButton = (ImageButton) findViewById(R.id.hangup_button);
		hangupButton.setOnClickListener(onClickListenerImpl);

		GridView gridView = (GridView) findViewById(R.id.grid_view_numbers);

		// final Button submitButton = new Button(this);
		// submitButton.setId(i);
		// String buttonText = "number" + i;
		//
		// submitButton.setText(buttonText);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, numbers);

		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
					long arg3) {
				// TODO Auto-generated method stub

				CharSequence existingText = ((TextView) view).getText();
				CharSequence newText = inputPhoneNumber.getText().append(existingText);

				inputPhoneNumber.setText(newText);

			}
		});

	}
	
	private class OnClickListenerImpl implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			EditText inputPhoneNumber = (EditText) findViewById(R.id.input_phone_number);
			
			if (v instanceof ImageButton) {
				if (v.getId() == R.id.call_button) {
					Intent intent = new Intent(Intent.ACTION_CALL);
					intent.setData(Uri.parse("tel:" + inputPhoneNumber));
					startActivity(intent);
				} else if (v.getId() == R.id.hangup_button) {
					finish();
				}
			}
			
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.phone_dialer, menu);
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
}
