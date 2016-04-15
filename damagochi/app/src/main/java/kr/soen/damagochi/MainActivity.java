package kr.soen.damagochi;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    WebView mWeb;
    final static int ACT_EDIT = 0;


    @Override //액티비티가 처음 만들어졌을 때 호출
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.rice).setOnClickListener(btnClickListener);
        findViewById(R.id.play).setOnClickListener(btnClickListener);
        findViewById(R.id.stroll).setOnClickListener(btnClickListener);
        findViewById(R.id.gift).setOnClickListener(btnClickListener);
    }

    Button.OnClickListener btnClickListener = new View.OnClickListener() {
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.rice: // feed pet
                    Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.play: // play with pet inside
                    LinearLayout linear_rice = (LinearLayout)View.inflate(MainActivity.this,R.layout.riceview,null);
                    Toast t1 = new Toast(MainActivity.this);
                    t1.setView(linear_rice);
                    t1.show();
                    break;
                case  R.id.stroll: // play with pet outside    임시로 다른 액티비티 넘어가는 것
                    Intent intent = new Intent(MainActivity.this,SubActivity.class);
                    startActivityForResult(intent,ACT_EDIT);
                    break;

                case R.id.gift: //connect CrowdFunding Site

                    new android.app.AlertDialog.Builder(MainActivity.this)
                            .setTitle("선물하기")
                            .setMessage("크라우드 펀딩 사이트로 연결하시겠습니까?")
                            .setPositiveButton("확인",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    setContentView(R.layout.funding_site);

                                    mWeb = (WebView)findViewById(R.id.web);
                                    mWeb.setWebViewClient(new MyWebClient());
                                    WebSettings set = mWeb.getSettings();
                                    set.setJavaScriptEnabled(true);
                                    set.setBuiltInZoomControls(true);
                                    mWeb.loadUrl("http://danaeri.dothome.co.kr");
                                }
                            })
                            .setNegativeButton("취소", null)
                            .show();

            }

        }
    };

    class MyWebClient extends WebViewClient {
        /*
	 * 웹뷰 내 링크 터치 시 새로운 창이 뜨지 않고
	 * 해당 웹뷰 안에서 새로운 페이지가 로딩되도록 함
	 */
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
           //mWebsetDefaultZoom(WebSettings.ZoomDensity.FAR);
            return true;
        }
    }


//if 'goBack' in webview, don't finish and goBack.
    @Override

    public void onBackPressed() {
        if (mWeb.canGoBack()) {
            mWeb.goBack();
         }
        else {
            //System.exit(0);

        }
        //super.onBackPressed(); // 원래 뒤로가기 기능을 실행, 즉 종료
    }
    /*public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if ((keyCode == android.view.KeyEvent.KEYCODE_BACK) && mWeb.canGoBack()) { //canGoBack()은 지금 페이지에서 더이상 뒤로가기가 가능한지를 판단
            mWeb.goBack();
            return true;
        }
        else {
            setContentView(R.layout.activity_main);
        }
        return false;
    }*/



}



