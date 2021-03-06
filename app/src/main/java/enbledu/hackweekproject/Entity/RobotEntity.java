package enbledu.hackweekproject.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class RobotEntity implements Serializable {


    //qq号不在构造方法中获取，在传递给服务器数据后返回客户端额外设置
    private String name;
    private long qqID;
    //性格常量在CharacterDetail里
    private int character;
    private int stopSpeakingNum;
    private boolean isOpen;
    private boolean isSpeakAuto;

    public RobotEntity() {

    }

    public RobotEntity(String name, long qqID, int character, int stopSpeakingNum, boolean isOpen, boolean isSpeakAoto) {
        this.qqID = qqID;
        this.name = name;
        this.character = character;
        this.stopSpeakingNum = stopSpeakingNum;
        this.isOpen = isOpen;
        this.isSpeakAuto = isSpeakAoto;
    }

    //不含qq号的构造方法
    public RobotEntity(String name, int character, int stopSpeakingNum, boolean isOpen, boolean isSpeakAoto) {

        this.name = name;
        this.character = character;
        this.stopSpeakingNum = stopSpeakingNum;
        this.isSpeakAuto = isSpeakAoto;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public int getCharacter() {
        return character;
    }

    public int getStopSpeakingNum() {
        return stopSpeakingNum;
    }

    public boolean isSpeakAuto() {
        return isSpeakAuto;
    }

    public void setCharacter(int character) {
        this.character = character;
    }

    public void setSpeakAuto(boolean speakAuto) {
        isSpeakAuto = speakAuto;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setStopSpeakingNum(int stopSpeakingNum) {
        this.stopSpeakingNum = stopSpeakingNum;
    }

    public long getQqID() {
        return qqID;
    }

    public void setQqID(long qqID) {
        this.qqID = qqID;
    }

    @Override
    public String toString() {
        return "RobotEntity{" +
                "name='" + name + '\'' +
                ", qqID=" + qqID +
                ", character=" + character +
                ", stopSpeakingNum=" + stopSpeakingNum +
                ", isOpen=" + isOpen +
                ", isSpeakAuto=" + isSpeakAuto +
                '}';
    }
}
