package com.xy.viewdemo;
import com.xy.viewdemo.ICallback;

interface AidlManager {

String client2Server(String name);

void registerCallback(in ICallback back);

}