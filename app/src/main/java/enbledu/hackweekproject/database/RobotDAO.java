package enbledu.hackweekproject.database;

import java.util.ArrayList;

import enbledu.hackweekproject.Entity.RobotEntity;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public interface RobotDAO {

    public void insertRobot(RobotEntity robotEntity);

    public void deleteRobot(String name);

    public void updateRobot(RobotEntity robotEntity);

    public ArrayList<RobotEntity> getRobotDatas();
}
