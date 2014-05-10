#include "stdafx.h"
#include <stdio.h>
#include <highgui.h>
#include <cv.h>
#include <cxcore.h>
#include <string>

std::string ext(".jpg");
std::string final;

#define PIX(img,i,j,k) (((uchar*)img->imageData) [(i)*img->widthStep + (j)*img->nChannels + (k)])

void createmetric(IplImage *img[], long ***metric,int numpiece){

	int conjside;
	int ht = img[0]->height;
	int wd = img[0]->width;

	
	 // making the ground for comparison

	  for(int c1 = 0 ; c1 < numpiece ; c1++){

		   for(int k = 0; k < 4 ;k++){

			  conjside=0;

				 switch(k){
				 case 0:conjside=1;
				        break;
				 case 1:conjside=0;
				        break;
				 case 2:conjside=3;
				        break;
				 case 3:conjside=2;
				        break;
				 }

			  for(int c2 = 0 ; c2 < numpiece ; c2++){
					
				  if(k==0 || k==1)
				  for(int m = 0; m < ht ;m++){

					for(int l=0 ; l<3 ; l++){
						metric[c1][c2][k] += abs(PIX(img[c1],m,wd-1,l) - PIX(img[c2],m,0,l));
						metric[c2][c1][conjside] = metric[c1][c2][k];
					}
				  }

				  if(k==2|| k==2)
				  for(int m = 0; m < wd ;m++){

					for(int l=0 ; l<3 ; l++){
						metric[c1][c2][k] += abs(PIX(img[c1],ht-1,m,l) - PIX(img[c2],0,m,l));
						metric[c2][c1][conjside] = metric[c1][c2][k];
					}
				  }

			  }
			  
		   }

    	}
}

int* solution(long ***metric,int numpiece){

	// write an algo by fixing one as left corner and making all other connections based on the minima condition
	long mindistance;


	long *distancetotal = (long *)malloc(numpiece*sizeof(int));
	for(int i =0 ; i<numpiece;i++)
		distancetotal[i] = 0;

	int tileselected;
	int *fixed = (int*)malloc(numpiece*sizeof(int));
	for(int i =0 ; i<numpiece;i++)
		fixed[i] = 0;

	int **order = (int**)malloc(numpiece*sizeof(int*));
	for(int i =0; i<numpiece; i++)
		order[i] = (int*)malloc(numpiece*sizeof(int));

	for(int i =0; i<numpiece; i++){
		for(int j =0; j<numpiece; j++){
			order[i][j] = -1;
		}
	}
	
	for(int p=0 ; p<numpiece ;p++){
		fixed[p] = 1;
		order[0][p] = p;

		for(int i = 0 ; i < numpiece ; i++){
		
			if(i < sqrt(numpiece) - 1){
				mindistance = 9999999999;
		
				for(int j =0 ; j< numpiece ;j++){

					if(fixed[j] == 1)
						continue;
					if(metric[order[i][p]][j][0] < mindistance){
						mindistance = metric[order[i][p]][j][0];
						tileselected = j;
					}
				}

				fixed[tileselected] = 1;
				order[i+1][p] = tileselected;
				distancetotal[p] += mindistance;
			
			}


		for(int q = 1; q < sqrt(numpiece);q++){
			if(i == q*sqrt(numpiece) - 1){
				mindistance = 9999999999;

				for(int j = 0 ; j<numpiece; j++){
						
					if(fixed[j] == 1)
						continue;

					if(metric[order[i- (int)sqrt(numpiece) + 1][p]][j][2] < mindistance){
						mindistance = metric[order[i- (int)sqrt(numpiece) + 1][p]][j][2];
						tileselected = j;
					}
				}

				fixed[tileselected] = 1;
				order[i+1][p] = tileselected;
				distancetotal[p] += mindistance;
			}
		}

		for(int q = 1; q <= sqrt(numpiece); q++){
			if( (i > q*sqrt(numpiece) - 1 && i < (q+1)*sqrt(numpiece) - 1)){

				mindistance = 9999999999;
					
				for(int j = 0 ; j<numpiece ; j++){

					if(fixed[j] == 1)
						continue;
					if((metric[order[i][p]][j][0] + metric[order[i- (int)sqrt(numpiece) + 1][p]][j][2]) < mindistance){
						mindistance =  metric[order[i][p]][j][0] + metric[order[i- (int)sqrt(numpiece) + 1][p]][j][2];
						tileselected = j;
					}
				}

				fixed[tileselected] = 1;
				order[i+1][p] = tileselected;
				distancetotal[p] += mindistance;
			}
		}

		}

		for(int i = 0; i<numpiece;i++)
			fixed[i] = 0;

	}

	int *finalorder = (int*)malloc(numpiece*sizeof(int));
	mindistance = 9999999999;
	for(int l = 0; l<numpiece ;l++){
		if(distancetotal[l] < mindistance){
			mindistance = distancetotal[l];

			for(int m = 0; m <numpiece;m++){
				finalorder[m] = order[m][l];
			}
		}
	}
	return finalorder;
	
}

void unscrimg(IplImage *img[],int *finalorder,int numpiece){

	IplImage *unscrimage = cvCreateImage(cvSize(img[0]->width*sqrt(numpiece),img[0]->height*sqrt(numpiece)),IPL_DEPTH_8U,3);
	int ht = unscrimage->height;
	int wd = unscrimage->width;

	int count1 = 0;

	for(int j = 0; j < wd; j += img[0]->width){
		for(int m = 0; m < img[0]->width; m++) {
			for(int i = 0; i < ht; i += img[0]->height) {
				for(int n = 0; n < img[0]->height; n++) {

					for(int k =0 ; k<3; k++)
						PIX(unscrimage,i+n,m+j,k) = PIX(img[finalorder[i*(int)sqrt(numpiece)/(img[0]->height) + j/(img[0]->width)]],n,m,k);
				 }
			 }
		}
	}


	cvNamedWindow("Unscrambled Image",0);
	cvShowImage("Unscrambled Image",unscrimage);

	cvWaitKey(0);
	cvReleaseImage(&unscrimage);

}

void writetofile(int *finalorder,int numpiece){

	FILE *file = fopen("ouput.txt","w");

	for(int i = 0 ; i < numpiece ; i++){
	
		fprintf(file," %d ",finalorder[i]);

		for(int s = 0 ; s < sqrt(numpiece) ; s++)
		if(i == s*sqrt(numpiece) - 1)
			fprintf(file,"\n");

		}	
}

int main()
{

	/*IplImage *img[] = {cvLoadImage("1.jpg"),cvLoadImage("2.jpg"),cvLoadImage("3.jpg"),cvLoadImage("4.jpg"),cvLoadImage("5.jpg"),cvLoadImage("6.jpg"),cvLoadImage("7.jpg"),cvLoadImage("8.jpg"),cvLoadImage("9.jpg"),cvLoadImage("10.jpg"),cvLoadImage("11.jpg"),cvLoadImage("12.jpg"),cvLoadImage("13.jpg"),cvLoadImage("14.jpg"),cvLoadImage("15.jpg"),cvLoadImage("16.jpg"),cvLoadImage("17.jpg"),cvLoadImage("18.jpg"),cvLoadImage("19.jpg"),cvLoadImage("20.jpg"),cvLoadImage("21.jpg"),cvLoadImage("22.jpg"),cvLoadImage("23.jpg"),cvLoadImage("24.jpg"),cvLoadImage("25.jpg"),cvLoadImage("26.jpg"),cvLoadImage("27.jpg"),cvLoadImage("28.jpg"),cvLoadImage("29.jpg"),cvLoadImage("30.jpg"),cvLoadImage("31.jpg"),cvLoadImage("32.jpg"),cvLoadImage("33.jpg"),cvLoadImage("34.jpg"),cvLoadImage("35.jpg"),cvLoadImage("36.jpg"),cvLoadImage("37.jpg"),cvLoadImage("38.jpg"),cvLoadImage("39.jpg"),cvLoadImage("40.jpg"),cvLoadImage("41.jpg"),cvLoadImage("42.jpg"),cvLoadImage("43.jpg"),cvLoadImage("44.jpg"),cvLoadImage("45.jpg"),cvLoadImage("46.jpg"),cvLoadImage("47.jpg"),cvLoadImage("48.jpg"),cvLoadImage("49.jpg"),cvLoadImage("50.jpg"),cvLoadImage("51.jpg"),cvLoadImage("52.jpg"),cvLoadImage("53.jpg"),cvLoadImage("54.jpg"),cvLoadImage("55.jpg"),cvLoadImage("56.jpg"),cvLoadImage("57.jpg"),cvLoadImage("58.jpg"),cvLoadImage("59.jpg"),cvLoadImage("60.jpg"),cvLoadImage("61.jpg"),cvLoadImage("62.jpg"),cvLoadImage("63.jpg"),cvLoadImage("64.jpg"),cvLoadImage("65.jpg"),cvLoadImage("66.jpg"),cvLoadImage("67.jpg"),cvLoadImage("68.jpg"),cvLoadImage("69.jpg"),cvLoadImage("70.jpg"),cvLoadImage("71.jpg"),cvLoadImage("72.jpg"),cvLoadImage("73.jpg"),cvLoadImage("74.jpg"),cvLoadImage("75.jpg"),cvLoadImage("76.jpg"),cvLoadImage("77.jpg"),cvLoadImage("78.jpg"),cvLoadImage("79.jpg"),cvLoadImage("80.jpg"),cvLoadImage("81.jpg"),cvLoadImage("82.jpg"),cvLoadImage("83.jpg"),cvLoadImage("84.jpg"),cvLoadImage("85.jpg"),cvLoadImage("86.jpg"),cvLoadImage("87.jpg"),cvLoadImage("88.jpg"),cvLoadImage("89.jpg"),cvLoadImage("90.jpg"),cvLoadImage("91.jpg"),cvLoadImage("92.jpg"),cvLoadImage("93.jpg"),cvLoadImage("94.jpg"),cvLoadImage("95.jpg"),cvLoadImage("96.jpg"),cvLoadImage("97.jpg"),cvLoadImage("98.jpg"),cvLoadImage("99.jpg"),cvLoadImage("100.jpg"),cvLoadImage("101.jpg"),cvLoadImage("102.jpg"),cvLoadImage("103.jpg"),cvLoadImage("104.jpg"),cvLoadImage("105.jpg"),cvLoadImage("106.jpg"),cvLoadImage("107.jpg"),cvLoadImage("108.jpg"),cvLoadImage("109.jpg"),cvLoadImage("110.jpg"),cvLoadImage("111.jpg"),cvLoadImage("112.jpg"),cvLoadImage("113.jpg"),cvLoadImage("114.jpg"),cvLoadImage("115.jpg"),cvLoadImage("116.jpg"),cvLoadImage("117.jpg"),cvLoadImage("118.jpg"),cvLoadImage("119.jpg"),cvLoadImage("120.jpg"),cvLoadImage("121.jpg"),cvLoadImage("122.jpg"),cvLoadImage("123.jpg"),cvLoadImage("124.jpg"),cvLoadImage("125.jpg"),cvLoadImage("126.jpg"),cvLoadImage("127.jpg"),cvLoadImage("128.jpg"),cvLoadImage("129.jpg"),cvLoadImage("130.jpg"),cvLoadImage("131.jpg"),cvLoadImage("132.jpg"),cvLoadImage("133.jpg"),cvLoadImage("134.jpg"),cvLoadImage("135.jpg"),cvLoadImage("136.jpg"),cvLoadImage("137.jpg"),cvLoadImage("138.jpg"),cvLoadImage("139.jpg"),cvLoadImage("140.jpg"),cvLoadImage("141.jpg"),cvLoadImage("142.jpg"),cvLoadImage("143.jpg"),cvLoadImage("144.jpg"),cvLoadImage("145.jpg"),cvLoadImage("146.jpg"),cvLoadImage("147.jpg"),cvLoadImage("148.jpg"),cvLoadImage("149.jpg"),cvLoadImage("150.jpg"),cvLoadImage("151.jpg"),cvLoadImage("152.jpg"),cvLoadImage("153.jpg"),cvLoadImage("154.jpg"),cvLoadImage("155.jpg"),cvLoadImage("156.jpg"),cvLoadImage("157.jpg"),cvLoadImage("158.jpg"),cvLoadImage("159.jpg"),cvLoadImage("160.jpg"),cvLoadImage("161.jpg"),cvLoadImage("162.jpg"),cvLoadImage("163.jpg"),cvLoadImage("164.jpg"),cvLoadImage("165.jpg"),cvLoadImage("166.jpg"),cvLoadImage("167.jpg"),cvLoadImage("168.jpg"),cvLoadImage("169.jpg"),cvLoadImage("170.jpg"),cvLoadImage("171.jpg"),cvLoadImage("172.jpg"),cvLoadImage("173.jpg"),cvLoadImage("174.jpg"),cvLoadImage("175.jpg"),cvLoadImage("176.jpg"),cvLoadImage("177.jpg"),cvLoadImage("178.jpg"),cvLoadImage("179.jpg"),cvLoadImage("180.jpg"),cvLoadImage("181.jpg"),cvLoadImage("182.jpg"),cvLoadImage("183.jpg"),cvLoadImage("184.jpg"),cvLoadImage("185.jpg"),cvLoadImage("186.jpg"),cvLoadImage("187.jpg"),cvLoadImage("188.jpg"),cvLoadImage("189.jpg"),cvLoadImage("190.jpg"),cvLoadImage("191.jpg"),cvLoadImage("192.jpg"),cvLoadImage("193.jpg"),cvLoadImage("194.jpg"),cvLoadImage("195.jpg"),cvLoadImage("196.jpg"),cvLoadImage("197.jpg"),cvLoadImage("198.jpg"),cvLoadImage("199.jpg"),cvLoadImage("200.jpg"),cvLoadImage("201.jpg"),cvLoadImage("202.jpg"),cvLoadImage("203.jpg"),cvLoadImage("204.jpg"),cvLoadImage("205.jpg"),cvLoadImage("206.jpg"),cvLoadImage("207.jpg"),cvLoadImage("208.jpg"),cvLoadImage("209.jpg"),cvLoadImage("210.jpg"),cvLoadImage("211.jpg"),cvLoadImage("212.jpg"),cvLoadImage("213.jpg"),cvLoadImage("214.jpg"),cvLoadImage("215.jpg"),cvLoadImage("216.jpg"),cvLoadImage("217.jpg"),cvLoadImage("218.jpg"),cvLoadImage("219.jpg"),cvLoadImage("220.jpg"),cvLoadImage("221.jpg"),cvLoadImage("222.jpg"),cvLoadImage("223.jpg"),cvLoadImage("224.jpg"),cvLoadImage("225.jpg"),cvLoadImage("226.jpg"),cvLoadImage("227.jpg"),cvLoadImage("228.jpg"),cvLoadImage("229.jpg"),cvLoadImage("230.jpg"),cvLoadImage("231.jpg"),cvLoadImage("232.jpg"),cvLoadImage("233.jpg"),cvLoadImage("234.jpg"),cvLoadImage("235.jpg"),cvLoadImage("236.jpg"),cvLoadImage("237.jpg")};
	int numpiece = 0;

	for(int i = 0; i <238 ;i++){

		if(!img[i]){
			numpiece = i;
			break;
		}
	}*/

	int numpiece = 0;
	IplImage *img[10000];
	char *temp;

	for(int i = 1; i <= 10000; i++){

		final = std::to_string(i) + ext;
		temp = new char[final.length() + 1];
		strcpy(temp,final.c_str());
		img[i-1] = cvLoadImage(temp);

		if(!img[i-1]){
			numpiece = i;
			break;
		}

	}
	
	delete temp;

	printf("%d",numpiece);
	getchar();

	for(int i=0 ; i< numpiece; i++)
		cvCvtColor(img[i],img[i],CV_BGR2Lab);

	// write a code for normalizing the LAB colour modes -- for better accuracy maybe (its a good practice)
	  
	long ***metric = new long**[numpiece];

	for(int i = 0; i < numpiece;i++){
		metric[i] = new long*[numpiece];

		for(int j = 0; j < numpiece;j++){
		metric[i][j] = new long[4];

			for(int k = 0; k<4 ;k++){
				metric[i][j][k] = 0;
			}
		}
	}

	
	createmetric(img,metric,numpiece);  

	int *finalorder;
	finalorder = solution(metric,numpiece);

	for(int j = 0 ; j<numpiece ;j++)
		printf(" %d ",finalorder[j]);
	
	unscrimg(img,finalorder,numpiece);
	writetofile(finalorder,numpiece);
	
	getchar();
	return(0);
}