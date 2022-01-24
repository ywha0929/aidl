// IMyAidlInterface.aidl
package com.example.host;
import com.example.host.MyData2;
parcelable MyData2;
// Declare any non-default types here with import statements
interface IMyAidlInterface {
    int getData();
    void resetData();
    void setData(int i);
    void setMyData(in Bundle bundle);
    void setMyData2(in MyData2 data);
}