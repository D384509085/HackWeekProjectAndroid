package enbledu.hackweekproject.Entity;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class RobotEntity {


    //qq号不在构造方法中获取，在传递给服务器数据后返回客户端额外设置
    private int qqID;
    //性格常量在CharacterDetail里
    private int character;
    private int stopSpeakingNum;
    private boolean isSpeakAuto;

    public RobotEntity(int character, int stopSpeakingNum, boolean isSpeakAoto) {
        this.character = character;
        this.stopSpeakingNum = stopSpeakingNum;
        this.isSpeakAuto = isSpeakAoto;
    }

    public int getCharacter() {
        return character;
    }

    public int getStopSpeakingNum() {
        return stopSpeakingNum;
    }

    public boolean isSpeakAoto() {
        return isSpeakAuto;
    }

    public void setCharacter(int character) {
        this.character = character;
    }

    public void setStopSpeakingNum(int stopSpeakingNum) {
        this.stopSpeakingNum = stopSpeakingNum;
    }

    public void setSpeakAoto(boolean speakAoto) {
        isSpeakAuto = speakAoto;
    }

    public int getQqID() {
        return qqID;
    }

    public void setQqID(int qqID) {
        this.qqID = qqID;
    }
}
