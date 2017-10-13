package ailixgame.com.mangathread;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import ailixgame.com.mangathread.util.Const;

public class ImageRequestActivity extends Activity{

	private static final String TAG = ImageRequestActivity.class
			.getSimpleName();
	private Button btnImageReq;
	private NetworkImageView imgNetWorkView;
	private ImageView imageView;
	private Float mx;
	private  Float my;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);
		btnImageReq = (Button) findViewById(R.id.btnImageReq);
		imageView = (ImageView) findViewById(R.id.imgView);
		//makeImageRequest();
		btnImageReq.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//AppController.getInstance().getRequestQueue().getCache().invalidate(Const.URL_IMAGE, true);
				makeImageRequest();
			}
		});
		imageView.setOnTouchListener(new View.OnTouchListener() {


			public boolean onTouch(View arg0, MotionEvent event) {

				float curX, curY;

				switch (event.getAction()) {

					case MotionEvent.ACTION_DOWN:
						mx = event.getX();
						my = event.getY();
						break;
					case MotionEvent.ACTION_MOVE:
						curX = event.getX();
						curY = event.getY();
						imageView.scrollBy((int) (mx - curX), (int) (my - curY));
						mx = curX;
						my = curY;
						break;
					case MotionEvent.ACTION_UP:
						curX = event.getX();
						curY = event.getY();
						imageView.scrollBy((int) (mx - curX), (int) (my - curY));
						break;
				}

				return true;
			}
		});
	}

	private void makeImageRequest() {
		ImageLoader imageLoader = AppController.getInstance().getImageLoader();

		// If you are using NetworkImageView
		//imgNetWorkView.setImageUrl(Const.URL_IMAGE, imageLoader);

		
		// If you are using normal ImageView
		imageLoader.get(Const.URL_IMAGE, new ImageLoader.ImageListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e(TAG, "Image Load Error: " + error.getMessage());
			}

			@Override
			public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
				if (response.getBitmap() != null) {
					// load image into imageview
					imageView.setImageBitmap(response.getBitmap());
				}
			}
		});

		// Loading image with placeholder and error image
		/*imageLoader.get(Const.URL_IMAGE, ImageLoader.getImageListener(
				imageView, R.drawable.ico_loading, R.drawable.ico_error));

		Cache cache = AppController.getInstance().getRequestQueue().getCache();
		Cache.Entry entry = cache.get(Const.URL_IMAGE);
		if(entry != null){
			try {
				String data = new String(entry.data, "UTF-8");
				Log.d("I_IMG", data);
				// handle data, like converting it to xml, json, bitmap etc.,
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();

			}
		}else{
			// cached response doesn't exists. Make a network call here
		}*/

	}

}
