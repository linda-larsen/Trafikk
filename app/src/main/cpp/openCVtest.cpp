//
// Created by Linda on 12/17/2021.
//

#include <jni.h>
#include <string>
#include <opencv2/imgcodecs.hpp>
#include <opencv2/highgui.hpp>
#include <opencv2/imgproc.hpp>
#include <opencv2/objdetect.hpp>
#include <opencv2/features2d.hpp>
#include <iostream>
#include <vector>

using namespace cv;
using namespace std;

int main() {
    for (int i = 1; i < 136; i++) {
        /* dataset removed due to large size. Indended for input data from the client*/
        string beginning = "Resources/positive_";
        string end = ".jpg";
        string path;

        path = beginning + to_string(i) + end;
        Mat img = imread(path);

        CascadeClassifier signCascade;
        signCascade.load("Resources/cascade.xml");

        if (signCascade.empty()) {
            cout << "Couldn't load XML file" << endl;
        }

        vector<Rect> signs;
        signCascade.detectMultiScale(img, signs, 1.18, 6);

        for (int i = 0; i < signs.size(); i++) {
            rectangle(img, signs[i].tl(), signs[i].br(), Scalar(255, 0, 255), 3);
        }

        imshow("Image", img);
        waitKey(0);

    }
    return 1;
}
