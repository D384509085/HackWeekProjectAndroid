package enbledu.hackweekproject.Entity;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class RobotEntity {
    //性格常量
    public static int CHARACTER_TULING = 0;
    public static int CHARACTER_BAIDU = 1;
    public static int CHARACTER_MOLI = 2;

    //qq号不在构造方法中获取，在传递给服务器数据后返回客户端额外设置
    private int qqID;
    private int character;
    private int stopSpeakingNum;
    private boolean isSpeakAoto;

    public RobotEntity(int character, int stopSpeakingNum, boolean isSpeakAoto) {
        this.character = character;
        this.stopSpeakingNum = stopSpeakingNum;
        this.isSpeakAoto = isSpeakAoto;
    }

    public int getCharacter() {
        return character;
    }

    public int getStopSpeakingNum() {
        return stopSpeakingNum;
    }

    public boolean isSpeakAoto() {
        return isSpeakAoto;
    }

    public void setCharacter(int character) {
        this.character = character;
    }

    public void setStopSpeakingNum(int stopSpeakingNum) {
        this.stopSpeakingNum = stopSpeakingNum;
    }

    public void setSpeakAoto(boolean speakAoto) {
        isSpeakAoto = speakAoto;
    }

    public int getQqID() {
        return qqID;
    }

    public void setQqID(int qqID) {
        this.qqID = qqID;
    }
}
