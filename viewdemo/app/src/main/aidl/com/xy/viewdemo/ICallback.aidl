package com.xy.viewdemo;

//Solve the problem of garbled Chinese characters:  https://blog.csdn.net/qq_19154605/article/details/107836869

interface ICallback {

// void server2Client(out byte[] data);
 void server2Client(inout byte[] data);

}