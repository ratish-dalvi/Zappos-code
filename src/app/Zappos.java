package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

/**
 * Accessing the Yelp API.
 */
public class Zappos {

  OAuthService service;
  String consumerKey = "5b8384087156eb88dce1a1d321c945564f4d858e";
  List lstProducts = null;

  /**
   * Search with term and location.
   *
   * @param term Search term
   * @param latitude Latitude
   * @param longitude Longitude
   * @return JSON string response
   */
  public String search(String term) {
    OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.zappos.com/Search?");
    request.addQuerystringParameter("term", term);
    request.addQuerystringParameter("key", consumerKey);
    //this.service.signRequest(this.accessToken, request);
    Response response = request.send();
    return response.getBody();
  }

  
  public double[] convertJSON(String jString) throws JSONException{
	  
	    final JSONObject obj = new JSONObject(jString);
	    final JSONArray resultSet = obj.getJSONArray("results");
	    int prodSize = resultSet.length();
	    lstProducts  = new ArrayList();
	    String strPrice = null;
	    double priceArray[] = new double[prodSize];
	    for (int i = 0; i < prodSize; ++i) {
	      ZappoProduct objProduct = new ZappoProduct();
	      final JSONObject eachProd = resultSet.getJSONObject(i);
	      objProduct.setStyleId(eachProd.getString("styleId"));
	      strPrice = eachProd.getString("price");
	      objProduct.setPrice(Double.parseDouble(strPrice.substring(1, strPrice.length()-1)));
	      strPrice = eachProd.getString("originalPrice");
	      objProduct.setOriginalPrice(Double.parseDouble(strPrice.substring(1, strPrice.length()-1)));
	      objProduct.setProductUrl(eachProd.getString("productUrl"));
	      objProduct.setProductName(eachProd.getString("productName"));
	      objProduct.setBrandName(eachProd.getString("brandName"));
	      objProduct.setThumbnailImageUrl(eachProd.getString("thumbnailImageUrl"));
	      objProduct.setPercentOff(eachProd.getString("percentOff"));
	      objProduct.setProductId(eachProd.getString("productId"));
	      
	      lstProducts.add(objProduct);
	      priceArray[i] = objProduct.getPrice();
	    }
	    return priceArray;
}
  

  public void searchExecute(int numProducts, Double total) throws JSONException{
	    String response = search("boots");
	    double[] priceArr  = convertJSON(response);
	    //get the ones which are closer to the total
	    CalculateProductSet objCalc = new CalculateProductSet(lstProducts,priceArr, numProducts, total);
	    objCalc.calculateCloseSub();
	   // System.out.println(response);
  }
  // this is the starting function which calls all the other functions needed to print the products
  public static void main(String[] args) throws JSONException, IOException  {
	  System.out.println("Enter the number of Products : ");  
	  BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
	  String s = bufferRead.readLine();
	  int numProducts = Integer.parseInt(s);
	  System.out.println("Enter the total sum: "); 
	  bufferRead = new BufferedReader(new InputStreamReader(System.in));
	  s = bufferRead.readLine();
	  double total = Double.parseDouble(s);

	  Zappos objZappos = new Zappos();
	  objZappos.searchExecute(numProducts, total);

  }
  
}

 