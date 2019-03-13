package com.synjones.common_base.view.loading;

import android.app.Dialog;
import android.content.Context;
import android.view.View;


import com.synjones.common_base.R;
import com.synjones.common_base.utils.StringUtils;


public class ProgressDialog extends Dialog {


	private OnItemClickListener mOnItemClickListener = null;

	public static interface OnItemClickListener {
		void onItemClick(View view);
	}

	public void setOnItemClickListener(OnItemClickListener listener) {
		this.mOnItemClickListener = listener;
	}


	private boolean isFail = false;

	LoadingView loadingView;

	public ProgressDialog(Context context) {
        this(context, R.style.CustomProgressDialog);
		init();
	}

	public ProgressDialog(Context context, boolean canCloseable, String msg) {
        this(context, R.style.CustomProgressDialog);
		init(canCloseable, msg);
	}

	public ProgressDialog(Context context, boolean canCloseable, int msg) {
		this(context, R.style.CustomProgressDialog);
		init(canCloseable, msg);
	}

	public ProgressDialog(Context context, int theme) {
		super(context, theme);

	 }

	/**
	 * 初始化页面
	 */
	private void init() {
		init(false, null);
	}
	
	/**
	 * 初始化页面
	 * @param canCloseable
	 * @param msg
	 */
	private void init(boolean canCloseable, String msg) {
		setContentView(R.layout.waiting_pupop);
		loadingView = (LoadingView) findViewById(R.id.loadingView);
		if (!StringUtils.isEmpty(msg)) {
			loadingView.setText(msg);
		}

		loadingView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (isFail) {
					if (null != mOnItemClickListener) {
						mOnItemClickListener.onItemClick(v);
					}

				}
			}
		});

		setCancelable(canCloseable);
		setCanceledOnTouchOutside(canCloseable);
	}
	
	/**
	 * 初始化页面
	 * @param canCloseable
	 * @param msg
	 */
	private void init(boolean canCloseable, int msg) {
		setContentView(R.layout.waiting_pupop);
		loadingView = (LoadingView) findViewById(R.id.loadingView);
		setCancelable(canCloseable);
		setCanceledOnTouchOutside(canCloseable);
	}
	 
	 /**
	  * 设置是否可关闭
	  * @param canClose
	  */
	 public void setCanClose(boolean canClose)
	 {
		 setCancelable(canClose);
		 setCanceledOnTouchOutside(canClose);
	 }
	 
	  /**
      *
      * liangzx
      * setMessage 提示内容
      * @param strMessage
      */
     public void setMessage(String strMessage){
     }
     
     /**
      *
      * liangzx
      * setMessage 提示内容
      * @param strMessage
      */
     public void setMessage(int strMessage){
     }
	
	@Override
	public void dismiss() {
		if (null != this && isShowing()) {
			super.dismiss();
		}
	}
	
	@Override
	public void show() {
     	isFail = false;
		if (null != this) {
			super.show();
		}
	}

	/**
	 * 加载中...
	 */
	public void showLoading() {
		loadingView.showLoading();
		loadingView.setText("正在加载");
	}

	/**
	 * 加载失败...
	 */
	public void loadFail(String msg) {
		isFail = true;
		loadingView.showFail();
		if (!StringUtils.isEmail(msg)) {
			loadingView.setText(msg);
		}
		loadingView.setText("加载失败");

	}
	/**
	 * 加载成功...
	 */
	public void loadSuccess() {
		loadingView.showSuccess();
		loadingView.setText("加载成功");
	}


}
