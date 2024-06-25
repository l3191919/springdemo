package com.lyz.shiwushixiao;

import java.util.concurrent.TimeoutException;

public interface ShiwushixiaoService {
    public void add()  throws TimeoutException;
    public void add1() throws TimeoutException;
    public void query();
    public void add2();

    public void threadAdd() throws TimeoutException;
}
