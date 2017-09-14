package com.lx.shell.ui;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.apkfuns.logutils.LogUtils;
import com.joker.annotation.PermissionsCustomRationale;
import com.joker.annotation.PermissionsDenied;
import com.joker.annotation.PermissionsGranted;
import com.joker.annotation.PermissionsNonRationale;
import com.joker.api.Permissions4M;
import com.lx.rentcheck.R;
import com.lx.shell.adapter.cell.TextCell;
import com.lx.shell.base.BaseActivity;
import com.lx.shell.core.Constance;
import com.lx.shell.mvp.model.bean.ResultList_right;
import com.lx.shell.mvp.presenter.IdeaPresenterImpl;
import com.lx.shell.mvp.view.IdeaView;
import com.lx.shell.utils.FileUtils;
import com.lx.shell.utils.HttpCallBack;
import com.lx.shell.utils.SPUtil;
import com.lx.shell.utils.ToastUtil;
import com.lx.shell.utils.adapter.base.Cell;
import com.lx.shell.utils.adapter.base.RVSimpleAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity<IdeaView,IdeaPresenterImpl> implements IdeaView,SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayoutManager linearLayoutManager;
    private List<ResultList_right> resultList_rightList = new ArrayList<>();
    private ImageView ivScan;

    private Button btGet;

    private RVSimpleAdapter mBaseAdapter;
  //  private EazyAdapter eazyAdapter;
        Bitmap bmp = null;

    @Override
    protected IdeaPresenterImpl creatPresenter() {
        return new IdeaPresenterImpl();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        ivScan = (ImageView) findViewById(R.id.iv_scan);
        recyclerView = (RecyclerView) findViewById(R.id.recyleview);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayout);
        btGet = (Button) findViewById(R.id.bt);
        btGet.setOnClickListener(this);

        findViewById(R.id.bt4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shenqingquanxian();
                //startDownload();
            }
        });
        findViewById(R.id.bt3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String str = "package com.inspur.rentcheck.ui;\n" +
                                "}\n";
                        bmp = getShareingBitmap(12, BitmapFactory.decodeResource(getResources(),R.drawable.img_huoer),"测试",str);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ivScan.setImageBitmap(bmp);

                                MediaStore.Images.Media.insertImage(getContentResolver(), bmp, "title", "description");


                            }
                        });
                    }
                }).start();

            }
        });
    }

    @Override
    protected void initData() {
        linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        mBaseAdapter = new RVSimpleAdapter();
        recyclerView.setAdapter(mBaseAdapter);

 //      eazyAdapter = new EazyAdapter(MainActivity.this,resultList_rightList);
 //      recyclerView.setAdapter(eazyAdapter);

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);


        //DaoUtils.getCustomerInstance().insertObject();
        //DaoUtils.getCustomerInstance().deleteAll()
        //DaoUtils.getCustomerInstance().updateObject();
        //DaoUtils.getCustomerInstance().QueryById()
        //DaoUtils.getCustomerInstance().CloseDataBase();//onDestory中调用

    }

    @Override
    protected void cancleDialog() {
        if(mPresenter != null){
            mPresenter.cancleLoad();
        }
    }

    @Override
    public void onClick(View v) {
        showProgress("正在请求...");
        Map<String,String> params = new HashMap<>();
        params.put(Constance.List_UID_MVP, new SPUtil(MainActivity.this,SPUtil.USER).getString(SPUtil.USER_UID,""));
        params.put(Constance.List_PAGE_MVP,"1");
        params.put(Constance.List_PAGESIZE_MVP,"15");
        mPresenter.loadIdeaList(params);
    }

    @Override
    public void showIdeaList(List<?> ideaList) {
        LogUtils.d("有数据吗"+ideaList.size());
        mBaseAdapter.addAll(getCells((List<ResultList_right>) ideaList));
//        resultList_rightList.addAll((List<ResultList_right>) ideaList);
//        eazyAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);

        dismissDialog();

    }

    @Override
    public void showIdeaMoreList(List<?> ideaList) {
        swipeRefreshLayout.setRefreshing(false);
        dismissDialog();
    }

    @Override
    public void showIdeaMoreErro(String erroInfo) {
        ToastUtil.showToast(MainActivity.this,erroInfo,ToastUtil.TOAST_TYPE_WARNING);
        swipeRefreshLayout.setRefreshing(false);
        dismissDialog();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onRefresh() {
        Map<String,String> params = new HashMap<>();
        params.put(Constance.List_UID_MVP, new SPUtil(MainActivity.this,SPUtil.USER).getString(SPUtil.USER_UID,""));
        params.put(Constance.List_PAGE_MVP,"1");
        params.put(Constance.List_PAGESIZE_MVP,"15");
        mPresenter.loadIdeaList(params);
    }

    protected List<Cell> getCells(List<ResultList_right> entries){
        //根据实体生成Cell
        List<Cell> cells = new ArrayList<>();
        for (int i = 0;i < entries.size();i++){
            ResultList_right entry = entries.get(i);
            entry.type = ResultList_right.TYPE_TEXT;
            cells.add(new TextCell(entry));
        }

        LogUtils.d("装换为cells"+cells.size());
        return cells;
    }

    private void startDownload() {
        String downloadUrl = "http://10.18.11.152:8080/InventoryManager/pdaMainTask!getResourceDb.interface";
        String json = "{\"cityId\": \""+1111+"\"}";
        Call<ResponseBody> responseBodyCall = userClient.downloadFile(downloadUrl,json);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                if(response.raw().code() == 200){
                    LogUtils.d("下载完成");
                }else{
                    LogUtils.d("下载..."+response.raw().code());
                }


                LogUtils.d("vivi",response.message()+"  length  "+response.body().contentLength()+"  type "+response.body().contentType());
                //建立一个文件
                final File file          = FileUtils.createFile(MainActivity.this);

                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                       // 保存到本地
                        FileUtils.writeFile2Disk(response, file, new HttpCallBack() {
                            @Override
                            public void onLoading(final long current, final long total) {
                                /** * 更新进度条 */
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
//                                        LogUtils.d(current+" to "+total);
//                                        LogUtils.d("runOnUiThread "+ currentThread().getName());
//                                        mTv.setText(current+"");
//                                        mPb.setMax((int) total);
//                                        mPb.setProgress((int) current);

                                 //       LogUtils.d("current==="+current);
                                        LogUtils.d("下载文件pro==="+total+"/"+current);
                                    }
                                });
                            }
                        });
                    } }.start();


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                LogUtils.d("下载异常"+t.toString());
            }
        });
    }






    // 拍摄所得的图片为imageBitmap
    private Bitmap getShareingBitmap(int textSize,Bitmap imageBitmap,String title,String content) {
        if(imageBitmap == null){
            Log.d("qqqq","null");
            return null;
        }
      //  Bitmap.Config config1 = imageBitmap.getConfig() != null ? imageBitmap.getConfig() : Bitmap.Config.ARGB_8888;
        Bitmap.Config config = imageBitmap.getConfig() != null ? imageBitmap.getConfig() : Bitmap.Config.ARGB_8888;
        int sourceBitmapHeight = imageBitmap.getHeight();
        int sourceBitmapWidth = imageBitmap.getWidth();
        Paint paint = new Paint();
        paint.setColor(Color.BLACK); // 画笔颜色
        TextPaint textpaint = new TextPaint(paint);
        textpaint.setTextSize(textSize); // 文字大小
        textpaint.setAntiAlias(true); // 抗锯齿
        StaticLayout title_layout = new StaticLayout(title, textpaint,
                sourceBitmapWidth, Layout.Alignment.ALIGN_CENTER, 1f, 1f, true);
        StaticLayout desc_layout = new StaticLayout(content, textpaint,
                sourceBitmapWidth, Layout.Alignment.ALIGN_NORMAL, 1f, 1f, true);
        StaticLayout phone_layout = new StaticLayout("联系电话：111", textpaint,
                sourceBitmapWidth, Layout.Alignment.ALIGN_NORMAL, 1f, 1f, true);
        Bitmap share_bitmap = Bitmap.createBitmap(sourceBitmapWidth, sourceBitmapHeight +
                        title_layout.getHeight() + desc_layout.getHeight() + phone_layout.getHeight(),
                config);
        Canvas canvas = new Canvas(share_bitmap);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(imageBitmap, 0, 0, paint); // 绘制图片
        canvas.translate(0, sourceBitmapHeight);
        title_layout.draw(canvas);

        canvas.translate(0, title_layout.getHeight());
        phone_layout.draw(canvas);

        canvas.translate(0, phone_layout.getHeight());
        desc_layout.draw(canvas);
        return share_bitmap;
    }

private final int AUDIO_CODE = 16;
    private void shenqingquanxian(){
        Permissions4M.get(MainActivity.this)
                .requestUnderM(true)
                //权限申请，可传多个
                .requestPermissions(Manifest.permission.BODY_SENSORS, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_CALENDAR)
                //请求吗，可传多个
                .requestCodes(AUDIO_CODE)
                .request();

        /*Permissions4M.get(MainActivity.this)
                // 是否强制弹出权限申请对话框，建议设置为 true，默认为 true
                // .requestForce(true)
                // 是否支持 5.0 权限申请，默认为 false
                 .requestUnderM(true)
                // 权限，单权限申请仅只能填入一个
                .requestPermissions(Manifest.permission.CAMERA)
                // 权限码
                .requestCodes(AUDIO_CODE)
                // 如果需要使用 @PermissionNonRationale 注解的话，建议添加如下一行
                // 返回的 intent 是跳转至**系统设置页面**
                // .requestPageType(Permissions4M.PageType.MANAGER_PAGE)
                // 返回的 intent 是跳转至**手机管家页面**
                // .requestPageType(Permissions4M.PageType.ANDROID_SETTING_PAGE)
                .request();*/

    }

    /**
     * 授权成功
     * @param code
     */
    @PermissionsGranted({AUDIO_CODE})
    public void granted(int code) {
        switch (code) {
            case AUDIO_CODE:
                ToastUtil.showToast(MainActivity.this,"授权成功",ToastUtil.TOAST_TYPE_SUCCESS);
                break;

            default:
                break;
        }
    }


    /**
     * 授权失败
     * @param code
     */
    @PermissionsDenied({AUDIO_CODE})
    public void denied(int code) {
        switch (code) {
            case AUDIO_CODE:
                ToastUtil.showToast(MainActivity.this,"授权失败",ToastUtil.TOAST_TYPE_ERRO);
                break;

            default:
                break;
        }
    }

    /**
     * 二次授权时回调，用于解释为何需要此权限
     */
    @PermissionsCustomRationale({AUDIO_CODE})
    public void cusRationale(int code) {
        switch (code) {
            case AUDIO_CODE:
                new AlertDialog.Builder(this)
                        .setMessage("短信权限申请：\n我们需要您开启短信权限(in activity with annotation)")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Permissions4M.get(MainActivity.this)
                                        // 注意添加 .requestOnRationale()
                                        .requestOnRationale()
                                        .requestPermissions(Manifest.permission.CAMERA)
                                        .requestCodes(AUDIO_CODE)
                                        .request();
                            }
                        })
                        .show();
                break;
            default:
                break;
        }
    }

        /**
         * 拒绝权限且不再提示
         * permission 将会返回一个跳转至 手机管家界面或者应用设置界面的 intent
         */
        @PermissionsNonRationale({AUDIO_CODE})
        public void nonRationale(int code,final Intent intent) {
            switch (code) {
                case AUDIO_CODE:
                    new AlertDialog.Builder(MainActivity.this)
                            .setMessage("读取录音权限申请：\n我们需要您开启读取录音权限")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .show();
                    break;

                default:
                    break;
            }
        }
        /**
         *
         * @param requestCode
         * @param permissions
         * @param grantResults
         */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
            grantResults) {
        Permissions4M.onRequestPermissionsResult(MainActivity.this, requestCode, grantResults);
        ToastUtil.showToast(MainActivity.this,requestCode+"==requestCode授权回调",ToastUtil.TOAST_TYPE_SUCCESS);

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
