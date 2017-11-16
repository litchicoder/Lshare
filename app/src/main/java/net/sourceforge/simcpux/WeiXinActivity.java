package net.sourceforge.simcpux;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.lsharelib.ShareFactory;
import com.example.lsharelib.WXShareAPI;
import com.example.lsharelib.listener.ShareListener;
import com.example.lsharelib.model.WXImageInfo;
import com.example.lsharelib.model.WXTextInfo;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;

public class WeiXinActivity extends AppCompatActivity implements View.OnClickListener {

    private int mTargetScene = SendMessageToWX.Req.WXSceneSession;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wei_xin);
        initView();
    }

    private void initView() {
        findViewById(R.id.button_wx_text).setOnClickListener(this);
        findViewById(R.id.button_wx_image).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        WXShareAPI wXShareAPI = ShareFactory.creatShareAPI(WXShareAPI.class);
        switch (view.getId()) {
            case R.id.button_wx_text:
                WXTextInfo wxTextInfo = new WXTextInfo();
                wxTextInfo.setText("微信分享文本");
                wXShareAPI.shareToSession(this, wxTextInfo, new ShareListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess() {
                        Toast.makeText(WeiXinActivity.this, "share success", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFail(int code, String msg) {
                        Toast.makeText(WeiXinActivity.this, msg, Toast.LENGTH_LONG).show();

                    }


                });
                break;
            case R.id.button_wx_image:
                WXImageInfo wxImageInfo = new WXImageInfo();
                wxImageInfo.setImgPath("http://img0.imgtn.bdimg.com/it/u=3233931600,2370880594&fm=200&gp=0.jpg");
                wXShareAPI.shareToSession(WeiXinActivity.this, wxImageInfo, new ShareListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onFail(int code, String msg) {

                    }
                });
                break;
            default:
                break;
        }
    }

    public void onRadioButtonClick(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radioButton:
                if (checked) {
                    mTargetScene = SendMessageToWX.Req.WXSceneSession;
                }
                break;
            case R.id.radioButton2:
                if (checked) {
                    mTargetScene = SendMessageToWX.Req.WXSceneTimeline;
                }
                break;
            default:
                break;
        }
    }
}
