package qingfengmy.developmentofart._2activity.binderpool.impl;

import android.os.RemoteException;

import qingfengmy.developmentofart._2activity.binderpool.aidl.ISecurityCenter;

/**
 * Created by Administrator on 2016/6/17.
 */
public class SecurityCenterImpl extends ISecurityCenter.Stub {
    @Override
    public String encrypt(String content) throws RemoteException {
        char[] chars = content.toCharArray();
        for (int i=0; i<chars.length;i++) {
            chars[i] = (char) (chars[i]+1);
        }

        return new String(chars);
    }

    @Override
    public String decrypt(String password) throws RemoteException {
        char[] chars = password.toCharArray();
        for (int i=0; i<chars.length;i++) {
            chars[i] = (char) (chars[i]-1);
        }

        return new String(chars);
    }
}
