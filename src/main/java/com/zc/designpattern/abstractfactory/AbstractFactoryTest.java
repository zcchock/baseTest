package com.zc.designpattern.abstractfactory;

/**
 * @author chock
 * @since 2020/2/22
 */
public class AbstractFactoryTest {

    public static void main(String[] args) {
        IDBUtils idbUtils = new MysqlDBUtils();
        IConnection conn = idbUtils.getConn();
        conn.connect();
        ICommand comm = idbUtils.getComm();
        comm.exec();

    }

}


// 考虑后续变化的部分和稳定的部分
// 假设数据库相关稳定的属于数据库连接和sql执行
interface IConnection {
    void connect();
}

interface ICommand {
    void exec();
}

interface IDBUtils {
    IConnection getConn();

    ICommand getComm();
}

class MysqlConnection implements IConnection{
    @Override
    public void connect() {
        System.out.println("mysql 数据库已连接");
    }
}

class OracleConnection implements IConnection{
    @Override
    public void connect() {
        System.out.println("oracle 数据库已连接");
    }
}

class MysqlCommand implements ICommand {
    @Override
    public void exec() {
        System.out.println("mysql 命令已执行");
    }
}

class OracleCommand implements ICommand {
    @Override
    public void exec() {
        System.out.println("oracle 命令已执行");
    }
}

class MysqlDBUtils implements IDBUtils {
    @Override
    public IConnection getConn() {
        return new MysqlConnection();
    }

    @Override
    public ICommand getComm() {
        return new MysqlCommand();
    }
}

class OracleDBUtils implements IDBUtils {
    @Override
    public IConnection getConn() {
        return new OracleConnection();
    }

    @Override
    public ICommand getComm() {
        return new OracleCommand();
    }
}