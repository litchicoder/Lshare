package com.example.lsharelib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.lsharelib.event.WeiXinEvent;
import com.example.lsharelib.listener.ShareListener;
import com.example.lsharelib.model.WXBaseInfo;
import com.example.lsharelib.model.WXImageInfo;
import com.example.lsharelib.model.WXMusicInfo;
import com.example.lsharelib.model.WXTextInfo;
import com.example.lsharelib.model.WXVideoInfo;
import com.example.lsharelib.model.WXWebpageInfo;
import com.example.lsharelib.util.BusUtil;
import com.example.lsharelib.util.Util;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMusicObject;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXVideoObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import static com.example.lsharelib.Constants.WX.ERROR_AUTH_DENIED;
import static com.example.lsharelib.Constants.WX.ERROR_UNKNOWN;
import static com.example.lsharelib.Constants.WX.ERROR_UNSUPPORT;
import static com.example.lsharelib.Constants.WX.ERROR_USER_CANCEL;

/**
 * Author: liujicheng
 * Package:com.example.lsharelib
 * Date:   2017/11/8
 * Desc: com.example.lsharelib
 */
public class WXShareAPI extends ShareAPI {

    ShareListener listener;

    public WXShareAPI() {
        BusUtil.register(this);
    }


    /***---------------------------------分享到会话---------------------------------------***/


    /**
     * 分享到会话（单文本）
     *
     * @param context  context
     * @param info     WXTextInfo
     * @param listener ShareListener
     */
    public void shareToSession(Context context, WXTextInfo info, ShareListener listener) {

        this.listener = listener;

        share(context, info, Constants.WX.TYPE_SHARE_TEXT, SendMessageToWX.Req.WXSceneSession);
    }


    /**
     * 分享到会话（单图片）
     *
     * @param context  context
     * @param info     WXTextInfo
     * @param listener ShareListener
     */
    public void shareToSession(Context context, final WXImageInfo info, final ShareListener listener) {

        this.listener = listener;

        share(context, info, Constants.WX.TYPE_SHARE_IMG, SendMessageToWX.Req.WXSceneSession);
    }


    /**
     * 分享到会话（音乐）
     *
     * @param context  context
     * @param info     info
     * @param listener listener
     */
    public void shareToSession(Context context, WXMusicInfo info, ShareListener listener) {
        this.listener = listener;
        share(context, info, Constants.WX.TYPE_SHARE_MUSIC, SendMessageToWX.Req.WXSceneSession);
    }

    /**
     * 分享到会话（视频）
     *
     * @param context  context
     * @param info     info
     * @param listener listener
     */
    public void shareToSession(Context context, WXVideoInfo info, ShareListener listener) {
        this.listener = listener;

        share(context, info, Constants.WX.TYPE_SHARE_VIDEO, SendMessageToWX.Req.WXSceneSession);

    }


    /**
     * 分享到会话（网页）
     *
     * @param context  context
     * @param info     info
     * @param listener listener
     */
    public void shareToSession(Context context, WXWebpageInfo info, ShareListener listener) {
        this.listener = listener;

        share(context, info, Constants.WX.TYPE_SHARE_WEBPAGE, SendMessageToWX.Req.WXSceneSession);

    }

    /***---------------------------------分享到会话---------------------------------------***/


    /***---------------------------------分享到朋友圈---------------------------------------***/

    /**
     * 分享到朋友圈（单图片）
     *
     * @param context  context
     * @param info     WXTextInfo
     * @param listener ShareListener
     */
    public void shareToMoments(Context context, WXTextInfo info, ShareListener listener) {
        this.listener = listener;

        share(context, info, Constants.WX.TYPE_SHARE_TEXT, SendMessageToWX.Req.WXSceneTimeline);
    }

    /**
     * 分享到朋友圈（单图片）
     *
     * @param context  context
     * @param info     WXTextInfo
     * @param listener ShareListener
     */
    public void shareToMoments(Context context, final WXImageInfo info, final ShareListener listener) {

        this.listener = listener;

        share(context, info, Constants.WX.TYPE_SHARE_IMG, SendMessageToWX.Req.WXSceneTimeline);
    }


    /**
     * 分享到朋友圈（音乐）
     *
     * @param context  context
     * @param info     info
     * @param listener listener
     */
    public void shareToMoments(Context context, WXMusicInfo info, ShareListener listener) {
        this.listener = listener;
        share(context, info, Constants.WX.TYPE_SHARE_MUSIC, SendMessageToWX.Req.WXSceneTimeline);
    }

    /**
     * 分享到朋友圈（视频）
     *
     * @param context  context
     * @param info     info
     * @param listener listener
     */
    public void shareToMoments(Context context, WXVideoInfo info, ShareListener listener) {
        this.listener = listener;

        share(context, info, Constants.WX.TYPE_SHARE_VIDEO, SendMessageToWX.Req.WXSceneTimeline);

    }


    /**
     * 分享到朋友圈（网页）
     *
     * @param context  context
     * @param info     info
     * @param listener listener
     */
    public void shareToMoments(Context context, WXWebpageInfo info, ShareListener listener) {
        this.listener = listener;

        share(context, info, Constants.WX.TYPE_SHARE_WEBPAGE, SendMessageToWX.Req.WXSceneTimeline);

    }


    /***---------------------------------分享到朋友圈---------------------------------------***/


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShareEvent(WeiXinEvent event) {
        switch (event.getCode()) {
            case BaseResp.ErrCode.ERR_OK:
                //发送成功
                if (null != listener) {
                    listener.onSuccess();
                }
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                //发送取消
                if (null != listener) {
                    listener.onFail(ERROR_USER_CANCEL, "发送被取消");
                }
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                //发送被拒绝
                if (null != listener) {
                    listener.onFail(ERROR_AUTH_DENIED, "发送被拒绝");
                }
                break;
            case BaseResp.ErrCode.ERR_UNSUPPORT:
                //不支持错误
                if (null != listener) {
                    listener.onFail(ERROR_UNSUPPORT, "不支持错误");
                }
                break;
            default:
                //未知
                if (null != listener) {
                    listener.onFail(ERROR_UNKNOWN, "未知");
                }
                break;
        }

        //销毁监听
        listener = null;
    }


    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    private void share(Context context, WXBaseInfo info, String transactionType, int scene) {

        IWXAPI api = WXAPIFactory.createWXAPI(context, Util.getAppMetaData(context, "SHARE_WX_APP_ID"));

        if (null == info) {
            if (null != listener) {
                listener.onFail(Constants.WX.ERROR_NULL_POINTER, "info 为空");
                this.listener = null;
                return;
            }
        }

        WXMediaMessage msg = new WXMediaMessage();

        switch (transactionType) {
            case Constants.WX.TYPE_SHARE_TEXT:
                handleTextMessage((WXTextInfo) info, msg);
                sendMessageReq(api, transactionType, scene, msg);
                break;
            case Constants.WX.TYPE_SHARE_IMG:
                handleImgMessage(api, transactionType, scene, (WXImageInfo) info, msg);
                break;
            case Constants.WX.TYPE_SHARE_MUSIC:
                handleMusicMessage((WXMusicInfo) info, api, transactionType, scene, msg);
                break;
            case Constants.WX.TYPE_SHARE_VIDEO:
                handleVideoMessage(api, transactionType, scene, (WXVideoInfo) info, msg);
                break;
            case Constants.WX.TYPE_SHARE_WEBPAGE:
                handleWebPageMessage(api, transactionType, scene, (WXWebpageInfo) info, msg);
                break;
            default:
                break;
        }


    }


    private void handleTextMessage(WXTextInfo info, WXMediaMessage msg) {

        if (null == info.getText()) {
            if (null != listener) {
                listener.onFail(Constants.WX.ERROR_NULL_POINTER, "text 为空");
                this.listener = null;
                return;
            }
        }

        WXTextObject textObj = new WXTextObject();
        textObj.text = info.getText();

        msg.mediaObject = textObj;
        msg.description = info.getText();

    }

    private void handleImgMessage(final IWXAPI api, final String transactionType, final int scene, WXImageInfo info, final WXMediaMessage msg) {

        if (null == info.getImgPath()) {
            if (null != listener) {
                listener.onFail(Constants.WX.ERROR_NULL_POINTER, "imgPath 为空");
                this.listener = null;
                return;
            }
        }

        try {
            Util.getImage(info.getImgPath(), new Util.ImageLoadCallBack() {
                @Override
                public void success(String path) {

                    File file = new File(path);
                    if (!file.exists()) {
                        if (null != listener) {
                            listener.onFail(Constants.WX.ERROR_NOT_FOUND, "图片未找到");
                            return;
                        }
                    }

                    WXImageObject imgObj = new WXImageObject();
                    imgObj.setImagePath(path);

                    msg.mediaObject = imgObj;

                    Bitmap bmp = BitmapFactory.decodeFile(path);
                    Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 150, 150, true); //创建新的图像大小
                    bmp.recycle();
                    msg.thumbData = Util.bmpToByteArray(thumbBmp, true);

                    sendMessageReq(api, transactionType, scene, msg);
                }

                @Override
                public void fail() {

                }
            });
        } catch (Exception e) {
            if (null != listener) {
                listener.onFail(Constants.WX.ERROR_UNKNOWN, e.getMessage());
                this.listener = null;
            }

        }

    }

    private void handleMusicMessage(final WXMusicInfo info, final IWXAPI api, final String transactionType, final int scene, final WXMediaMessage msg) {
        if (null == info.getMusicUrl()) {
            if (null != listener) {
                listener.onFail(Constants.WX.ERROR_NULL_POINTER, "musicUrl 为空");
                this.listener = null;
                return;
            }
        }

        if (null == info.getDisplayImg()) {
            if (null != listener) {
                listener.onFail(Constants.WX.ERROR_NULL_POINTER, "displayImg 为空");
                this.listener = null;
                return;
            }
        }

        try {
            Util.getImage(info.getDisplayImg(), new Util.ImageLoadCallBack() {
                @Override
                public void success(String path) {
                    File file = new File(path);
                    if (!file.exists()) {
                        if (null != listener) {
                            listener.onFail(Constants.WX.ERROR_NOT_FOUND, "图片未找到");
                            return;
                        }
                    }

                    WXMusicObject music = new WXMusicObject();
                    music.musicUrl = info.getMusicUrl();

                    msg.mediaObject = music;
                    msg.title = info.getTitle();
                    msg.description = info.getDescription();

                    Bitmap bmp = BitmapFactory.decodeFile(path);
                    Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 150, 150, true);
                    bmp.recycle();
                    msg.thumbData = Util.bmpToByteArray(thumbBmp, true);

                    sendMessageReq(api, transactionType, scene, msg);

                }

                @Override
                public void fail() {

                }
            });
        } catch (Exception e) {
            if (null != listener) {
                listener.onFail(Constants.WX.ERROR_UNKNOWN, e.getMessage());
                this.listener = null;
            }
        }


    }

    private void handleVideoMessage(final IWXAPI api, final String transactionType, final int scene, final WXVideoInfo info, final WXMediaMessage msg) {
        if (null == info.getVideoUrl()) {
            if (null != listener) {
                listener.onFail(Constants.WX.ERROR_NULL_POINTER, "videoUrl 为空");
                this.listener = null;
                return;
            }
        }

        if (null == info.getDisplayImg()) {
            if (null != listener) {
                listener.onFail(Constants.WX.ERROR_NULL_POINTER, "displayImg 为空");
                this.listener = null;
                return;
            }
        }

        try {
            Util.getImage(info.getDisplayImg(), new Util.ImageLoadCallBack() {
                @Override
                public void success(String path) {
                    File file = new File(path);
                    if (!file.exists()) {
                        if (null != listener) {
                            listener.onFail(Constants.WX.ERROR_NOT_FOUND, "图片未找到");
                            return;
                        }
                    }

                    WXVideoObject video = new WXVideoObject();
                    video.videoUrl = info.getVideoUrl();

                    msg.title = info.getTitle();
                    msg.description = info.getDescription();

                    Bitmap bmp = BitmapFactory.decodeFile(path);
                    Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 150, 150, true);
                    bmp.recycle();
                    msg.thumbData = Util.bmpToByteArray(thumbBmp, true);

                    sendMessageReq(api, transactionType, scene, msg);

                }

                @Override
                public void fail() {

                }
            });
        } catch (Exception e) {
            if (null != listener) {
                listener.onFail(Constants.WX.ERROR_UNKNOWN, e.getMessage());
                this.listener = null;
            }
        }

    }

    private void handleWebPageMessage(final IWXAPI api, final String transactionType, final int scene, final WXWebpageInfo info, final WXMediaMessage msg) {
        if (null == info.getWebpageUrl()) {
            if (null != listener) {
                listener.onFail(Constants.WX.ERROR_NULL_POINTER, "videoUrl 为空");
                this.listener = null;
                return;
            }
        }

        if (null == info.getDisplayImg()) {
            if (null != listener) {
                listener.onFail(Constants.WX.ERROR_NULL_POINTER, "displayImg 为空");
                this.listener = null;
                return;
            }
        }

        try {
            Util.getImage(info.getDisplayImg(), new Util.ImageLoadCallBack() {
                @Override
                public void success(String path) {

                    File file = new File(path);
                    if (!file.exists()) {
                        if (null != listener) {
                            listener.onFail(Constants.WX.ERROR_NOT_FOUND, "图片未找到");
                            return;
                        }
                    }

                    WXWebpageObject webpage = new WXWebpageObject();
                    webpage.webpageUrl = info.getWebpageUrl();

                    WXMediaMessage msg = new WXMediaMessage();
                    msg.title = info.getTitle();
                    msg.description = info.getDescription();

                    Bitmap bmp = BitmapFactory.decodeFile(path);
                    Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 150, 150, true);
                    bmp.recycle();
                    msg.thumbData = Util.bmpToByteArray(thumbBmp, true);

                    sendMessageReq(api, transactionType, scene, msg);

                }

                @Override
                public void fail() {

                }
            });
        } catch (Exception e) {
            if (null != listener) {
                listener.onFail(Constants.WX.ERROR_UNKNOWN, e.getMessage());
                this.listener = null;
            }
        }


    }

    private void sendMessageReq(IWXAPI api, String transactionType, int scene, WXMediaMessage msg) {
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction(transactionType);
        req.message = msg;
        req.scene = scene;

        //发起分享
        if (null != listener) {
            listener.onStart();
        }
        api.sendReq(req);
    }
}

