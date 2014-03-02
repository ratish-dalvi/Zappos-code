package app;

import java.util.ArrayList;
import java.util.List;

public class CalculateProductSet {
	private static List lstFinal = new ArrayList();
	private int absDiff = 999;
	private int numOfResults = 5;
	private List closestProducts = new  ArrayList();
	private double []arrInput;
	private List lstInput;
	private int numberOfProducts;
	private double totalPrice;
	

	CalculateProductSet(List lstProd,double arrProd[], int num, double totPrice){
		arrInput = arrProd;
		lstInput = lstProd;
		numberOfProducts = num;
		totalPrice = totPrice;
	}
	
	public void insertSort(CombinedProducts objComb){
		if(closestProducts.size()==0){
			closestProducts.add(objComb);
			return ;
		}
			int iSize = closestProducts.size();
			for (int i = iSize -1; i >= 0; i--){
				if (Math.abs( totalPrice - objComb.getSum()) < Math.abs(totalPrice - ((CombinedProducts)closestProducts.get(i)).getSum()) ){
					closestProducts.add(i, objComb);
					break;
				}
			}
			if (closestProducts.size() > numOfResults)
				closestProducts.remove(numOfResults);

	}
	
	public  void getSub(int index, int num, String strIn, double sum){
		
			
			for (int i=index;i< arrInput.length; i++ ){
				if (num == 1){
						String strFinal = strIn +  new String(Integer.toString(i));
						CombinedProducts objComb = new CombinedProducts( strFinal.toCharArray(), sum + arrInput[i]);
						insertSort(objComb);
				}
				else{
					getSub(i+1, num -1, strIn + Integer.toString(i), sum + arrInput[i]);        	
				}
			}
	}

	public void calculateCloseSub(){
		getSub( 0, numberOfProducts, "", 0.0);
		printClosestProducts();
	}
	
	public void printClosestProducts(){
		ZappoProduct objProduct;
		int iComb = 0;
		System.out.println("**************************************************************************");
		System.out.println("PLEASE FIND BELOW A SET OF COMBINATION OF PRODUCTS WHICH ARE CLOSEST TO THE TOTAL OF " +  totalPrice);
		System.out.println("The combination is in increasing order of closeness " +  totalPrice);
		System.out.println("**************************************************************************");
		for(int i=0;i<closestProducts.size();i++){
			CombinedProducts objComb = (CombinedProducts)closestProducts.get(i);
			char []prodIndex = objComb.getArrIndex();
			iComb = i + 1;
			System.out.println(iComb  + " Combination of Products : " );
			System.out.println("This combination gives a total of : " +  objComb.getSum());
			for(int j = 0;j < prodIndex.length;j ++ ){
				iComb = j+1; // to print the index of element
				System.out.println("Product : " + iComb);
				int prodI = (int)prodIndex[j] - 48;
				 objProduct = (ZappoProduct)lstInput.get(prodI);
				 printZappoProduct(objProduct);
				System.out.println("----------------------------------------------------------------------");
				System.out.println("----------------------------------------------------------------------");
			}
			System.out.println("**************************************************************************");
			System.out.println("**************************************************************************");

		}
		
	}
	
	public void printZappoProduct(ZappoProduct objProd){
		System.out.println("Style ID :" + objProd.getStyleId());
		System.out.println("Product ID :" + objProd.getProductId());
		System.out.println("Product Name :" + objProd.getProductName());
		System.out.println("Price of the Product : " + objProd.getPrice());
		System.out.println("Product URL :" + objProd.getProductUrl());
		System.out.println("Colour ID :" + objProd.getColorId());
		System.out.println("Original Price :" + objProd.getOriginalPrice());
		System.out.println("Price of the Product : " + objProd.getPrice());
		System.out.println("Discount on the Product :" + objProd.getPercentOff());

	}
	
	
}
