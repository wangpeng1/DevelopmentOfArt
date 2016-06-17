package qingfengmy.developmentofart._2activity.binderpool.impl;

import android.os.RemoteException;

import qingfengmy.developmentofart._2activity.binderpool.aidl.ICompute;

/**
 * Created by Administrator on 2016/6/17.
 */
public class ComputerImpl extends ICompute.Stub {
    @Override
    public int add(int a, int b) throws RemoteException {
        return a+b;
    }
}
