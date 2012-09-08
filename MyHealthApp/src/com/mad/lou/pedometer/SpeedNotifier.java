package com.mad.lou.pedometer;


public class SpeedNotifier implements PaceNotifier.Listener, SpeakingTimer.Listener {

    public interface Listener {
        public void valueChanged(float value);
        public void passValue();
    }
    private Listener mListener;
    
    int mCounter = 0;
    float mSpeed = 0;
    
    boolean mIsMetric;
    float mStepLength;

    PedometerSettings mSettings;
    Utils mUtils;

    /** Desired speed, adjusted by the user */
    float mDesiredSpeed;
    
    /** Should we speak? */
    boolean mShouldTellFasterslower;
    boolean mShouldTellSpeed;
    
    
    public SpeedNotifier(Listener listener, PedometerSettings settings, Utils utils) {
        mListener = listener;
        mUtils = utils;
        mSettings = settings;
        mDesiredSpeed = mSettings.getDesiredSpeed();
        reloadSettings();
    }
    public void setSpeed(float speed) {
        mSpeed = speed;
        notifyListener();
    }
    public void reloadSettings() {
        mIsMetric = mSettings.isMetric();
        mStepLength = mSettings.getStepLength();
        mShouldTellSpeed = mSettings.shouldTellSpeed();
        mShouldTellFasterslower = 
            mSettings.shouldTellFasterslower()
            && mSettings.getMaintainOption() == PedometerSettings.M_SPEED;
        notifyListener();
    }
    public void setDesiredSpeed(float desiredSpeed) {
        mDesiredSpeed = desiredSpeed;
    }
    
    private void notifyListener() {
        mListener.valueChanged(mSpeed);
    }
    
    public void paceChanged(int value) {
        if (mIsMetric) {
            mSpeed = // kilometers / hour
                value * mStepLength // centimeters / minute
                / 100000f * 60f; // centimeters/kilometer
        }
        else {
            mSpeed = // miles / hour
                value * mStepLength // inches / minute
                / 63360f * 60f; // inches/mile 
        }
       
        notifyListener();
    }
    
      
    public void passValue() {
        // Not used
    }

    public void speak() {
        if (mSettings.shouldTellSpeed()) {
            if (mSpeed >= .01f) {
                mUtils.say(("" + (mSpeed + 0.000001f)).substring(0, 4) + (mIsMetric ? " kilometers per hour" : " miles per hour"));
            }
        }
        
    }

}

