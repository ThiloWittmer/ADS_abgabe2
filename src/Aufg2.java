
public class Aufg2 {
    
    public static void main(String[] args) {
        int m,n;
        int ausgabe=mult(2,-9);
 System.out.println(ausgabe); 
 System.out.print("Bitte geben Sie eine positive oder negative Zahl ein: ");
     m = StaticScanner.nextInt();
     n = StaticScanner.nextInt();



 }
 public static int mult(int x, int y) {
     if(y==0) return 0;
     else if(x>=1) return add(y,mult(dec(x),y));
     else return mult(pm(x),(pm(y)));
 }
 
 public static int pm (int x){
     if(x == 0) return 0;
     else if (x < 0) return inc(pm(inc(x)));
     else return dec(pm(dec(x)));
 }
 public static int add(int m, int n){
     if(n==0) return n;
     else if (m>=1) return add(dec(m),inc(n));
     else return add(inc(m), dec(n));
 }
//	    static void test(int x, int y){
//	    	 int res = mult(x,y);
//	    	 System.out.print(x + ","+ y);
//	    	 if(x*y == res) System.out.print(" ok");
//	    	 else System.out.println(" falsch");
//	    	 }


 public static int inc(int x) {
     return x+1;
 }

 public static int dec(int x) {
     return x-1;
 }
}
