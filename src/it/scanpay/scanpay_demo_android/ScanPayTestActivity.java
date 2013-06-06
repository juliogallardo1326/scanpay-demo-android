package it.scanpay.scanpay_demo_android;

import scanpay.it.SPCreditCard;
import scanpay.it.ScanPay;
import scanpay.it.ScanPayActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class ScanPayTestActivity extends Activity
{
    static final int YOUR_RESULT_DEFINE = 4;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scan_pay_test);
	}

	public void startScan(View v)
	{
		Intent scanActivity = new Intent(this, ScanPayActivity.class);
		scanActivity.putExtra(ScanPay.EXTRA_TOKEN, "Enter_your_token_here");

        //Put true if you want use your own manual entry UI
        scanActivity.putExtra(ScanPay.EXTRA_USE_CUSTOM_CONFIRMATION_VIEW, false);
        startActivityForResult(scanActivity, YOUR_RESULT_DEFINE);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
	  super.onActivityResult(requestCode, resultCode, data);
      // The result code is ScanPay.RESULT_SCAN_SUCCESS when we succeed to scan
	  if (requestCode == YOUR_RESULT_DEFINE && resultCode == ScanPay.RESULT_SCAN_SUCCESS)
	  {
	    SPCreditCard creditCard = (SPCreditCard) data.getParcelableExtra(ScanPay.EXTRA_CREDITCARD);
	    Toast.makeText(this, creditCard.number + " " + creditCard.month + "/" + creditCard.year + " " + creditCard.cvv.length(), Toast.LENGTH_LONG).show();
	  }
      // The result code is ScanPay.RESULT_SCAN_CANCEL when the user back
      else if (requestCode == YOUR_RESULT_DEFINE && resultCode == ScanPay.RESULT_SCAN_CANCEL)
      {
          Toast.makeText(this, "Scan cancel", Toast.LENGTH_LONG).show();
      }
	}
}
