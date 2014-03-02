package app;

public class CombinedProducts {
private char arrIndex[];
private double sum;

CombinedProducts(char [] chrArr, double tot){
	arrIndex = chrArr;
	sum = tot;
}




public char[] getArrIndex() {
	return arrIndex;
}




public void setArrIndex(char[] arrIndex) {
	this.arrIndex = arrIndex;
}




public double getSum() {
	return sum;
}

public void setSum(double sum) {
	this.sum = sum;
}




}
