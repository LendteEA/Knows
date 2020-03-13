package com.bs.knows.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.baidu.ocr.sdk.model.GeneralBasicParams;
import com.baidu.ocr.sdk.model.GeneralParams;
import com.baidu.ocr.sdk.model.GeneralResult;
import com.baidu.ocr.sdk.model.WordSimple;

import java.io.File;

public class RecoginzeService {

    private static String token;
    private static String TAG="crop";
    public interface ServiceListener{
        public void onResult(String result);
    }

    /**
     * 以license方式初始化OCR
     * @return
     */
    public static String initAccessTokenLicenseFile(Context context) {

        OCR.getInstance(context).initAccessToken(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken accessToken) {
                token = accessToken.getAccessToken();
                Log.d(TAG, "onResult: " + token);
//                hasGotToken=true;
            }

            @Override
            public void onError(OCRError ocrError) {
                ocrError.printStackTrace();
                Log.d(TAG, "onError: "+ocrError.getMessage());

            }
        },"aip.license",context);

        return token;
    }


    //高精度识别
    public static void recAccurateBasic(Context context, File filePath, final ServiceListener listener){
        GeneralParams params=new GeneralParams();
        params.setDetectDirection(true);
        params.setVertexesLocation(true);
        params.setLanguageType(GeneralBasicParams.CHINESE_ENGLISH);
        params.setRecognizeGranularity(GeneralParams.GRANULARITY_SMALL);
        params.setImageFile(new File(String.valueOf(filePath)));

        //调用识别
        OCR.getInstance(context).recognizeAccurateBasic(params, new OnResultListener<GeneralResult>() {
            @Override
            public void onResult(GeneralResult generalResult) {
                StringBuilder sb=new StringBuilder();

                for (WordSimple wordSimple:generalResult.getWordList()){
                    WordSimple word=wordSimple;
                    sb.append(word.getWords());
                    sb.append("\n");
                }
            }

            @Override
            public void onError(OCRError ocrError) {
                Log.d("error", " RecoginzeService onError: "+ocrError.getMessage());
            }
        });
    }
}
