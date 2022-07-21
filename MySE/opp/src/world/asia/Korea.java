package world.asia;

public class Korea {
	private String language; // 언어. 누구나 접근 O
	private String capital; // 수도. default기 때문에 외부에서 접근X
	private int population; // 인구. 외부에서 접근X -> 본인 class내에서만 사용 o 정보 은닉. information hiding
	public Korea() {
		capital = "서울";
		
	}
	public void setPopulation(int population) { //public method 공개이기 때문에 잘못된 자료가 들어갈 가능성이 O. 
		if(population < 1) {
			System.out.println("인구는 1이상이어야합니다");
			return;
		}
		this.population = population;
	}
}
