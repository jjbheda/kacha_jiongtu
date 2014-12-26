package com.kachaembarrassed;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.graphics.Path;



public class KaChaEmarrassedApplication extends Application {
	

	/******************************
	 * private Members <br>
	 ******************************/
	
	
	private List<Activity> mActivityList = new LinkedList<Activity>();
	
	/** Default WorkSpace's path */
	private String mDefaultWorkeSpacePath = null;

	
	/** User clip path */
	private Path mUserClipPath = null;

	/******************************
	 * InnerClass <br>
	 ******************************/

	/** Singleton Methods */
	private static KaChaEmarrassedApplication mInstance;

	public static synchronized KaChaEmarrassedApplication getInstance() {
		if(mInstance == null){
			mInstance = new KaChaEmarrassedApplication();
		}
		return mInstance;
	}
/**
 * 
 */
	/******************************
	 * Construct <br>
	 ******************************/

	/******************************
	 * Implement Methods <br>
	 ******************************/

	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
	
	}

	@Override
	public void onTerminate() {
	
		super.onTerminate();
	}
	
	/******************************
	 * Public Methods <br>
	 ******************************/
	
	/** Add activity in the RunTime-Manage-activity-list */
	public void addActivity(Activity activity){
		if(activity != null && !mActivityList.contains(activity)){
			mActivityList.add(activity);
		}
	}
	
	/** Remove activity from the RunTime-Manage-activity-list */
	public void removeActivity(Activity activity){
		if(activity != null && mActivityList.contains(activity)){
			mActivityList.remove(activity);
		}
	}
	
	/**  traves the RunTime-Manage-activity-list and finish all of them */
	public void exitApp(){
		for(Activity act : mActivityList){
			if( act != null ) act.finish();
		}
		System.exit(0);
	}
	
	

	public void setDefaultWorkeSpacePath(String mDefaultWorkeSpacePath) {
		this.mDefaultWorkeSpacePath = mDefaultWorkeSpacePath;
	}
	
	
	

	public Path getUserClipPath() {
		return mUserClipPath;
	}

	public void setUserClipPath(Path mUserClipPath) {
		this.mUserClipPath = mUserClipPath;
	}

	
}