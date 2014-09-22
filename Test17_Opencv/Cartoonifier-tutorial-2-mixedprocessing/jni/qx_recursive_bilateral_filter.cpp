//#include "stdafx.h"
#include "qx_basic.h"
inline int qx_compute_color_difference(unsigned char a[3],unsigned char b[3])
{
	unsigned char dr=abs(a[0]-b[0]);
	unsigned char dg=abs(a[1]-b[1]);
	unsigned char db=abs(a[2]-b[2]);
	return(((dr<<1)+dg+db)>>2);
}
void qx_gradient_domain_recursive_bilateral_filter(double***out,double***in,unsigned char***texture,double sigma_spatial,double sigma_range,int h,int w,double***temp,double***temp_2w)
{
	double range_table[QX_DEF_CHAR_MAX+1];//compute a lookup table
	double inv_sigma_range;
	inv_sigma_range=1.0/(sigma_range*QX_DEF_CHAR_MAX);
	for(int i=0;i<=QX_DEF_CHAR_MAX;i++) range_table[i]=exp(-i*inv_sigma_range);
	double alpha=exp(-sqrt(2.0)/(sigma_spatial*w));//filter kernel size
	double***in_=in;
	unsigned char tpr,tpg,tpb,tcr,tcg,tcb;
	double ypr,ypg,ypb,ycr,ycg,ycb;
	for(int y=0;y<h;y++)/*horizontal filtering*/
	{
		double*temp_x=temp[y][0];
		double*in_x=in_[y][0];
		unsigned char*texture_x=texture[y][0];
		*temp_x++=ypr=*in_x++; *temp_x++=ypg=*in_x++; *temp_x++=ypb=*in_x++;
		tpr=*texture_x++; tpg=*texture_x++; tpb=*texture_x++;
		for(int x=1;x<w;x++) //from left to right
		{
			tcr=*texture_x++; tcg=*texture_x++; tcb=*texture_x++;
			unsigned char dr=abs(tcr-tpr);
			unsigned char dg=abs(tcg-tpg);
			unsigned char db=abs(tcb-tpb);
			int range_dist=(((dr<<1)+dg+db)>>2);
			double weight=range_table[range_dist];
			double alpha_=weight*alpha;
			double inv_alpha_=1-alpha_;
			*temp_x++=ycr=inv_alpha_*(*in_x++)+alpha_*ypr; *temp_x++=ycg=inv_alpha_*(*in_x++)+alpha_*ypg; *temp_x++=ycb=inv_alpha_*(*in_x++)+alpha_*ypb;//update temp buffer
			tpr=tcr; tpg=tcg; tpb=tcb;
			ypr=ycr; ypg=ycg; ypb=ycb;
		}
		int w1=w-1;
		*--temp_x; *temp_x=0.5*((*temp_x)+(*--in_x)); 
		*--temp_x; *temp_x=0.5*((*temp_x)+(*--in_x)); 
		*--temp_x; *temp_x=0.5*((*temp_x)+(*--in_x));
		
		ypr=*in_x; ypg=*in_x; ypb=*in_x;
		tpr=*--texture_x; tpg=*--texture_x; tpb=*--texture_x;

		for(int x=w-2;x>=0;x--) //from right to left
		{
			tcr=*--texture_x; tcg=*--texture_x; tcb=*--texture_x;
			unsigned char dr=abs(tcr-tpr);
			unsigned char dg=abs(tcg-tpg);
			unsigned char db=abs(tcb-tpb);
			int range_dist=(((dr<<1)+dg+db)>>2);
			double weight=range_table[range_dist];
			double alpha_=weight*alpha;
			double inv_alpha_=1-alpha_;

			ycr=inv_alpha_*(*--in_x)+alpha_*ypr; ycg=inv_alpha_*(*--in_x)+alpha_*ypg; ycb=inv_alpha_*(*--in_x)+alpha_*ypb;
			*--temp_x; *temp_x=0.5*((*temp_x)+ycr);
			*--temp_x; *temp_x=0.5*((*temp_x)+ycg);
			*--temp_x; *temp_x=0.5*((*temp_x)+ycb);
			tpr=tcr; tpg=tcg; tpb=tcb;
			ypr=ycr; ypg=ycg; ypb=ycb;
		}
	}
	alpha=exp(-sqrt(2.0)/(sigma_spatial*h));//filter kernel size
	in_=temp;/*vertical filtering*/
	double*ycy,*ypy,*xcy,*xpy;
	unsigned char*tcy,*tpy;
	memcpy(out[0][0],temp[0][0],sizeof(double)*w*3);
	for(int y=1;y<h;y++)
	{
		tpy=texture[y-1][0];
		tcy=texture[y][0];
		xcy=in_[y][0];
		ypy=out[y-1][0];
		ycy=out[y][0];
		for(int x=0;x<w;x++)
		{
			unsigned char dr=abs((*tcy++)-(*tpy++));
			unsigned char dg=abs((*tcy++)-(*tpy++));
			unsigned char db=abs((*tcy++)-(*tpy++));
			int range_dist=(((dr<<1)+dg+db)>>2);
			double weight=range_table[range_dist];
			double alpha_=weight*alpha;
			double inv_alpha_=1-alpha_;
			for(int c=0;c<3;c++) *ycy++=inv_alpha_*(*xcy++)+alpha_*(*ypy++);
		}
	}
	int h1=h-1;
	ycy=temp_2w[0][0];
	ypy=temp_2w[1][0];
	memcpy(ypy,in_[h1][0],sizeof(double)*w*3);
	int k=0; for(int x=0;x<w;x++) for(int c=0;c<3;c++) out[h1][x][c]=0.5*(out[h1][x][c]+ypy[k++]);
	for(int y=h1-1;y>=0;y--)
	{
		tpy=texture[y+1][0];
		tcy=texture[y][0];
		xcy=in_[y][0];
		double*ycy_=ycy;
		double*ypy_=ypy;
		double*out_=out[y][0];
		for(int x=0;x<w;x++)
		{
			unsigned char dr=abs((*tcy++)-(*tpy++));
			unsigned char dg=abs((*tcy++)-(*tpy++));
			unsigned char db=abs((*tcy++)-(*tpy++));
			int range_dist=(((dr<<1)+dg+db)>>2);
			double weight=range_table[range_dist];
			double alpha_=weight*alpha;
			double inv_alpha_=1-alpha_;
			for(int c=0;c<3;c++) 
			{
				//ycy[x][c]=inv_alpha_*xcy[x][c]+alpha_*ypy[x][c];
				//out[y][x][c]=0.5*(out[y][x][c]+ycy[x][c]);
				double ycc=inv_alpha_*(*xcy++)+alpha_*(*ypy_++);
				*ycy_++=ycc;
				*out_=0.5*(*out_+ycc); *out_++;
			}
		}
		memcpy(ypy,ycy,sizeof(double)*w*3);
	}
}
