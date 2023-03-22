package my.lab10.beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Data;

import java.io.Serializable;

@Named
@Data
@SessionScoped
public class Logic implements Serializable {
    double a;
    double step;
    double begin;
    double end;
    int countSteps;
    double valueIndexMaxY;
    double valueMaxYNumber;
    double valueIndexMinY;
    double valueMinYNumber;
    double valueMinXNumber;
    double valueMaxXNumber;
    double sum;
    double mid;
    double x;
    double y;
    double eps = 1.0E-4;


    public int count(double begin, double end, double h) {
        return (int)Math.round((end - begin) / h) + 1;
    }

    public double[] creatFillX(double begin, double h, double[] arrX) {
        double a = begin;
        for(int i = 0; i < arrX.length; ++i) {
            arrX[i] = a + (double)i * h;
        }
        return arrX;
    }
    public double functionY (double x,double a) {
        if (x < 2.4 - this.eps){
            y = x*Math.sqrt(Math.abs(x-a));
        } else if (Math.abs(x - 2.4) < this.eps) {
            y = Math.exp(-a*x)*Math.cos(a*x);
        } else {
            y = x*Math.sin(x*a);;
        }
        System.out.println(y);
        return y;
    }

    public double[] creatFillY(double x, double a, double[] arrX, double[] arrY) {
        for (int i = 0; i < arrX.length; ++i) {
            x = arrX[i];
            arrY[i] = functionY(x,a);
        }
        return arrY;
    }

    public double maxIndexY(double[] arrY) {
        int maxIndex = 0;

        for(int k = 0; k < arrY.length; ++k) {
            if (arrY[k] > arrY[maxIndex]) {
                maxIndex = k;
            }
        }
        return maxIndex;
    }


    public double maxYNumber(double[] arrY) {
        this.maxIndexY(arrY);
        double maxNumber = arrY[(int)this.maxIndexY(arrY)];
        return maxNumber;
    }
    public double maxXNumber(double[] arrX, double[] arrY) {
        double maxNumber = maxIndexY(arrY);
        return arrX[(int) maxNumber];
    }

    public double minIndexY(double[] arrY) {
        int minIndex=0;
        for(int i = 1; i < arrY.length; ++i) {
            if (arrY[i] < arrY[minIndex]) {
                minIndex = i;
            }
        }

        return minIndex;
    }


    public double minYNumber(double[] arrY) {
        double minNumber = 0.0;
        this.minIndexY(arrY);
        minNumber = arrY[(int)this.minIndexY(arrY)];
        return minNumber;
    }
    public double minxXNumber(double[] arrX, double[] arrY) {
        int minNumber = (int) minIndexY(arrY);
        return arrX[minNumber];
    }

    public double sumY(double[] arrY) {
        double Sum = 0.0;

        for(int i = 1; i < arrY.length; ++i) {
            Sum += arrY[i];
        }

        return Sum;
    }


    public double midY(double[] arrY) {
        double sum = sumY(arrY);
        double mid = sum/arrY.length;
        return mid;

    }


    public String show() {
        double[] arrX = new double[this.count(begin, end, step)];
        double[] arrY = new double[this.count(begin, end, step)];
        countSteps= count(begin, end, step);
        creatFillX(begin,step, arrX);
        creatFillY(x, a, arrX,arrY);
        valueIndexMaxY = maxIndexY(arrY);
        valueMaxYNumber= maxYNumber(arrY);
        valueIndexMinY =  minIndexY(arrY);
        valueMinYNumber= minYNumber(arrY);
        valueMinXNumber= minxXNumber(arrX, arrY);
        valueMaxXNumber=maxXNumber(arrX, arrY);
        sum = sumY(arrY);
        mid = midY(arrY);
        return "index";
    }
}