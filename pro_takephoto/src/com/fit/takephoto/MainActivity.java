package com.fit.takephoto;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	public final String DRIVER_IMG_PATH = "/sdcard/ssss.jpg";
	public final String DRIVER_IMG_PATH1 = "/sdcard/ssss.jpg";
	private String _dPhotoPath;
	private String _tPhotoPath;
	int mPhoto = -1;
	final int choosephoto = 0;
	final int take = 0;
	ImageView photo;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        EditText text=(EditText) findViewById(R.id.text);
        text.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this,"sssssssssssss",Toast.LENGTH_LONG).show();
			}
		});
        
        Button btn=(Button) findViewById(R.id.takePhoto);
        
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				int sdvalue = 1;
				//调用Utils里的isSdPresent()方法给与存储路径
				sdvalue =isSdPresent();
				if (sdvalue == 0) {
					mPhoto = 0;
					//_dPhotoPath = DRIVER_IMG_PATH;
					//调用拍照方法
					 _dPhotoPath = DRIVER_IMG_PATH;
//					startCameraActivity(take);
					 startCamera();
				} else if (sdvalue == 2) {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							MainActivity.this);
					builder.setMessage(
							"Your device memory is Full, please remove some applications/data form SD Card")
							.setCancelable(true)
							.setPositiveButton("OK",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog, int id) {
											dialog.cancel();

										}
									});
					AlertDialog alert = builder.create();
					alert.show();
				} else {
					
			}
			}
		});
        
        photo=(ImageView) findViewById(R.id.photo);
        if(_dPhotoPath==null){
      	  photo.setScaleType(ImageView.ScaleType.CENTER_CROP);
            photo.setImageBitmap(getPreview(DRIVER_IMG_PATH1, true));
      }else{
      	 photo.setScaleType(ImageView.ScaleType.CENTER_CROP);
           photo.setImageBitmap(getPreview(_dPhotoPath, true));
      }
        
        
        Button choose=(Button) findViewById(R.id.choose);
        choose.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mPhoto = 0;
				startChooseActivity(choosephoto);
			}
		});
     
    }
    
    
	protected void startChooseActivity(int photooption) {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(Intent.createChooser(intent, "Select Picture"),
				photooption);

	}
    
	protected void onPhotoChoose(Intent data) {
		String filePath = "";
		if (data != null) {
			Uri selectedImageUri = data.getData();
			if (selectedImageUri != null) {
				if (selectedImageUri.toString().startsWith("content:")) {
					filePath = getPath(selectedImageUri);
				} else {
					filePath = selectedImageUri.getPath();
				}
				if (mPhoto == 0) {
					photo.setScaleType(ImageView.ScaleType.CENTER_CROP);
					_dPhotoPath = filePath;
					photo.setImageBitmap(getPreview(_dPhotoPath, true));
				} else {
					photo.setScaleType(ImageView.ScaleType.CENTER_CROP);
					_tPhotoPath = filePath;
					photo.setImageBitmap(getPreview(_tPhotoPath, true));
				}
			}
		}
		mPhoto = -1;
	}
    
    public String getPath(Uri uri) {
		String path = "";
		String[] projection = { MediaStore.Images.Media.DATA };
		Cursor cursor = managedQuery(uri, projection, null, null, null);

		if (cursor != null) {
			try {
				int column_index = cursor
						.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
				cursor.moveToFirst();
				path = cursor.getString(column_index);
			} catch (IllegalArgumentException ile) {

			}
		}
		return path;
	}
    
    
    public  int isSdPresent() {
        Boolean sdcardpresent = android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        
        if(sdcardpresent){
        	StatFs stat = new StatFs(android.os.Environment.getExternalStorageDirectory().getPath());
        	long bytesAvailable = (long)stat.getBlockSize() *(long)stat.getBlockCount();
        	long megAvailable = bytesAvailable / 1048576;
        	if(megAvailable>0.5){
        		 return 0;
        	}else{
        		return 2;
        	}

        }else{
        	return 1;
        }
     }
    protected void startCameraActivity(int photooption) {
		File file;
		Uri outputFileUri;
		if (mPhoto == 0) {
			file = new File(_dPhotoPath);
			outputFileUri = Uri.fromFile(file);
		} else {
			file = new File(_tPhotoPath);
			outputFileUri = Uri.fromFile(file);
		}
		Intent intent = new Intent(
				android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

		startActivityForResult(intent, photooption);
	}
    
    protected void startCamera(){
    	FileOutputStream out=null;
    	
    	try {
			out=MainActivity.this.openFileOutput("driver.jpg", MODE_PRIVATE);
			String mm=Environment.getDataDirectory().toString()+"/files";
			Toast.makeText(MainActivity.this, mm, Toast.LENGTH_LONG).show();
			Intent intent = new Intent(
					android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, "");

			startActivityForResult(intent,0);
			
			out.write(mm.getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    //get bitmap
    public static Bitmap getPreview(String path, Boolean smallsize) {
		File image = new File(path);

		BitmapFactory.Options bounds = new BitmapFactory.Options();
		bounds.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(image.getPath(), bounds);
		if ((bounds.outWidth == -1) || (bounds.outHeight == -1))
			return null;

		int originalSize = (bounds.outHeight > bounds.outWidth) ? bounds.outHeight
				: bounds.outWidth;

		BitmapFactory.Options opts = new BitmapFactory.Options();
		if(smallsize)
		  opts.inSampleSize = originalSize / 100;
		return BitmapFactory.decodeFile(image.getPath(), opts);
	}

}