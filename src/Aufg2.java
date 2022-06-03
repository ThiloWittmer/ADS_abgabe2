

public class Aufg2 {
    
    public static void main(String[] args) {
    	int[] zahlen = {5, 10, -6, 5, 2, -9, -4, -4, 142, 369};
    	
    	for(int i=0; i < zahlen.length; i+=2) {
    		test(zahlen[i], zahlen[i+1]);
    	}
    }
    
	public static int mult(int x, int y) {
	    if(y==0 || x == 0) return 0;
	    else if(x>=1) return add(y,mult(dec(x),y));
	    else return mult(pm(x),(pm(y)));
	}
	
	public static int pm (int x){
	    if(x == 0) return 0;
	    else if (x < 0) return inc(pm(inc(x)));
	    else return dec(pm(dec(x)));
	}
	
	public static int add(int m, int n){
	    if(m==0) return n;
	    else if (n == 0) return m;
	    else if (m>=1) return add(dec(m),inc(n));
	    else return add(inc(m), dec(n));
	}

	public static int inc(int x) {
	    return x+1;
	}

	public static int dec(int x) {
	    return x-1;
	}
	
	static void test(int x, int y){
		int res = mult(x,y);
		System.out.print(x + ","+ y);
		if(x*y == res) System.out.println(" ok");
		else System.out.println(" falsch");
	}
}
