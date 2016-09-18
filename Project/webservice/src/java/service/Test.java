/*
 * To change this template, choose Tools / Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author hp
 */
public class Test {

    public static void main(String args[]) {

        webservice ws = new webservice();
        
       System.out.println(ws.inquiry("1234567890"));
       System.out.println(ws.getinquiryResponcode());
       System.out.println(ws.getinquiryAmmount());

//        String tes = "asdaasdad    ";
//        System.out.println(tes);
//        System.out.println(tes.trim());

 //System.out.println(new Utility().bill_periodtodb("mar2011-dec2012"));
  //     String bit48 = "7088022696       /Adhelin-9/2456456/300/456456/100/NOV2011-DEC2011/LUNAS/                   /AET1234567891010";
       String bit48 = "1234567890        /candra/2456756/456456/NOV2011-DEC2011/AWAL/                   /AET1234567891011";

        //   String bit48 = "123456789012345        /candra/2456756/456456/NOV2011-DEC2011/AWAL/                   /AET1234567891011";
    //   String bit48 =  "1234098764/yoga/456556/000000000000/NOV2011 /LUNAS/                   /AET1234567891012";
       // String bit48 = "123456789012345/candra/2456756/456456/NOV2011-DEC2011/LUNAS/                   /AET1234567891012";
    //   String bit48 = "123456789012343/yoga/456556/000000000000/NOV2011/     /                   /AET1234567891012";
    //  String bit48 = "2834111231/doble-4/2030660/87340/SEP2011-DEC2011/AWAL/                   /AET1234567891012";
       String[] bit = bit48.split("/");
//        System.out.println(bit48);
//        for (int i = 0; i < bit.length; i++) {
//            System.out.println("" + i + " => " + bit[i]);
//        }
//
//
        System.out.println(ws.bayar(bit[0], bit[1], bit[5], bit[7], bit[2], bit[3], bit[4]));
        System.out.println(ws.getbayarResponcode());
        System.out.println(ws.getbayarAmmount());

//        System.out.println(ws.reversalbayar(bit[0], bit[1], bit[5], bit[7], bit[2], bit[3], bit[4]));
//        System.out.println(ws.getreversalResponcode());
//        System.out.println(ws.getreversalAmmount());

              

//      
    }
}
