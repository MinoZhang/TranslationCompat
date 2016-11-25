package com.learn.translationcompat.database;

import android.provider.BaseColumns;

/**
 * @author MinoZhang
 * @since 2016.9.1
 * @do(DBUser辅助类)
 */
public class DBUser {
    public static final class  User implements BaseColumns{
        public static final String USERNAME = "username";
        public static final String USERPSW = "userpassword";
    }
}
