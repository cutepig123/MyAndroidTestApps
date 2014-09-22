#include <jni.h>
#include <opencv2/core/core.hpp>
#include <opencv2/imgproc/imgproc.hpp>
#include <opencv2/features2d/features2d.hpp>
#include <vector>

using namespace std;
using namespace cv;

void cartoonifyImage(Mat const& srcColor, Mat &dst, bool sketchMode, bool alienMode, bool evilMode, int debugType);

extern "C" {
JNIEXPORT void JNICALL Java_org_opencv_samples_tutorial2_Tutorial2Activity_FindFeatures(JNIEnv*, jobject, jlong addrGray, jlong addrRgba);

JNIEXPORT void JNICALL Java_org_opencv_samples_tutorial2_Tutorial2Activity_FindFeatures(JNIEnv*, jobject, jlong addrGray, jlong addrRgba)
{
    Mat& mGr  = *(Mat*)addrGray;
    Mat& mRgb = *(Mat*)addrRgba;
    vector<KeyPoint> v;

    FastFeatureDetector detector(50);
    detector.detect(mGr, v);
    printf("Feature Num %d\n", v.size());fflush(stdout);
    for( unsigned int i = 0; i < v.size(); i++ )
    {
        const KeyPoint& kp = v[i];
        circle(mRgb, Point(kp.pt.x, kp.pt.y), 50, Scalar(255,0,0,255));
        printf("Feature[%d] %f %f\n", i, kp.pt.x, kp.pt.y);
        fflush(stdout);
    }
}

void cartoonifyImagex(Mat const &mbgr, Mat &mbgrdes, jboolean sketchMode, jboolean alienMode, jboolean evilMode,jboolean debugType )
{
	// mbgr-->mGray-->mGrayCanny-->mbgrdes
	Mat mGray(mbgr.rows, mbgr.cols, CV_8UC1);
	Mat mGrayCanny(mbgr.rows, mbgr.cols, CV_8UC1);
	cvtColor(mbgr, mGray, CV_BGR2GRAY);

	Canny(mGray, mGrayCanny, 80, 100);
	cvtColor(mGrayCanny, mbgrdes, CV_GRAY2BGR);
}


// Modify the camera image using the Cartoonifier filter.
JNIEXPORT void JNICALL Java_org_opencv_samples_tutorial2_Tutorial2Activity_CartoonifyImage(JNIEnv* env, jobject,
        jlong addrGray, jlong addrRgba,
        jboolean sketchMode, jboolean alienMode, jboolean evilMode, jboolean debugMode)
{
	printf("Hello, Java_org_opencv_samples_tutorial2_Tutorial2Activity_CartoonifyImage!\n");
	fflush(stdout);

	// addrRgba-->mrgba-->mbgr
    Mat &mrgba=*(Mat*)addrRgba;
    Mat displayedFramebgr(mrgba.size(), CV_8UC3);
    Mat mbgr(mrgba.rows, mrgba.cols, CV_8UC3);   // Allocate a new image buffer.

    cvtColor(mrgba, mbgr, CV_RGBA2BGR);

    int debugType = 0;

    // mbgr -->displayedFramebgr -->mrgba
    cartoonifyImage(mbgr, displayedFramebgr, sketchMode, alienMode, evilMode, debugType);
    cvtColor(displayedFramebgr, mrgba, CV_BGR2RGBA);

}

}
