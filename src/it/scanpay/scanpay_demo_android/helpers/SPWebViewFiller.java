package scanpay.it.utils;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.HashMap;
import java.util.Map;

public class SPWebViewFiller extends WebViewClient
{
  private final Map<String, String> _values;
  private final WebView _web;

  public SPWebViewFiller(WebView web)
  {
    _web = web;

    WebSettings setting = web.getSettings();
    setting.setJavaScriptEnabled(true);

    _values = new HashMap<String, String>();
  }

  public void addField(String fieldID, String value)
  {
    _values.put(fieldID, value);
    _web.setWebViewClient(this);
  }

  public void fillField(String fieldId, String value)
  {
    _web.loadUrl("javascript:document.getElementById('" + fieldId + "').value=\"" + value + "\"");
  }

  public void onPageFinished(WebView view, String url)
  {
    for (String key : _values.keySet())
    {
      _web.loadUrl("javascript:document.getElementById('" + key + "').value=\"" + _values.get(key) + "\"");
    }
  }
}
