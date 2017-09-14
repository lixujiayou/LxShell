package com.lx.shell.base;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lx.rentcheck.R;
import com.lx.shell.adapter.GridImageAdapter;
import com.lx.shell.utils.FullyGridLayoutManager;
import com.lx.shell.utils.SPUtil;
import com.lx.shell.utils.ToastUtil;
import com.lx.shell.utils.ToolUtil;
import com.luck.picture.lib.model.FunctionConfig;
import com.luck.picture.lib.model.PictureConfig;
import com.yalantis.ucrop.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lixu on 2017/8/17.
 */

public abstract class BasePhotoActivity<V,T extends BasePresenter<V>> extends BaseActivity{

    private RecyclerView ryPhoto0;
    private RecyclerView ryPhoto1;
    private RecyclerView ryPhoto2;
    private int maxSelectNum = 2;// 图片最大可选数量

    private GridImageAdapter adapter0;
    private GridImageAdapter adapter1;
    private GridImageAdapter adapter2;


    private List<LocalMedia> selectMedia0 = new ArrayList<>();
    private List<LocalMedia> selectMedia1 = new ArrayList<>();
    private List<LocalMedia> selectMedia2 = new ArrayList<>();

    private TextView tvPhoto0,tvPhoto1,tvPhoto2;
    private LinearLayout llPhoto0,llPhoto1,llPhoto2;


    private Context mContext;
    private int mType = 3;//当前类型个数

    @Override
    protected BasePresenter creatPresenter() {
        return null;
    }

    //子类必须重写该方法
    @Override
    protected int getLayout() {
        return getLayoutId();
    }

    @Override
    protected void initView() {
        mContext = this;

        tvPhoto0 = (TextView) findViewById(R.id.tv_photo_1);
        tvPhoto1 = (TextView) findViewById(R.id.tv_photo_2);
        tvPhoto2 = (TextView) findViewById(R.id.tv_photo_3);

        llPhoto0 = (LinearLayout) findViewById(R.id.ll_photo_1);
        llPhoto1 = (LinearLayout) findViewById(R.id.ll_photo_2);
        llPhoto2 = (LinearLayout) findViewById(R.id.ll_photo_3);

        ryPhoto0 = (RecyclerView) findViewById(R.id.ry_photo_1);
        ryPhoto1 = (RecyclerView) findViewById(R.id.ry_photo_2);
        ryPhoto2 = (RecyclerView) findViewById(R.id.ry_photo_3);

        FullyGridLayoutManager manager0 = new FullyGridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        FullyGridLayoutManager manager1 = new FullyGridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        FullyGridLayoutManager manager2 = new FullyGridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        ryPhoto0.setLayoutManager(manager0);
        ryPhoto1.setLayoutManager(manager1);
        ryPhoto2.setLayoutManager(manager2);

        adapter0 = new GridImageAdapter(this, onAddPicClickListener0);
        adapter0.setSelectMax(maxSelectNum);
        ryPhoto0.setAdapter(adapter0);

        adapter1 = new GridImageAdapter(this, onAddPicClickListener1);
        adapter1.setSelectMax(maxSelectNum);
        ryPhoto1.setAdapter(adapter1);

        adapter2 = new GridImageAdapter(this, onAddPicClickListener2);
        adapter2.setSelectMax(maxSelectNum);
        ryPhoto2.setAdapter(adapter2);



        adapter0.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                // 这里可预览图片
                PictureConfig.getPictureConfig().externalPicturePreview(mContext, position, selectMedia0);
            }
        });
        adapter1.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                // 这里可预览图片
                PictureConfig.getPictureConfig().externalPicturePreview(mContext, position, selectMedia1);
            }
        });
        adapter2.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                // 这里可预览图片
                PictureConfig.getPictureConfig().externalPicturePreview(mContext, position, selectMedia2);
            }
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void cancleDialog() {

    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 上传图片
     * params:图片
     * id:
     * 人：
     * 类型
     *
     */
    public void uploadAllImage(){
        if(ToolUtil.isEmpty(selectMedia0) || selectMedia0.size() < maxSelectNum){
            ToastUtil.showToast(this,"\""+tvPhoto0.getText().toString()+"\"类型照片不足两张，请确认。",ToastUtil.TOAST_TYPE_WARNING);
            return;
        }
        switch (mType){

            case 3:
                if(ToolUtil.isEmpty(selectMedia2) || selectMedia2.size() < maxSelectNum){
                    ToastUtil.showToast(this,"\""+tvPhoto2.getText().toString()+"\"类型照片不足两张，请确认。",ToastUtil.TOAST_TYPE_WARNING);
                    return;
                }
            case 2:
                if(ToolUtil.isEmpty(selectMedia1) || selectMedia1.size() < maxSelectNum){
                    ToastUtil.showToast(this,"\""+tvPhoto1.getText().toString()+"\"类型照片不足两张，请确认。",ToastUtil.TOAST_TYPE_WARNING);
                    return;
                }
                break;
            case 1:
                break;
            default:
                break;

        }


            showProgress("正在上传图片...");

            File file1 = new File(selectMedia0.get(0).getPath());
            File file2 = new File(selectMedia0.get(1).getPath());

            RequestBody requestFile1 = RequestBody.create(MediaType.parse("multipart/form-data"), file1);
            RequestBody requestFile2 = RequestBody.create(MediaType.parse("multipart/form-data"), file2);

            MultipartBody.Part[]  filePart = new MultipartBody.Part[2];
            filePart[0] = MultipartBody.Part.createFormData("file", file1.getName(), requestFile1);
            filePart[1] = MultipartBody.Part.createFormData("file", file2.getName(), requestFile2);

            Map params = new HashMap();
            params.put("uid",new SPUtil(this,SPUtil.USER).getString(SPUtil.USER_UID,""));
            params.put("resid","");
            userClient.updateImage(filePart,params).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if(mType == 1) {
                        uploadOK();
                    }else{
                        uploadImageFor1();

                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    uploadErro(t.getMessage().toString());

                    ToastUtil.showToast(mContext,"上传失败"+t.getMessage().toString(),ToastUtil.TOAST_TYPE_ERRO);
                    dismissDialog();
                }
            });

    }



    private void uploadImageFor1(){

        File file1 = new File(selectMedia1.get(0).getPath());
        File file2 = new File(selectMedia1.get(1).getPath());

        RequestBody requestFile1 = RequestBody.create(MediaType.parse("multipart/form-data"), file1);
        RequestBody requestFile2 = RequestBody.create(MediaType.parse("multipart/form-data"), file2);

        MultipartBody.Part[]  filePart = new MultipartBody.Part[2];
        filePart[0] = MultipartBody.Part.createFormData("file", file1.getName(), requestFile1);
        filePart[1] = MultipartBody.Part.createFormData("file", file2.getName(), requestFile2);

        Map params = new HashMap();
        params.put("uid",new SPUtil(this,SPUtil.USER).getString(SPUtil.USER_UID,""));
        params.put("resid","");
        userClient.updateImage(filePart,params).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(mType == 2) {
                    uploadOK();
                }else{
                    uploadImageFor2();

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                uploadErro(t.getMessage().toString());

                ToastUtil.showToast(mContext,"上传失败"+t.getMessage().toString(),ToastUtil.TOAST_TYPE_ERRO);
                dismissDialog();
            }
        });
    }

    private void uploadImageFor2(){

        File file1 = new File(selectMedia2.get(0).getPath());
        File file2 = new File(selectMedia2.get(1).getPath());

        RequestBody requestFile1 = RequestBody.create(MediaType.parse("multipart/form-data"), file1);
        RequestBody requestFile2 = RequestBody.create(MediaType.parse("multipart/form-data"), file2);

        MultipartBody.Part[]  filePart = new MultipartBody.Part[2];
        filePart[0] = MultipartBody.Part.createFormData("file", file1.getName(), requestFile1);
        filePart[1] = MultipartBody.Part.createFormData("file", file2.getName(), requestFile2);

        Map params = new HashMap();
        params.put("uid",new SPUtil(this,SPUtil.USER).getString(SPUtil.USER_UID,""));
        params.put("resid","");
        userClient.updateImage(filePart,params).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                uploadOK();
                dismissDialog();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                uploadErro(t.getMessage().toString());
                ToastUtil.showToast(mContext,"上传失败"+t.getMessage().toString(),ToastUtil.TOAST_TYPE_ERRO);
                dismissDialog();
            }
        });
    }



    /**
     * 设置图片类型的个数
     */
    public void setCurrentType(int typeNum){
        mType = typeNum;
        switch (typeNum){
            case 1:
                llPhoto1.setVisibility(View.GONE);
                llPhoto2.setVisibility(View.GONE);
                break;
            case 2:
                llPhoto1.setVisibility(View.VISIBLE);
                llPhoto2.setVisibility(View.GONE);

                break;
            case 3:
                llPhoto1.setVisibility(View.VISIBLE);
                llPhoto2.setVisibility(View.VISIBLE);
                break;
        }
    }



    /**
     * 修改三个TextView的文字
     */
    public void setThisText0(String text){
        tvPhoto0.setText(text);
    }

    public void setThisText0(int textid){
        tvPhoto0.setText(getResources().getString(textid));
    }

    public void setThisText1(String text){
        tvPhoto1.setText(text);
    }

    public void setThisText1(int textid){
        tvPhoto1.setText(getResources().getString(textid));
    }

    public void setThisText2(String text){
        tvPhoto2.setText(text);
    }

    public void setThisText2(int textid){
        tvPhoto2.setText(getResources().getString(textid));
    }


    /**
     * 其它拍照按钮
     */
    public void openCamera(int type){
        PictureConfig.init(new FunctionConfig());
        switch (type){
            case 0:
                PictureConfig.getPictureConfig().startOpenCamera(mContext, resultCallback0);

                break;
            case 1:
                PictureConfig.getPictureConfig().startOpenCamera(mContext, resultCallback1);

                break;
            case 2:
                PictureConfig.getPictureConfig().startOpenCamera(mContext, resultCallback2);

                break;
        }
    }





    /**
     * 删除图片回调接口0
     */

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener0 = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick(int type, int position) {
            switch (type) {
                case 0:
                    // 先初始化参数配置，在启动相册
                    PictureConfig.init(new FunctionConfig());
                    //PictureConfig.getPictureConfig().openPhoto(this, resultCallback);

                    // 只拍照
                    PictureConfig.getPictureConfig().startOpenCamera(mContext, resultCallback0);
                    break;
                case 1:
                    // 删除图片
                    selectMedia0.remove(position);
                    adapter0.notifyItemRemoved(position);
                    break;
            }
        }
    };
    /**
     * 图片回调方法0
     */
    private PictureConfig.OnSelectResultCallback resultCallback0 = new PictureConfig.OnSelectResultCallback() {
        @Override
        public void onSelectSuccess(List<LocalMedia> resultList) {


            if (!ToolUtil.isEmpty(resultList)) {
                Log.i("callBack_result", selectMedia0.size() + "");
                selectMedia0.addAll(resultList);
            //    selectMedia0 = resultList;
                adapter0.setList(selectMedia0);
                adapter0.notifyDataSetChanged();
            }
        }
    };



    /**
     * 删除图片回调接口1
     */

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener1 = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick(int type, int position) {
            switch (type) {
                case 0:
                    // 先初始化参数配置，在启动相册
                    PictureConfig.init(new FunctionConfig());
                    //PictureConfig.getPictureConfig().openPhoto(this, resultCallback);

                    // 只拍照
                    PictureConfig.getPictureConfig().startOpenCamera(mContext, resultCallback1);
                    break;
                case 1:
                    // 删除图片
                    selectMedia1.remove(position);
                    adapter1.notifyItemRemoved(position);
                    break;
            }
        }
    };
    /**
     * 图片回调方法1
     */
    private PictureConfig.OnSelectResultCallback resultCallback1 = new PictureConfig.OnSelectResultCallback() {
        @Override
        public void onSelectSuccess(List<LocalMedia> resultList) {


            if (!ToolUtil.isEmpty(resultList)) {
                Log.i("callBack_result", selectMedia1.size() + "");
                selectMedia1.addAll(resultList);
              //  selectMedia1 = resultList;
                adapter1.setList(selectMedia1);
                adapter1.notifyDataSetChanged();
            }
        }
    };




    /**
     * 删除图片回调接口2
     */
    private GridImageAdapter.onAddPicClickListener onAddPicClickListener2 = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick(int type, int position) {
            switch (type) {
                case 0:
                    // 先初始化参数配置，在启动相册
                    PictureConfig.init(new FunctionConfig());
                    //PictureConfig.getPictureConfig().openPhoto(this, resultCallback);

                    // 只拍照
                    PictureConfig.getPictureConfig().startOpenCamera(mContext, resultCallback2);
                    break;
                case 1:
                    // 删除图片
                    selectMedia2.remove(position);
                    adapter2.notifyItemRemoved(position);
                    break;
            }
        }
    };
    /**
     * 图片回调方法2
     */
    private PictureConfig.OnSelectResultCallback resultCallback2 = new PictureConfig.OnSelectResultCallback() {
        @Override
        public void onSelectSuccess(List<LocalMedia> resultList) {


            if (!ToolUtil.isEmpty(resultList)) {
                Log.i("callBack_result", selectMedia2.size() + "");
                selectMedia2.addAll(resultList);
             //   selectMedia2 = resultList;
                adapter2.setList(selectMedia2);
                adapter2.notifyDataSetChanged();
            }
        }
    };




    protected abstract int getLayoutId();
    protected abstract void uploadOK();
    protected abstract void uploadErro(String erroInfo);

}
