package com.example.host;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class MyData2 implements Parcelable
{
    private static int num;
    public MyData2()
    {
        super();
    }
    public MyData2(int n)
    {
        super();
        this.num = n;
    }
    public int getnum()
    {
        Log.d("MyData2","MyData : "+num);
        return num;
    }
    public void setnum(int i)
    {
        num = i;
    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel parcel, int flag) {
        parcel.writeInt(num);
    }

    public static final Creator<MyData2> CREATOR = new Creator<MyData2>() {
        @Override
        public MyData2 createFromParcel(Parcel in) {
            return new MyData2(in.readInt());
        }

        @Override
        public MyData2[] newArray(int size) {
            return new MyData2[size];
        }
    };


    protected MyData2(Parcel in) {
        super();
        num = in.readInt();
        Log.d("MyData2","MyData : "+num);
    }
    public void readFromParcel(Parcel in)
    {
        num = in.readInt();
    }



}
