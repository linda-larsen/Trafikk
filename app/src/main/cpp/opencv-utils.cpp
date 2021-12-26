//
// Created by Linda on 12/25/2021.
//

#include <opencv2/core.hpp>
#include "opencv-utils.h"
#include <opencv2/imgproc.hpp>

using namespace cv;

void myFlip(Mat src) {
    flip(src, src, 0);
}

void myBlur(Mat src, float sigma){
    GaussianBlur(src, src, Size(), sigma);
}