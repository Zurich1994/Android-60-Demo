
package com.android.takephoto;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivitytakephoto extends Activity {

    private CameraView cv;

    private Camera mCamera = null;

    public Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {

        public void onPictureTaken(byte[] data, Camera camera) {
            Bitmap mBitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            File myCaptureFile = new File(getFilesDir(), "driver.jpg");
            try {
                BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(
                        myCaptureFile));
                mBitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
                os.flush();
                os.close();

                resetCamera();

                Intent intent = new Intent();
                intent.putExtra("photopath", myCaptureFile.getPath());
                setResult(RESULT_OK, intent);
                finish();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (mBitmap != null) {
                    mBitmap.recycle();
                }
            }
        }
    };

    private void resetCamera() {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    // Activity的创建方法

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        setContentView(R.layout.takephoto);
        
        LinearLayout fl=(LinearLayout) findViewById(R.id.layout);
        cv = new CameraView(this);
        fl.addView(cv);
       
        
        
        Button take=(Button) findViewById(R.id.take);
        take.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 if (mCamera != null) {
		                mCamera.takePicture(null, null, pictureCallback);
		            }
			}
		});
   
    Button cancel=(Button) findViewById(R.id.cancel);
    cancel.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			finish();
		}
	});
}
//
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
//            if (mCamera != null) {
//                mCamera.takePicture(null, null, pictureCallback);
//            }
//        }
//        return cv.onKeyDown(keyCode, event);
//    }

    
    /*
     * (non-Javadoc)
     * @see android.app.Activity#onPause()
     */
    @Override
    protected void onPause() {
        super.onPause();
        resetCamera();
    }

    // 照相视图
    class CameraView extends SurfaceView {

        private SurfaceHolder holder = null;

        public CameraView(Context context) {
            super(context);
            holder = this.getHolder();
            holder.addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                }

                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    mCamera = Camera.open();
                    try {
                        mCamera.setPreviewDisplay(holder);
                    } catch (IOException e) {
                        mCamera.release();
                        mCamera = null;
                    }
                }
                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                    Camera.Parameters parameters = mCamera.getParameters();
                    // 设置格式
                    parameters.setPictureFormat(PixelFormat.JPEG);
                    // 设置预览大小，这里我的测试机是Milsstone所以设置的是854x480
                     parameters.setPreviewSize(320, 240);
                    // 设置自动对焦
                     parameters.setFocusMode(Parameters.FOCUS_MODE_AUTO);
                    // 设置图片保存时的分辨率大小
                    // parameters.setPictureSize(320, 480);
                    // 给相机对象设置刚才设定的参数
                    mCamera.setParameters(parameters);
                    // 开始预览
                    mCamera.startPreview();
                }
            });
            holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }

    }

}


