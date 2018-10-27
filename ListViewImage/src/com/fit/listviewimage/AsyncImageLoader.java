package com.fit.listviewimage;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;

public class AsyncImageLoader {

	private HashMap<String, SoftReference<Drawable>> imageCache;

	public AsyncImageLoader() {
		imageCache = new HashMap<String, SoftReference<Drawable>>();
	}

	public Drawable loadDrawable(final String imageUrl,final ImageCallback imageCallback) {
		//�������ͬ��url��ֱ��ȡ
		if (imageCache.containsKey(imageUrl)) {
			SoftReference<Drawable> softReference = imageCache.get(imageUrl);
			Drawable drawable = softReference.get();
			if (drawable != null) {
				return drawable;
			}
		}
		//���û�оͿ����߳�ȥ����
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				imageCallback.imageLoaded((Drawable) msg.obj, imageUrl);
			}
		};
		new Thread() {
			@Override
			public void run() {
				Drawable drawable = loadImageFromUrl(imageUrl);
				//�뼯�������ͼƬ
				imageCache.put(imageUrl, new SoftReference<Drawable>(drawable));
				Message message = handler.obtainMessage(0, drawable);
				handler.sendMessage(message);
			}
		}.start();
		return null;
	}
	//����ͼƬ�ķ���
	public static Drawable loadImageFromUrl(String url) {
		URL m;
		InputStream i = null;
		try {
			m = new URL(url);
			i = (InputStream) m.getContent();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Drawable d = Drawable.createFromStream(i, "src");
		return d;
	}
	
	public interface ImageCallback {
		public void imageLoaded(Drawable imageDrawable, String imageUrl);
	}
}
