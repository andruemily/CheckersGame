package com.example.android.checkers;

/**
 * Created by emilyandrulis on 9/20/15.
 */
public class Checker {

    private boolean mIsKing;
    private boolean mIsRed;
    private int mPosition;

    public Checker(int position, boolean isRed) {
        mIsKing = false;
        mIsRed = isRed;
        mPosition = position;
    }

    public boolean isKing() {
        return mIsKing;
    }

    public void setIsKing(boolean isKing) {
        mIsKing = isKing;
    }

    public boolean isRed() {
        return mIsRed;
    }

    public void setIsRed(boolean isRed) {
        mIsRed = isRed;
    }

    public int getPosition() {
        return mPosition;
    }

    public void setPosition(int position) {
        mPosition = position;
    }


    //Override equals to only check that has the same color parameter
    @Override
    public boolean equals(Object o){
        return (o instanceof Checker && isRed() == ((Checker) o).isRed());
    }

}
