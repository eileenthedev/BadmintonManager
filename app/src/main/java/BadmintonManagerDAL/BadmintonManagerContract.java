package BadmintonManagerDAL;

import android.provider.BaseColumns;

public final class BadmintonManagerContract {

    private BadmintonManagerContract(){}

    //User Profile Table
    public static class UserProfile implements BaseColumns{
        public static final String TABLE_NAME = "User";
        public static final String COLUMN_NAME_PLAYER_NAME = "Name";
        public static final String COLUMN_NAME_PLAYER_LEVEL = "Level";
        public static final String COLUMN_NAME_PLAYER_GENDER = "Gender";

    }
}
