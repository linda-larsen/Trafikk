//
// Created by Linda on 12/17/2021.
//

#include <jni.h>
#include <string>
#include <opencv2/core.hpp>
#include <opencv2/imgcodecs.hpp>
#include <opencv2/highgui.hpp>
#include <opencv2/imgproc.hpp>
#include <iostream>

using namespace cv;
using namespace std;


int main() {
    string path = "Resources/test.jpg";
    Rect();
    Mat img = imread(path);
    imshow("Image", img);
    waitKey(0);
    return 1;
}
