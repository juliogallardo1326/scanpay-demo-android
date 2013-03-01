package it.scanpay.scanpay_demo_android;

import scanpay.it.ScanPayActivity;
import scanpay.it.config.ScanPay;
import scanpay.it.model.SPCreditCard;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class ScanPayTestActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scan_pay_test);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_scan_pay_test, menu);
		return true;
	}

	public void startScan(View v)
	{
		Intent scanActivity = new Intent(this, ScanPayActivity.class);
		scanActivity.putExtra(ScanPay.EXTRA_TOKEN, "Enter_your_token_here");
		startActivityForResult(scanActivity, ScanPay.RESULT_SCANPAY_ACTIVITY);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
	  super.onActivityResult(requestCode, resultCode, data);
	  if (requestCode == ScanPay.RESULT_SCANPAY_ACTIVITY && resultCode == RESULT_OK)
	  {
	    SPCreditCard creditCard = (SPCreditCard) data.getParcelableExtra(ScanPay.EXTRA_CREDITCARD);
	    Toast.makeText(this, creditCard.getNumber() + " " + creditCard.month + "/" + creditCard.year + " " + creditCard.pictogram, Toast.LENGTH_LONG).show();
	  }
	}
}
