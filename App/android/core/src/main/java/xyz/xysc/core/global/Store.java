package xyz.xysc.core.global;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

/**
 * @author architect.bian
 * @date 2017-11-30 3:28 PM
 */

public class Store {

    private SharedPreferences engine;

    public Store(Context context, String dbName) {
        this.engine = context.getSharedPreferences(dbName, Context.MODE_PRIVATE);
    }

    public int getInt(String key) {
        return this.engine.getInt(key, Integer.MIN_VALUE);
    }

    public void setInt(String key, int value) {
        this.engine.edit().putInt(key, value).commit();
    }

    public boolean getBoolean(String key) {
        return this.engine.getBoolean(key, false);
    }

    public void setBoolean(String key, boolean value) {
        this.engine.edit().putBoolean(key, value).commit();
    }

    public String getString(String key) {
        return this.engine.getString(key, "");
    }

    public void setString(String key, String value) {
        this.engine.edit().putString(key, value).commit();
    }

    /**
     * 移除Key对应的value值
     */
    public void remove(String key){
        this.engine.edit().remove(key).commit();
    }

    public void clearAll() {
        this.engine.edit().clear().commit();
    }

    public void putObject(String key, Object object) {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream objOut = null;
        try {
            objOut = new ObjectOutputStream(byteOut);
            objOut.writeObject(object);
            String objectVal = new String(Base64.encode(byteOut.toByteArray(), Base64.DEFAULT));
            this.engine.edit().putString(key, objectVal);
            this.engine.edit().commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (byteOut != null) {
                    byteOut.close();
                }
                if (objOut != null) {
                    objOut.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public <T> T getObject(String key, Class<T> clazz) {
        if (this.engine.contains(key)) {
            String objectVal = this.engine.getString(key, null);
            byte[] buffer = Base64.decode(objectVal, Base64.DEFAULT);
            ByteArrayInputStream byteIn = new ByteArrayInputStream(buffer);
            ObjectInputStream objIn = null;
            try {
                objIn = new ObjectInputStream(byteIn);
                return (T) objIn.readObject();
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (byteIn != null) {
                        byteIn.close();
                    }
                    if (objIn != null) {
                        objIn.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
