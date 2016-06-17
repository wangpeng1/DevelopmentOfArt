// ISecurityCenter.aidl
package qingfengmy.developmentofart._2activity.binderpool.aidl;

interface ISecurityCenter {
    String encrypt(in String content);
    String decrypt(in String password);
}
