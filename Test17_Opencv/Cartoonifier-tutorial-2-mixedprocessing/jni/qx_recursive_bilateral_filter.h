//#include "stdafx.h"
/***********************************************************************
\Author:	Qingxiong Yang
\Function:	Recursive Bilateral Filtering 
\Reference:	Qingxiong Yang, Recursive Bilateral Filtering,
			European Conference on Computer Vision (ECCV) 2012, 399-413.
************************************************************************/


//Large sigma_spatial/sigma_range parameter may results in visible artifact which can be removed by 
//an additional filter with small sigma_spatial/sigma_range parameter.
void qx_gradient_domain_recursive_bilateral_filter(double***out,double***in,unsigned char***texture,
double sigma_spatial,double sigma_range,int h,int w,double***temp,double***temp_2w);
