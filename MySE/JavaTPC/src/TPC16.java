import kr.tpc.OverLoad;

public class TPC16 {
	public static void main(String[] args) {
		// 1+1 = ? , 12.4+56 = ? , 56.7+78.9 = ?
		
		OverLoad ov = new OverLoad();
		ov.hap(20, 50); // ov.hap_int_int(20,50)이 호출될 것
		ov.hap(12.4f, 56); // ov.hap_float_int(12.4f,56)이 호출될 것
		ov.hap(56.7f, 78.9f); // ov.hap_float_float(56.7f,78.9f)이 호출될 것
	}

}
