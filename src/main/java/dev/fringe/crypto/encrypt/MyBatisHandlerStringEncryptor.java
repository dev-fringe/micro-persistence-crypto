package dev.fringe.crypto.encrypt;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import test.util.EncryptionUtil;

import javax.crypto.NoSuchPaddingException;
import java.security.GeneralSecurityException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by v.hdlee on 2016-09-13.
 */
public class MyBatisHandlerStringEncryptor implements TypeHandler<String> {
    public void setParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {
        byte[] password = null;
        if (s != null)
            try {
                password = EncryptionUtil.encrypt(s) ;
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }
        preparedStatement.setBytes(i, password);
    }

    public String getResult(ResultSet resultSet, String s) throws SQLException {
        String retValue = null;
        try {
            retValue = EncryptionUtil.decrypt(resultSet.getBytes(s));
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    public String getResult(ResultSet resultSet, int i) throws SQLException {
        String retValue = null;
        try {
            retValue = EncryptionUtil.decrypt(resultSet.getBytes(i));
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    public String getResult(CallableStatement callableStatement, int i) throws SQLException {
        String retValue = null;
        try {
            retValue = EncryptionUtil.decrypt(callableStatement.getBytes(i));
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return retValue;
    }
}
